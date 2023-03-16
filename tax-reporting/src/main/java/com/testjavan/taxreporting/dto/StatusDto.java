package com.testjavan.taxreporting.dto;

import java.util.Date;
import java.util.Set;

public class StatusDto {
    private int statusid;
    private String name;
    private Date createdtime;
    private Date lastmodifiedtime;
    
    public StatusDto() {
    }

    public StatusDto(int statusid, String name, Date createdtime, Date lastmodifiedtime) {
        this.statusid = statusid;
        this.name = name;
        this.createdtime = createdtime;
        this.lastmodifiedtime = lastmodifiedtime;
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

    
}
