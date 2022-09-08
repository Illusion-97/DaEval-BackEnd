package org.dawan.formations.tools;

import org.dawan.formations.dtos.*;
import org.dawan.formations.models.*;
import org.dawan.formations.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class DtoTools {

    private static final ModelMapper myMapper = new ModelMapper();

    public DtoTools() {
        myMapper.typeMap(Etudiant.class, EtudiantDto.class)
                .addMappings(mapper -> mapper.using(context -> ((context.getSource()==null)?new ArrayList<Promotion>():(List<Promotion>)context.getSource()).stream().map(Promotion::getId).collect(Collectors.toList())).map(Etudiant::getPromotions, EtudiantDto::setPromotionsId));
        myMapper.typeMap(Promotion.class, PromotionDto.class)
                .addMappings(mapper -> mapper.using(context -> ((context.getSource()==null)?new ArrayList<Etudiant>():(List<Etudiant>)context.getSource()).stream().map(Etudiant::getId).collect(Collectors.toList())).map(Promotion::getEtudiants, PromotionDto::setEtudiantsId));

    }


    public static <S, D> D convert(S obj, Class<D> clazz) {
        return myMapper.map(obj, clazz);
    }

    public static <T> T update(T origin, T destination) {
        Class<?> clazz = origin.getClass();
        if (clazz == VilleDto.class) {
            ((VilleDto) destination).setName(((VilleDto) origin).getName());
        } else if (clazz == TitreProfessionnelDto.class) {
            TitreProfessionnelDto o = ((TitreProfessionnelDto) origin);
            TitreProfessionnelDto d = ((TitreProfessionnelDto) destination);
            d.setTitre(o.getTitre());
            d.setSlug(o.getSlug());
            d.setTitre(o.getTitre());
            d.setObjectives(o.getObjectives());
            d.setDescription(o.getDescription());
        } else if (clazz == PromotionDto.class) {
            PromotionDto o = ((PromotionDto) origin);
            PromotionDto d = ((PromotionDto) destination);
            d.setVilleId(o.getVilleId());
            d.setTitreProfessionnelId(o.getTitreProfessionnelId());
            d.setDateDebut(o.getDateDebut());
            d.setDateFin(o.getDateFin());
            if(d.getEtudiantsId() == null) d.setEtudiantsId(new ArrayList<>());
        }
        return destination;
    }
}
