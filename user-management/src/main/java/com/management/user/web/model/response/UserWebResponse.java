package com.management.user.web.model.response;

import com.management.user.enums.TaxRole;
import com.management.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWebResponse {

    private Long id;

    private String username;

    private UserRole userRole;

    private TaxRole taxRole;

    private String fullName;

    private String phoneNumber;

    private Long version;

    private String createdBy;

    private Long createdDate;

    private String lastModifiedBy;

    private Long lastModifiedDate;
}
