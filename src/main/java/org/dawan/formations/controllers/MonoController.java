package org.dawan.formations.controllers;

import org.dawan.formations.services.HandlerService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
public class MonoController {

    private final HandlerService service;

    @Autowired
    public MonoController(HandlerService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index() {
        return service.index();
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},
            path = "/{service}/{method}")
    public ResponseEntity<Object> handle(HttpServletRequest request,
                                         @PathVariable Map<String, String> pathArgs,
                                         @RequestParam(required = false) Map<String,String> reqArgs,
                                         @RequestBody(required = false) String body) {
        try
        {
            return getResponse(HttpStatus.OK,service.handle(request.getMethod(),pathArgs,reqArgs,body));

        } catch (NoSuchBeanDefinitionException nsbde) {
            return getResponse(HttpStatus.BAD_REQUEST,"Service Not Found");
        } catch (NoSuchMethodException nsme) {
            return getResponse(HttpStatus.BAD_REQUEST,"Method Not Found");
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            return getResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    private ResponseEntity<Object> getResponse(HttpStatus status, Object o) {
        return ResponseEntity.status(status).body(o);
    }

}
