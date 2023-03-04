package com.javan.server.table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter

public class TableUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 50)
    private String id;

    @Column(columnDefinition = "TEXT")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String email;
    @Column(columnDefinition = "TEXT")
    private String password;
    @Column(columnDefinition = "TEXT")
    private String role;
    @Column(columnDefinition = "TEXT")
    private String taxrole;

    @Column(columnDefinition = "TEXT")
    private String createdBy;
    @Column(columnDefinition = "DATETIME")
    private Date createdAt;
    @Column(columnDefinition = "TEXT")
    private String lastModifiedBy;
    @Column(columnDefinition = "DATETIME")
    private Date lastModifiedAt;
    @Column(length = 11)
    private Integer version = 0;

    public TableUser() {

    }

}
