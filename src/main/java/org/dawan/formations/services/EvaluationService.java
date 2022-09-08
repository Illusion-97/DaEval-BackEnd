package org.dawan.formations.services;

import org.dawan.formations.dtos.EvaluationDto;
import org.dawan.formations.models.Evaluation;
import org.dawan.formations.repositories.EvaluationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationService extends GenericService<Evaluation, EvaluationDto, EvaluationRepository> {
    List<EvaluationDto> GETfindByBlocAndEtudiantAndPromotion(Long id, Long id1, Long id2);

    Double GETavgByEtudiantAndPromotionAndBloc(Long etudiantId, Long promotionId, Long blocId);
}
