// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assetsManagement.config.MQConfig;
import com.assetsManagement.entity.Location;
import com.assetsManagement.repository.LocationRepository;
import com.assetsManagement.service.LocationService;
import com.assetsManagement.service.MQService;
import com.google.gson.Gson;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private MQService mqService;

    public LocationServiceImpl() {
    }

    public Location save(Location location) {
        location.setCreatedAt(location.getCreatedAt() == null ? LocalDateTime.now() : location.getCreatedAt());
        location = locationRepository.save(location);
        mqService.sendMessage(MQConfig.queueSLocation().getName(), (new Gson()).toJson(location));
        mqService.sendMessage(MQConfig.queueCLocation().getName(), (new Gson()).toJson(location));
        return location;
    }

    public Location findById(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.isPresent() ? (Location)location.get() : null;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}