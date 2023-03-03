// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.assetsManagement.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import com.assetsManagement.service.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(readOnly = true)
@RequestMapping({ "/location" })
public class LocationController
{
    @Autowired
    private LocationService locationService;
    
    @PostMapping
    private ResponseEntity<?> saveLocation(@RequestBody Location location) {
        location.setCreatedAt(LocalDateTime.now());
        location = locationService.save(location);
        return ResponseEntity.ok(location);
    }
    
    @GetMapping
    private ResponseEntity<?> getLocationList() {
        List<Location> locationList = locationService.findAll();
        return ResponseEntity.ok(locationList);
    }
    
    @GetMapping({ "/detail/{id}" })
    private ResponseEntity<?> getLocation(@PathVariable final Integer id) {
        Location location = locationService.findById(id);
        return ResponseEntity.ok((location != null) ? location : "Location Not Found");
    }
}
