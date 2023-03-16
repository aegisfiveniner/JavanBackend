package com.testjavan.taxreporting.dto;

import java.util.Date;

public class TaxReportDto {
    private int taxreportid;
    private String nomorresi;
    private Date tanggalpembuatan;
    private Date createdtime;
    private Date lastmodifiedtime;
    private userDto user;
    private StatusDto status;
    public TaxReportDto() {
    }
    public TaxReportDto(int taxreportid, String nomorresi, Date tanggalpembuatan, Date createdtime,
            Date lastmodifiedtime, userDto user, StatusDto status) {
        this.taxreportid = taxreportid;
        this.nomorresi = nomorresi;
        this.tanggalpembuatan = tanggalpembuatan;
        this.createdtime = createdtime;
        this.lastmodifiedtime = lastmodifiedtime;
        this.user = user;
        this.status = status;
    }
    public int getTaxreportid() {
        return taxreportid;
    }
    public void setTaxreportid(int taxreportid) {
        this.taxreportid = taxreportid;
    }
    public String getNomorresi() {
        return nomorresi;
    }
    public void setNomorresi(String nomorresi) {
        this.nomorresi = nomorresi;
    }
    public Date getTanggalpembuatan() {
        return tanggalpembuatan;
    }
    public void setTanggalpembuatan(Date tanggalpembuatan) {
        this.tanggalpembuatan = tanggalpembuatan;
    }
    public Date getCreatedtime() {
        return createdtime;
    }
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }
    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }
    public userDto getUser() {
        return user;
    }
    public void setUser(userDto user) {
        this.user = user;
    }
    public StatusDto getStatus() {
        return status;
    }
    public void setStatus(StatusDto status) {
        this.status = status;
    }

    
}
