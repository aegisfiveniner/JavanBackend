package com.javanbackend.laporanpajak.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laporan_pajak", schema = "public")
@EntityListeners({ AuditingEntityListener.class })
public class LaporanPajak {
    @Id
    @SequenceGenerator(name = "generator_laporan", schema = "public", sequenceName = "laporan_id_laporan_seq", allocationSize = 1)
    @GeneratedValue(generator = "generator_laporan", strategy = GenerationType.SEQUENCE)
    @Column(name = "laporan_id", nullable = false, unique = true, updatable = false)
    private Long laporanId;
    @Column(name = "nomor_resi")
    private String nomorResi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User submittedBy;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", length = 29)
    private Date createdTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time", length = 29)
    private Date lastModifiedTime;
}
