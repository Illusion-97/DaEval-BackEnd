package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.CompetenceDto;
import org.dawan.formations.models.Competence;
import org.dawan.formations.repositories.CompetenceRepository;
import org.dawan.formations.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompetenceServiceImpl extends GenericServiceImpl<Competence, CompetenceDto, CompetenceRepository> implements CompetenceService {

    @Autowired
    public CompetenceServiceImpl(CompetenceRepository repo) {
        super(repo, Competence.class, CompetenceDto.class);
    }

    @Override
    public List<CompetenceDto> GETfindByBlocCompetences_Id(Long blocCompetenceId) {
        return getListDto(getRepo().findByBlocCompetences_Id(blocCompetenceId));
    }

    public List<CompetenceDto> POSTFilteredByPage(CompetenceDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getTitre(), obj.getDescription(), obj.getBlocCompetencesId(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(CompetenceDto obj) {
        return getRepo().getCount(obj.getTitre(), obj.getDescription(), obj.getBlocCompetencesId());
    }
}
