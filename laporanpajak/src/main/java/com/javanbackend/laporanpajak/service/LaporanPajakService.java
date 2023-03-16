package com.javanbackend.laporanpajak.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javanbackend.laporanpajak.model.LaporanPajak;
import com.javanbackend.laporanpajak.repository.LaporanPajakRepository;
import com.javanbackend.laporanpajak.repository.StatusRepository;
import com.javanbackend.laporanpajak.repository.UserRepository;
import com.javanbackend.laporanpajak.request.LaporanBody;

@Service
public class LaporanPajakService {
    @Autowired
    private LaporanPajakRepository laporanPajakRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    public void getListLaporan(Map<String, Object> result, HttpStatus status) {
        List<LaporanPajak> listLaporanPajak = laporanPajakRepository.findAll();

        result.put("message", "Get list laporan success.");
        result.put("status", status);
        result.put("data", listLaporanPajak);
    }

    @Transactional
    public void changeStatusLaporan(Long laporanId, Integer statusId, Map<String, Object> result, HttpStatus status) {
        LaporanPajak laporanPajak = laporanPajakRepository.getReferenceById(laporanId);
        laporanPajak.setStatus(statusRepository.getReferenceById(statusId));
        laporanPajakRepository.save(laporanPajak);
        result.put("message", "Change status laporan success.");
        result.put("status", status);
        result.put("data", laporanPajak);
    }

    @Transactional
    public void createLaporan(LaporanBody requestBody, Map<String, Object> result, HttpStatus status) {
        LaporanPajak newLaporan = LaporanPajak.builder()
            .nomorResi(requestBody.getNomorResi())
            .status(statusRepository.getReferenceById(requestBody.getStatusId()))
            .submittedBy(userRepository.getReferenceById(requestBody.getUserId()))
        .build();
        LaporanPajak savedLaporan = laporanPajakRepository.save(newLaporan);
        result.put("message", "Create laporan success.");
        result.put("status", status);
        result.put("data", savedLaporan);
    }
}
