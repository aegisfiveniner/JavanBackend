package com.models.model.taxreport.events;

import com.models.model.taxreport.enums.TaxStatus;
import com.models.model.user.enums.TaxRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxReportEvent {

    private String receipt;

    private TaxStatus taxStatus;

    private Date createdDate;

    private String username;

    private TaxRole taxRole;
}
