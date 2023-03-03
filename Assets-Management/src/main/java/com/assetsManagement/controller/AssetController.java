package com.assetsManagement.controller;

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

import com.assetsManagement.entity.Asset;
import com.assetsManagement.entity.model.AssetModel;
import com.assetsManagement.service.AssetService;

@RestController
@Transactional(readOnly = false)
@RequestMapping({ "/asset" })
public class AssetController
{
    @Autowired
    private AssetService assetService;
    
    @PostMapping
    private ResponseEntity<?> saveAsset(@RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.save(asset));
    }
    
    @GetMapping
    private ResponseEntity<?> getAssetList() {
    	List<AssetModel> assetModelList = assetService.findAll();
        return ResponseEntity.ok(assetModelList);
    }
    
    @GetMapping({ "/detail/{id}" })
    private ResponseEntity<?> getAsset(@PathVariable final Integer id) {
        return ResponseEntity.ok(assetService.findById(id));
    }
}
