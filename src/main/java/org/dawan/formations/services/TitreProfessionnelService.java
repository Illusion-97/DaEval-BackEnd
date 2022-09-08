package org.dawan.formations.services;

import org.dawan.formations.dtos.TitreProfessionnelDto;
import org.dawan.formations.models.TitreProfessionnel;
import org.dawan.formations.repositories.TitreProfessionnelRepository;

public interface TitreProfessionnelService extends GenericService<TitreProfessionnel, TitreProfessionnelDto, TitreProfessionnelRepository> {
    TitreProfessionnelDto getByTitre(String titre);
}
