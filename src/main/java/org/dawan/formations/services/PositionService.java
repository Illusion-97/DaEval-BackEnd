package org.dawan.formations.services;

import org.dawan.formations.dtos.PositionDto;
import org.dawan.formations.models.Positionnement;
import org.dawan.formations.repositories.PositionRepository;

public interface PositionService extends GenericService<Positionnement, PositionDto, PositionRepository> {
}
