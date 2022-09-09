package org.dawan.formations.controllers.neutralized;

import org.dawan.formations.dtos.TitreProfessionnelDto;
import org.dawan.formations.models.TitreProfessionnel;
import org.dawan.formations.services.TitreProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/TitreProfessionnel")
public class TitreProfessionnelController extends GenericController<TitreProfessionnel,TitreProfessionnelDto,TitreProfessionnelService> {


    @Autowired
    protected TitreProfessionnelController(TitreProfessionnelService service) {
        super(service);
    }
}
