package com.configmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.configmanagement.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
