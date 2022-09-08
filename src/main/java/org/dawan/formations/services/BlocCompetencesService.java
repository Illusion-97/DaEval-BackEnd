package org.dawan.formations.services;

import org.dawan.formations.dtos.BlocCompetencesDto;
import org.dawan.formations.models.BlocCompetences;
import org.dawan.formations.repositories.BlocCompetencesRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BlocCompetencesService extends GenericService<BlocCompetences, BlocCompetencesDto, BlocCompetencesRepository> {
    List<BlocCompetencesDto> GETfindByTitreProId(Long id);
    Long GETCountByTitreProId(Long titreId);
}
