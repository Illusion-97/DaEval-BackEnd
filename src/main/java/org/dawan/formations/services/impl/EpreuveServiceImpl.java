package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.EpreuveDto;
import org.dawan.formations.models.Epreuve;
import org.dawan.formations.repositories.EpreuveRepository;
import org.dawan.formations.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EpreuveServiceImpl extends GenericServiceImpl<Epreuve, EpreuveDto,EpreuveRepository> implements EpreuveService {

    @Autowired
    public EpreuveServiceImpl(EpreuveRepository repo) {
        super(repo, Epreuve.class, EpreuveDto.class);
    }

    @Override
    public List<EpreuveDto> GETfindByBlocCompetences_Id(Long blocCompetenceId) {
        return getListDto(getRepo().findByBlocCompetences_Id(blocCompetenceId));
    }

    public List<EpreuveDto> POSTFilteredByPage(EpreuveDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getTitre(), obj.getDescription(),
                obj.getBlocCompetencesId(), obj.getType(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(EpreuveDto obj) {
        return getRepo().getCount(obj.getTitre(), obj.getDescription(),
                obj.getBlocCompetencesId(), obj.getType());
    }
}
