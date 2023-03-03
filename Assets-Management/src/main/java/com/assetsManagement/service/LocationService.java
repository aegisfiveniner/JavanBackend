//
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service;

import com.assetsManagement.entity.Location;
import java.util.List;

public interface LocationService {
    Location save(Location location);

    Location findById(Integer id);

    List<Location> findAll();
}