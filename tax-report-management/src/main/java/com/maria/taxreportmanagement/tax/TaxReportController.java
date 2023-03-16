package com.maria.taxreportmanagement.tax;

import com.maria.taxreportmanagement.base.BaseResponse;
import com.maria.taxreportmanagement.tax.dto.TaxReportRequestDto;
import com.maria.taxreportmanagement.tax.dto.TaxReportResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax-report")
public class TaxReportController {

    @Autowired
    private TaxReportService service;

    @PreAuthorize("hasAnyRole ('ROLE_USER_CHECKER', 'ROLE_USER_APPROVER')")
    @GetMapping
    public ResponseEntity<?> getTaxReports(Pageable pageable) {
        BaseResponse<Page<TaxReportResponseDto>> response = new BaseResponse<>(HttpStatus.OK, service.getTaxReports(pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_USER_MAKER')")
    @PostMapping
    public ResponseEntity<?> createTaxReport(@RequestBody TaxReportRequestDto request) {
        service.createTaxReport(request);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "Tax report created");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_USER_CHECKER', 'ROLE_USER_APPROVER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaxReportById(@PathVariable String id) {
        BaseResponse<TaxReportResponseDto> response = new BaseResponse<>(HttpStatus.OK, service.getTaxReportById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_USER_CHECKER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> changeStatusTaxReport(@RequestBody TaxReportRequestDto request, @PathVariable String id) {
        service.changeStatusTaxReport(request, id);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "Tax report status changed");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
