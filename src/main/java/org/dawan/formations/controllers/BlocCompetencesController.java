package org.dawan.formations.controllers;

import org.dawan.formations.dtos.BlocCompetencesDto;
import org.dawan.formations.models.BlocCompetences;
import org.dawan.formations.services.BlocCompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/BlocCompetences")
public class BlocCompetencesController extends GenericController<BlocCompetences,BlocCompetencesDto,BlocCompetencesService> {


    @Autowired
    protected BlocCompetencesController(BlocCompetencesService service) {
        super(service);
    }

    @GetMapping("/Titre/{id}")
    public List<BlocCompetencesDto> getByTitreId(@PathVariable("id") Long id)
    {
        return getService().GETfindByTitreProId(id);
    }
}
