package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.DG2TitreProDto;
import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.dtos.TitreProfessionnelDto;
import org.dawan.formations.dtos.VilleDto;
import org.dawan.formations.services.*;
import org.dawan.formations.tools.DtoTools;
import org.dawan.formations.tools.RestTemplateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DG2ServiceImpl implements DG2Service {

    private final VilleService villeService;
    private final TitreProfessionnelService titreProfessionnelService;
    private final PromotionService promotionService;

    @Autowired
    public DG2ServiceImpl(VilleService villeService, TitreProfessionnelService titreProfessionnelService, PromotionService promotionService) {
        this.villeService = villeService;
        this.titreProfessionnelService = titreProfessionnelService;
        this.promotionService = promotionService;
    }

    @Override
    public String GETformations(boolean force) {

        List<Object> saved = new ArrayList<>();
        RestTemplateTool.getListFromUrl("https://dawan.org/public/location/", VilleDto.class)
                .forEach(villeDto -> saveOrUpdate(villeService, villeDto, force,saved));
        return Arrays.stream(RestTemplateTool.getObjectFromUrl("https://dawan.org/public/training/search?keywords=titre-professionnel", DG2TitreProDto[].class))
                .map(dg2Titre -> {
                    TitreProfessionnelDto titre = titreProfessionnelService.getByTitre(dg2Titre.getTraining().getSlug());
                    if(titre == null || force) {
                        TitreProfessionnelDto newTitre = new TitreProfessionnelDto();
                        newTitre.setSlug(dg2Titre.getTraining().getSlug());
                        newTitre.setTitre(dg2Titre.getTraining().getTitle());
                        newTitre.setObjectives(dg2Titre.getTraining().getObjectives());
                        newTitre.setDescription(dg2Titre.getTraining().getDescription());
                        titre = saveOrUpdate(titreProfessionnelService, newTitre, force,saved);
                    }
                    TitreProfessionnelDto finalTitre = titre;
                    dg2Titre.getNext_sessions().forEach(s -> {
                        PromotionDto p = new PromotionDto();
                        p.setDateDebut(s.getBeginAt().toDate());
                        p.setDateFin(s.getFinishAt().toDate());
                        p.setEtudiantsId(new ArrayList<>());
                        p.setVilleId(villeService.GETBySlug(s.getLocation()).getId());
                        p.setTitreProfessionnelId(finalTitre.getId());
                        saveOrUpdate(promotionService, p, force,saved);
                    });
                    return saved;
                })
                .reduce(new ArrayList<>(),(a,b) -> {
                    a.addAll(b);
                    return a;
                }).stream().distinct()
                .collect(Collectors.groupingBy(Object::getClass))
                .entrySet()
                .stream().map(e -> e.getKey().getName() + " : " + e.getValue().size() + " saved.")
                .reduce((a,b) -> String.join("\n",a,b)).orElse("Aucune modification");

    }

    private <E,T> T saveOrUpdate(GenericService<E,T,?> service, T dto, boolean force, List<Object> count) {
        List<T> presents = service.GETAll();
        T saveOrUpdate = null;
        if (presents.contains(dto)) {
            if (force) {
                saveOrUpdate = DtoTools.update(dto, presents.get(presents.indexOf(dto)));
            }
        } else {
            saveOrUpdate = dto;
        }

        if (saveOrUpdate != null) {
            saveOrUpdate = service.POSTSave(saveOrUpdate);
            count.add(saveOrUpdate);
        }
        return saveOrUpdate;
    }
}
