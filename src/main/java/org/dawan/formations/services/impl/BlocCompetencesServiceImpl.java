package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.BlocCompetencesDto;
import org.dawan.formations.dtos.EtudiantDto;
import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.models.BlocCompetences;
import org.dawan.formations.repositories.BlocCompetencesRepository;
import org.dawan.formations.services.BlocCompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlocCompetencesServiceImpl extends GenericServiceImpl<BlocCompetences, BlocCompetencesDto, BlocCompetencesRepository> implements BlocCompetencesService {

    @Autowired
    public BlocCompetencesServiceImpl(BlocCompetencesRepository repo) {
        super(repo, BlocCompetences.class, BlocCompetencesDto.class);
    }

    @Override
    public List<BlocCompetencesDto> GETfindByTitreProId(Long titreId) {
        return getListDto(getRepo().findByTitreProfessionnel_Id(titreId));
    }

    @Override
    public Long GETCountByTitreProId(Long titreId) {
        return getRepo().countByTitreProfessionnelId(titreId);
    }

    public List<BlocCompetencesDto> POSTFilteredByPage(BlocCompetencesDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getTitre(), obj.getDescription(), obj.getTitreProfessionnelId(),PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(BlocCompetencesDto obj) {
        return getRepo().getCount(obj.getTitre(), obj.getDescription(), obj.getTitreProfessionnelId());
    }

}
