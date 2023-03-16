package com.javanbackend.laporanpajak.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanbackend.laporanpajak.repository.LaporanPajakRepository;
import com.javanbackend.laporanpajak.repository.StatusRepository;
import com.javanbackend.laporanpajak.repository.UserRepository;
import com.javanbackend.laporanpajak.request.LaporanBody;
import com.javanbackend.laporanpajak.service.LaporanPajakService;

@RestController
@RequestMapping("/api/laporanpajak")
public class LaporanPajakController {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LaporanPajakRepository laporanPajakRepository;

    @Autowired
    private LaporanPajakService laporanPajakService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list-laporan")
    public ResponseEntity<Object> getListLaporan() {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        laporanPajakService.getListLaporan(result, status);

        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/{laporanId}")
    public ResponseEntity<Object> changeStatusLaporan(@PathVariable(name = "laporanId") Long laporanId, @RequestParam(name = "statusId") Integer statusId) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        if (!statusRepository.existsById(statusId)) throw new EntityNotFoundException("Status with id " + statusId + " not found.");
        if (!laporanPajakRepository.existsById(laporanId)) throw new EntityNotFoundException("Laporan with id " + laporanId + " not found.");

        laporanPajakService.changeStatusLaporan(laporanId, statusId, result, status);

        return new ResponseEntity<>(result, status);
    }

    @PostMapping
    public ResponseEntity<Object> createLaporan(@RequestBody LaporanBody requestBody) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.OK;

        if (!userRepository.existsById(requestBody.getUserId())) throw new EntityNotFoundException("User with id " +requestBody.getUserId()+ " not found.");
        if (!statusRepository.existsById(requestBody.getStatusId())) throw new EntityNotFoundException("Status with id " + requestBody.getStatusId() + " not found.");
        if (!userRepository.getReferenceById(requestBody.getUserId()).getUserRole().equalsIgnoreCase("MAKER")) {
            status = HttpStatus.BAD_REQUEST;
            result.put("message", "Create laporan fail. Laporan must be created by MAKER");
            result.put("status", status);
            return new ResponseEntity<>(result, status);
        }

        laporanPajakService.createLaporan(requestBody, result, status);

        return new ResponseEntity<>(result, status);
    }
}
