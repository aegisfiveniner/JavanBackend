package com.management.taxreport.service.impl;

import com.management.taxreport.entity.TaxReport;
import com.management.taxreport.enums.TaxStatus;
import com.management.taxreport.repository.TaxReportRepository;
import com.management.taxreport.service.TaxReportService;
import com.management.taxreport.streaming.TaxReportListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class TaxReportServiceImpl implements TaxReportService {


    @Autowired
    private TaxReportRepository taxReportRepository;

    @Override
    public void createTaxReport(TaxReportListener.TaxReportEvent taxReportEvent) throws Exception {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(taxReportEvent.getCreatedDate());
            TaxReport taxReport = TaxReport
                    .builder()
                    .receipt(taxReportEvent.getReceipt())
                    .createdBy(taxReportEvent.getUsername())
                    .createdDate(date.getTime())
                    .taxStatus(TaxStatus.valueOf(taxReportEvent.getTaxStatus()))
                    .build();
            taxReportRepository.save(taxReport);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void updateTaxReport(TaxReportListener.TaxReportEvent taxReportEvent) {

        Optional<TaxReport> taxReport = taxReportRepository.findByReceipt(taxReportEvent.getReceipt());
        if (taxReport.isPresent()) {
            TaxReport tr = taxReport.get();
            tr.setLastModifiedBy(taxReportEvent.getUsername());
            tr.setTaxStatus(TaxStatus.valueOf(taxReportEvent.getTaxStatus()));
            taxReportRepository.save(tr);
        }
    }
}
