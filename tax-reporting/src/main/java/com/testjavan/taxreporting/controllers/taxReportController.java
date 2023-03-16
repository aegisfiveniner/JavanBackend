package com.testjavan.taxreporting.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testjavan.taxreporting.dto.TaxReportDto;
import com.testjavan.taxreporting.models.taxReport;
import com.testjavan.taxreporting.repository.statusRepository;
import com.testjavan.taxreporting.repository.taxReportRepository;
import com.testjavan.taxreporting.repository.userRepository;

@RestController
@RequestMapping("/api")
public class taxReportController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
	statusRepository statusRepository;

    @Autowired
	userRepository userRepository;

    @Autowired
	taxReportRepository taxReportRepository;


   
	// @PreAuthorize("hasRole('USER CHECKER')")
    @GetMapping("/tax-report")
	public ResponseEntity<Object> readServices() {
        try {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        
        List<taxReport> listTaxReport = taxReportRepository.findAll();
        List<TaxReportDto> listService = new ArrayList<TaxReportDto>();
        for (taxReport serviceEntity : listTaxReport) {
            TaxReportDto taxReportDto = modelMapper.map(serviceEntity, TaxReportDto.class);
            listService.add(taxReportDto);
        }
        if (listService.isEmpty()) {
            result.put("message", "get all data successfuly, but no data");
            result.put("stastus", "404");
            return new ResponseEntity<>(result, status);
        } else {
            result.put("message", "get all data successfuly");
            result.put("stastus", "200");
            result.put("data", listService);
            result.put("size", listService.size());
            return new ResponseEntity<>(result, status);
        }
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/tax-report/create")
    // @PreAuthorize("hasRole('USER MAKER')")
    public ResponseEntity<Object> saveLaporanPajak(
        @RequestParam(name = "nomorresi") String nomorresi,
        @RequestParam(name = "status") Integer statusid
        ) {
        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus responStatus = HttpStatus.OK;
        String message = "";
        
        Date date = new Date();

        taxReport taxReport = new taxReport();

        taxReport.setNomorresi(nomorresi);
        taxReport.setTanggalpembuatan(date);
        taxReport.setCreatedtime(date);
        taxReport.setLastmodifiedtime(date);
        //masih hardcore
        taxReport.setUser(userRepository.finduser());
        taxReport.setStatus(statusRepository.findStatus(statusid));

        taxReportRepository.save(taxReport);

        TaxReportDto taxReportDto = modelMapper.map(taxReport, TaxReportDto.class);

        result.put("Data", taxReportDto);

        message = "Success create data Vehicle Type";

        result.put("Message", message);
        result.put("Status", responStatus.value());

        return new ResponseEntity<Object>(result, responStatus);
    }

    @PostMapping("/tax-report/approve-reject")
    // @PreAuthorize("hasRole('USER CHECKER')")
    public ResponseEntity<Object> updatetaxReport(
        @RequestParam(name = "taxreportid") Integer taxreportid,
        @RequestParam(name = "status") Integer statusid
        ) {
		
		Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus responseStatus = HttpStatus.OK;
        HttpStatus responseStatusError = HttpStatus.BAD_REQUEST;
        String message = "";

        taxReport taxReport = new taxReport();
        
        if(taxReportRepository.findStatusbyName(statusid) != null){
            taxReport = taxReportRepository.findStatusbyName(statusid);
            if(taxReport.getStatus().getName() == "approved" || taxReport.getStatus().getName() == "rejected"){
                message = "data ini sudah diambil keputusan";
            
                result.put("Status", responseStatusError);
                result.put("Message", message);
                return new ResponseEntity<>(result, responseStatus);
            } else{
                taxReport.setStatus(statusRepository.findStatus(statusid));
                taxReportRepository.save(taxReport);

                message = "Success update data status service";

                result.put("Status", responseStatusError);
                result.put("Message", message);
                return new ResponseEntity<>(result, responseStatus); 
            }
           
        }else{
            message = "Gagal";
            
            result.put("Status", responseStatus);
            result.put("Message", message);
            return new ResponseEntity<>(result, responseStatus);
        }
    }


    @GetMapping("/tax-report/approver")
	// @PreAuthorize("hasRole('USER APPROVER')")
	public ResponseEntity<Object> readtaxApproved() {
        try {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        
        List<taxReport> listTaxReport = taxReportRepository.findStatus();
        List<TaxReportDto> listService = new ArrayList<TaxReportDto>();
        for (taxReport serviceEntity : listTaxReport) {
            TaxReportDto taxReportDto = modelMapper.map(serviceEntity, TaxReportDto.class);
            listService.add(taxReportDto);
        }
        if (listService.isEmpty()) {
            result.put("message", "get all data successfuly, but no data");
            result.put("stastus", "404");
            return new ResponseEntity<>(result, status);
        } else {
            result.put("message", "get all data successfuly");
            result.put("stastus", "200");
            result.put("data", listService);
            result.put("size", listService.size());
            return new ResponseEntity<>(result, status);
        }
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
