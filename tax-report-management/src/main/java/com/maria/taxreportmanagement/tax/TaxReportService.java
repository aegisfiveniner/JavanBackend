package com.maria.taxreportmanagement.tax;

import com.maria.taxreportmanagement.enumerator.EnumStatus;
import com.maria.taxreportmanagement.exception.Exception;
import com.maria.taxreportmanagement.security.UserInfo;
import com.maria.taxreportmanagement.tax.dto.TaxReportRequestDto;
import com.maria.taxreportmanagement.tax.dto.TaxReportResponseDto;
import com.maria.taxreportmanagement.tax.model.TaxReport;
import com.maria.taxreportmanagement.tax.repository.TaxReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class TaxReportService {

    @Autowired
    private TaxReportRepository taxReportRepository;

    @Autowired
    private UserInfo userInfo;

    public Page<TaxReportResponseDto> getTaxReports(Pageable pageable) {
        String status = null;
        if (userInfo.getRole().equals("ROLE_USER_CHECKER")) {
            status = EnumStatus.SUBMITTED.getValue();
        } else {
            status = EnumStatus.APPROVED.getValue();
        }

        Page<TaxReport> taxReportPage = taxReportRepository.findByStatus(status, pageable);
        return taxReportPage.map(this::convertEntityToDto);
    }

    @Transactional(rollbackOn = java.lang.Exception.class)
    public void createTaxReport(TaxReportRequestDto request) {
        TaxReport newTaxReport = new TaxReport();
        newTaxReport.setReceiptNo(request.getReceiptNo());
        newTaxReport.setDate(LocalDate.now());
        newTaxReport.setStatus(EnumStatus.SUBMITTED.getValue());
        taxReportRepository.save(newTaxReport);
    }

    public TaxReportResponseDto getTaxReportById(String id) {
        TaxReport taxReport = taxReportRepository.findById(id).orElseThrow(() -> new Exception("Tax report not found"));
        if (userInfo.getRole().equals("ROLE_USER_CHECKER")) {
            if (!taxReport.getStatus().equals(EnumStatus.SUBMITTED.getValue())) {
                throw new Exception("Can not view tax report");
            }
        } else {
            if (!taxReport.getStatus().equals(EnumStatus.APPROVED.getValue())) {
                throw new Exception("Can not view tax report");
            }
        }

        return convertEntityToDto(taxReport);
    }

    @Transactional(rollbackOn = java.lang.Exception.class)
    public void changeStatusTaxReport(TaxReportRequestDto request, String id) {
        TaxReport taxReport = taxReportRepository.findById(id).orElseThrow(() -> new Exception("Tax report not found"));
        EnumStatus status = EnumStatus.getEnumStatus(request.getStatus()).orElseThrow(() -> new Exception("Status not found"));

        if (status.equals(EnumStatus.APPROVED) || status.equals(EnumStatus.REJECTED)) {
            taxReport.setStatus(status.getValue());
            taxReportRepository.save(taxReport);
        } else {
            throw new Exception("Status must be APPROVED or REJECTED");
        }
    }

    private TaxReportResponseDto convertEntityToDto(TaxReport tax) {
        TaxReportResponseDto response = new TaxReportResponseDto();
        response.setId(tax.getId());
        response.setReceiptNo(tax.getReceiptNo());
        response.setDate(tax.getDate().toString());
        response.setStatus(tax.getStatus());
        return response;
    }
}
