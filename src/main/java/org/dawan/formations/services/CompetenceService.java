package org.dawan.formations.services;

import org.dawan.formations.dtos.CompetenceDto;
import org.dawan.formations.models.Competence;
import org.dawan.formations.repositories.CompetenceRepository;

import java.util.List;

public interface CompetenceService extends GenericService<Competence, CompetenceDto, CompetenceRepository> {
    List<CompetenceDto> GETfindByBlocCompetences_Id(Long id);
}
