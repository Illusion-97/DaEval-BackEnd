package org.dawan.formations.controllers;

import org.dawan.formations.dtos.EvaluationDto;
import org.dawan.formations.models.Evaluation;
import org.dawan.formations.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Evaluation")
public class EvaluationController extends GenericController<Evaluation,EvaluationDto,EvaluationService> {

    @Autowired
    protected EvaluationController(EvaluationService service) {
        super(service);
    }

    @GetMapping("/AVG")
    public Double search(
            @RequestParam(required = false) Long etudiantId,
            @RequestParam(required = false) Long promotionId,
            @RequestParam(required = false) Long blocId)
    {
        return getService().GETavgByEtudiantAndPromotionAndBloc(etudiantId, promotionId, blocId);
    }
}
