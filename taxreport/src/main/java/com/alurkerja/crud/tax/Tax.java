package com.alurkerja.crud.tax;

import com.alurkerja.core.entity.CrudEntity;
import com.alurkerja.crud.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity
@Getter
@Setter
public class Tax extends CrudEntity {
    private String receiptNo;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
