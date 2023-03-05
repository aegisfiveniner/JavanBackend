package com.management.user.web.model.request;

import com.management.user.enums.TaxRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserWebRequest {

    private TaxRole taxRole;

    private String fullName;

    private String phoneNumber;
}
