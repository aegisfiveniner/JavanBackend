package com.maria.taxreportmanagement.tax.model;

import com.maria.taxreportmanagement.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tax_reports")
@Getter
@Setter
public class TaxReport extends BaseModel {

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "receipt_no")
    private String receiptNo;

    @Column(name = "date_created")
    private LocalDate date;

    @Column(name = "status")
    private String status;
}
