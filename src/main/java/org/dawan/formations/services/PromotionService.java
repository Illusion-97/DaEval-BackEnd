package org.dawan.formations.services;

import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.models.Promotion;
import org.dawan.formations.repositories.PromotionRepository;
import java.util.Date;
import org.springframework.lang.Nullable;

import java.util.List;

public interface PromotionService extends GenericService<Promotion, PromotionDto, PromotionRepository> {

    List<PromotionDto> GETfindByVilleAndTitreAndDateDebut(String name, String titre, Date dateDebut, int page, int max);

    List<PromotionDto> GETfindByTitreAndVille(@Nullable Long id, @Nullable Long id1);

    Long GETcountByTitreAndVille(@Nullable Long id, @Nullable Long id1);

}
