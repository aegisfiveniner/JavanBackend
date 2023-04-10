package com.alurkerja.crud.tax;

import com.alurkerja.crud.user.User;
import com.alurkerja.spec.entity.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class TaxDto extends BaseDto<Tax, TaxDto> {
    private String userEmail;
    private String receiptNo;
    private String status;
    protected Date createdDate;
    protected Date updatedDate;
    protected String createdBy;
    protected String updatedBy;
}
