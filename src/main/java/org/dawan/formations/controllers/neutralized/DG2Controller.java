package org.dawan.formations.controllers.neutralized;

import org.dawan.formations.services.DG2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/Dg2")
public class DG2Controller {

    private final DG2Service service;

    @Autowired
    protected DG2Controller(DG2Service service) {
        this.service = service;
    }

    @GetMapping("/Update")
    public String updateTitreFromDg2() {
        return service.GETformations(false);
    }

    @GetMapping("/ForceUpdate")
    public String forceUpdateTitreFromDg2() {
        return service.GETformations(true);
    }
}
