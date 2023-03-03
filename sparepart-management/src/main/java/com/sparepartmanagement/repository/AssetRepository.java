package com.sparepartmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparepartmanagement.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
