package org.dawan.formations.controllers;

import org.dawan.formations.services.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<E,T,S> {

    protected final GenericService<E,T,?> service;

    protected GenericController(GenericService<E,T,?> service) {
        this.service = service;
    }

    @GetMapping()
    public List<T> getAll() {
        return service.GETAll();
    }

    @GetMapping(value = "/{id}")
    public T findById(@PathVariable("id") long id){
        return service.GETById(id);
    }

    @PostMapping()
    public ResponseEntity<T> save(@RequestBody T uDto) {
        T result = service.POSTSave(uDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PutMapping()
    public T update(@RequestBody T uDto) {
        return service.POSTSave(uDto);
    }

    //suppression
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable(name = "id") long id) {
        service.DELETEById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    protected S getService()
    {
        return (S)service;
    }
}
