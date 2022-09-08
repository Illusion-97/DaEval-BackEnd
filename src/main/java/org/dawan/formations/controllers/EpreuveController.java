package org.dawan.formations.controllers;

import org.dawan.formations.dtos.EpreuveDto;
import org.dawan.formations.models.Epreuve;
import org.dawan.formations.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Epreuve")
public class EpreuveController extends GenericController<Epreuve,EpreuveDto,EpreuveService> {


    @Autowired
    protected EpreuveController(EpreuveService service) {
        super(service);
    }

    @GetMapping("/Bloc/{id}")
    public List<EpreuveDto> getByBlocId(@PathVariable("id") Long id)
    {
        return getService().GETfindByBlocCompetences_Id(id);
    }
}
