package org.dawan.formations.controllers.neutralized;

import org.dawan.formations.dtos.VilleDto;
import org.dawan.formations.models.Ville;
import org.dawan.formations.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/Ville")
public class VilleController extends GenericController<Ville,VilleDto,VilleService> {

    @Autowired
    protected VilleController(VilleService service) {
        super(service);
    }
}
