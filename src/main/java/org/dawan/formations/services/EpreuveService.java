package org.dawan.formations.services;

import org.dawan.formations.dtos.EpreuveDto;
import org.dawan.formations.models.Epreuve;
import org.dawan.formations.repositories.EpreuveRepository;

import java.util.List;

public interface EpreuveService extends GenericService<Epreuve, EpreuveDto, EpreuveRepository> {
    List<EpreuveDto> GETfindByBlocCompetences_Id(Long id);
}
