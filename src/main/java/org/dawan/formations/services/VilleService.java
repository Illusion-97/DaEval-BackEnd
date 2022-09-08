package org.dawan.formations.services;

import org.dawan.formations.dtos.VilleDto;
import org.dawan.formations.models.Ville;
import org.dawan.formations.repositories.VilleRepository;

public interface VilleService extends GenericService<Ville, VilleDto, VilleRepository> {
    VilleDto GETBySlug(String slug);
}
