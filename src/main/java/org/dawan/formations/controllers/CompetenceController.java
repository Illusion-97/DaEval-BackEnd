package org.dawan.formations.controllers;

import org.dawan.formations.dtos.CompetenceDto;
import org.dawan.formations.models.Competence;
import org.dawan.formations.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Competence")
public class CompetenceController extends GenericController<Competence,CompetenceDto,CompetenceService> {


    @Autowired
    protected CompetenceController(CompetenceService service) {
        super(service);
    }

    @GetMapping("/Bloc/{id}")
    public List<CompetenceDto> getByBlocId(@PathVariable("id") Long id)
    {
        return getService().GETfindByBlocCompetences_Id(id);
    }
}
