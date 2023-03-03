package com.sparepartmanagement.service;

import com.sparepartmanagement.entity.Sparepart;
import java.util.List;

public interface SparepartService {
    Sparepart save(Sparepart sparepart);

    List<Sparepart> findAll();

    Sparepart findById(Integer id);
}