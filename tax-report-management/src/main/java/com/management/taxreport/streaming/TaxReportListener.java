package com.management.taxreport.streaming;

import com.google.gson.Gson;
import com.management.taxreport.enums.TaxStatus;
import com.management.taxreport.service.TaxReportService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static com.management.taxreport.enums.TaxStatus.CREATED;

@Component
public class TaxReportListener {

    @Autowired
    private TaxReportService taxReportService;


    @KafkaListener(
            topics ="tax-report",
            groupId = "group-id-1"
    )
    void listeners(String data) throws Exception {
        Gson gson = new Gson();
        TaxReportEvent taxReportEvent = gson.fromJson(data, TaxReportEvent.class);
        switch (TaxStatus.valueOf(taxReportEvent.getTaxStatus())){
            case CREATED:
                taxReportService.createTaxReport(taxReportEvent);
                break;
            case APPROVED:
            case REJECTED:
                taxReportService.updateTaxReport(taxReportEvent);
                break;
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaxReportEvent {

        private String receipt;

        private String taxStatus;

        private String createdDate;

        private String username;

        private String taxRole;
    }
}
