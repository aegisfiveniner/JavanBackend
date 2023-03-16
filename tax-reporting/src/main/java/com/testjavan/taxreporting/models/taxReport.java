package com.testjavan.taxreporting.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tax_report", schema = "public")
public class taxReport {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taxreportid;

    @Column(name = "nomor_resi", length = 50)
    private String nomorresi;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tanggal_pembuatan", length = 29)
    private Date tanggalpembuatan;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdtime", length = 29)
    private Date createdtime;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastmodifiedtime", length = 29)
    private Date lastmodifiedtime;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statusid")
    private status status;
    
    public taxReport() {
    }

    public taxReport(int taxreportid, String nomorresi, Date tanggalpembuatan, Date createdtime,
            Date lastmodifiedtime) {
        this.taxreportid = taxreportid;
        this.nomorresi = nomorresi;
        this.tanggalpembuatan = tanggalpembuatan;
        this.createdtime = createdtime;
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public taxReport(int taxreportid, String nomorresi, Date tanggalpembuatan, Date createdtime,
            Date lastmodifiedtime, User user, com.testjavan.taxreporting.models.status status) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    
}
