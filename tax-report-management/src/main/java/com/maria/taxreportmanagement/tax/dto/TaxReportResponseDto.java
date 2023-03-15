package com.maria.taxreportmanagement.tax.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxReportResponseDto {

    private String id;
    private String receiptNo;
    private String date;
    private String status;
}
