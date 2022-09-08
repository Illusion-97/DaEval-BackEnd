package org.dawan.formations.services.impl;

import org.dawan.formations.dtos.EvaluationDto;
import org.dawan.formations.models.Evaluation;
import org.dawan.formations.repositories.EvaluationRepository;
import org.dawan.formations.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluationServiceImpl extends GenericServiceImpl<Evaluation, EvaluationDto, EvaluationRepository> implements EvaluationService {

    @Autowired
    public EvaluationServiceImpl(EvaluationRepository repo) {
        super(repo, Evaluation.class, EvaluationDto.class);
    }


    @Override
    public List<EvaluationDto> GETfindByBlocAndEtudiantAndPromotion(Long blocId, Long etudiantId, Long promoId) {
        return getListDto(getRepo().findByEpreuve_BlocCompetences_IdAndEtudiant_IdAndPromotion_Id(etudiantId,promoId,blocId));
    }

    @Override
    public Double GETavgByEtudiantAndPromotionAndBloc(Long etudiantId, Long promoId, Long blocId) {
        return getRepo().getAvgByEtudiantIdAndPromotionIdAndBlocCompetenceId(etudiantId, promoId, blocId);
    }

    public List<EvaluationDto> POSTFilteredByPage(EvaluationDto obj, int page, int max) {
        return getListDto(getRepo().getFiltered(obj.getEpreuveId(), obj.getEtudiantId(), obj.getPromotionId(), obj.getBlocCompetencesId(), PageRequest.of(page,max)));
    }

    public Long POSTFilteredCount(EvaluationDto obj) {
        return getRepo().getCount(obj.getEpreuveId(), obj.getEtudiantId(), obj.getPromotionId(),  obj.getBlocCompetencesId());
    }
}
