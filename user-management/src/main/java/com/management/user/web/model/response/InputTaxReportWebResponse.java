package com.management.user.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputTaxReportWebResponse {

    private String username;

    private String receipt;

    private String message;
}
