package org.dawan.formations.controllers;

import org.dawan.formations.dtos.PromotionDto;
import org.dawan.formations.models.Promotion;
import org.dawan.formations.services.PromotionService;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Promotion")
public class PromotionController extends GenericController<Promotion,PromotionDto,PromotionService> {


    @Autowired
    protected PromotionController(PromotionService service) {
        super(service);
    }

    @GetMapping("/Search")
    public List<PromotionDto> search(
            @RequestParam(defaultValue = "") String ville,
            @RequestParam(defaultValue = "") String titre,
            @RequestParam(defaultValue = "1970-01-01") String date,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer max)
    {
        return getService().GETfindByVilleAndTitreAndDateDebut(
                ville,titre, LocalDate.parse(date).toDate(),page,max);
    }

    @GetMapping("Titre&Ville")
    public List<PromotionDto> titreVille(
            @RequestParam Optional<Long> titre,
            @RequestParam Optional<Long> ville)
    {
        return getService().GETfindByTitreAndVille(titre.orElse(null),ville.orElse(null));
    }
}
