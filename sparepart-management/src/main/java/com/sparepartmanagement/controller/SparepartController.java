package com.sparepartmanagement.controller;

import com.sparepartmanagement.entity.Sparepart;
import com.sparepartmanagement.service.SparepartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(
        readOnly = true
)
@RequestMapping({"/sparepart"})
public class SparepartController {
    @Autowired
    private SparepartService sparepartService;

    public SparepartController() {
    }

    @PostMapping
    private ResponseEntity<?> saveSparepart(@RequestBody Sparepart sparepart) {
        sparepart = sparepartService.save(sparepart);
        return ResponseEntity.ok(sparepart);
    }

    @GetMapping
    private ResponseEntity<?> getSparepartList() {
        List<Sparepart> sparepartList = sparepartService.findAll();
        return ResponseEntity.ok(sparepartList);
    }

    @GetMapping({"/detail/{id}"})
    private ResponseEntity<?> getSparepart(@PathVariable Integer id) {
        Sparepart sparepart = sparepartService.findById(id);
        return ResponseEntity.ok(sparepart != null ? sparepart : null);
    }
}