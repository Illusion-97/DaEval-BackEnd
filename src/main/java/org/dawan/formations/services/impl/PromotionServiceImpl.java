package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.EtudiantDto;
import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.models.Promotion;
import org.dawan.formations.repositories.PromotionRepository;
import org.dawan.formations.services.PromotionService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromotionServiceImpl extends GenericServiceImpl<Promotion, PromotionDto, PromotionRepository> implements PromotionService {

    @Autowired
    public PromotionServiceImpl(PromotionRepository repo) {
        super(repo, Promotion.class, PromotionDto.class);
    }

    @Override
    public List<PromotionDto> GETfindByVilleAndTitreAndDateDebut(String name, String titre, Date dateDebut, int page, int max) {
        return getListDto(getRepo().findByVilleNameContainingAndTitreProfessionnelTitreContainingAndDateDebutIsAfter(name, titre, dateDebut, PageRequest.of(page,max)));
    }

    @Override
    public List<PromotionDto> GETfindByTitreAndVille(Long titreId, Long villeId) {
        return getListDto(getRepo().findByTitreProfessionnelIdAndVilleId(titreId, villeId));
    }

    @Override
    public Long GETcountByTitreAndVille(Long titreId, Long villeId) {
        return getRepo().countByTitreAndVille(titreId,villeId);
    }

    public List<PromotionDto> POSTFilteredByPage(PromotionDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getTitreProfessionnelId(), obj.getVilleId(), obj.getDateDebut(), obj.getDateFin(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(PromotionDto obj) {
        return getRepo().getCount(obj.getTitreProfessionnelId(), obj.getVilleId(), obj.getDateDebut(), obj.getDateFin());
    }

    public List<PromotionDto> POSTAllById(EtudiantDto obj) {
        return getListDto(getRepo().findAllById(obj.getPromotionsId()));
    }
}
