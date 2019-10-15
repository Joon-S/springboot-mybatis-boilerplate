package com.joons.springboot.controller;

import com.joons.springboot.model.Division;
import com.joons.springboot.service.DivisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class DivisionController {

    private DivisionService divisionService;

    public DivisionController(DivisionService service) {
        this.divisionService = service;
    }

    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> findById(@PathVariable long id) {
        Division division = divisionService.findById(id);
        return ResponseEntity.ok(division);
    }

    @PostMapping("/divisions")
    public ResponseEntity<Void> postDivision(HttpServletRequest request, UriComponentsBuilder builder, @RequestBody Division division) {
        divisionService.save(division);
        URI uri = builder.path(request.getRequestURI() + "/{id}").buildAndExpand(division.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/divisions/{id}")
    public ResponseEntity<Void> updateDivision(@PathVariable long id, @RequestBody Division division) {
        division.setId(id);
        divisionService.update(division);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/divisions/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable long id) {
        divisionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
