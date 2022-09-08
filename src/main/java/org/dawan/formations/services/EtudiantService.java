package org.dawan.formations.services;

import org.dawan.formations.dtos.EtudiantDto;
import org.dawan.formations.models.Etudiant;
import org.dawan.formations.repositories.EtudiantRepository;

import java.util.List;

public interface EtudiantService extends GenericService<Etudiant, EtudiantDto, EtudiantRepository> {
    List<EtudiantDto> GETsearchByNameOrEmail(String search, int page, int max);

    Etudiant inscription(long id, Long etudiant);

    List<EtudiantDto> GETfindByPromotionId(long promoId);

    List<EtudiantDto> GETfindByVilleId(long promoId);
}
