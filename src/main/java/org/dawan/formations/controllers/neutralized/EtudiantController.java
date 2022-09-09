package org.dawan.formations.controllers.neutralized;

import io.micrometer.core.lang.Nullable;
import org.dawan.formations.dtos.EtudiantDto;
import org.dawan.formations.models.Etudiant;
import org.dawan.formations.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.*;

@RestController
@RequestMapping("/api/Etudiant")
public class EtudiantController extends GenericController<Etudiant,EtudiantDto,EtudiantService> {


    @Autowired
    protected EtudiantController(EtudiantService service) {
        super(service);
    }

    @Override
    @PostMapping()
    public ResponseEntity<EtudiantDto> save(@Nullable EtudiantDto uDto) {
        throw new MethodNotAllowedException(HttpMethod.POST, Arrays.asList(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE));
    }


    @GetMapping("{id}/Inscription")
    public String inscription(@PathVariable("id") long id, @RequestParam Long promotion)
    {
        getService().inscription(id,promotion);
        return "Inscrits!";
    }
}
