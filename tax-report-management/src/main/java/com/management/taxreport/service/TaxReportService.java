package com.management.taxreport.service;

import com.management.taxreport.streaming.TaxReportListener;

import java.text.ParseException;

public interface TaxReportService {
    void createTaxReport(TaxReportListener.TaxReportEvent taxReportEvent) throws Exception;

    void updateTaxReport(TaxReportListener.TaxReportEvent taxReportEvent);
}
