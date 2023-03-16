package com.testjavan.taxreporting.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "status", schema = "public")
public class status {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusid;

    @Column(name = "nama")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdtime", length = 29)
    private Date createdtime;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastmodifiedtime", length = 29)
    private Date lastmodifiedtime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<taxReport> taxReports = new HashSet<taxReport>(0);
    
    public status() {
    }

    public status(int statusid, String name, Date createdtime, Date lastmodifiedtime) {
        this.statusid = statusid;
        this.name = name;
        this.createdtime = createdtime;
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public status(int statusid, String name, Date createdtime, Date lastmodifiedtime, Set<taxReport> taxReports) {
        this.statusid = statusid;
        this.name = name;
        this.createdtime = createdtime;
        this.lastmodifiedtime = lastmodifiedtime;
        this.taxReports = taxReports;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<taxReport> getTaxReports() {
        return taxReports;
    }

    public void setTaxReports(Set<taxReport> taxReports) {
        this.taxReports = taxReports;
    }
    
}
