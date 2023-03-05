package com.management.user.web.controller;

import com.management.user.enums.TaxRole;
import com.management.user.service.StreamingService;
import com.management.user.validation.UserTaxRoleMustMatch;
import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.UpdateTaxReportWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.UpdateTaxReportWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/streaming")
@Validated
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @PostMapping(
            path = "/tax-report/_input",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<InputTaxReportWebResponse>> inputTaxReport(@RequestHeader @UserTaxRoleMustMatch(message = "Unauthorized", propName = "token", taxRole = TaxRole.MAKER) String token,
                                                                              @RequestBody @Valid InputTaxReportWebRequest webRequest) {

        Response<InputTaxReportWebResponse> userWebResponse = streamingService.inputTaxReport(token, webRequest);

        return new ResponseEntity<>(userWebResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(
            path = "/tax-report/_update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<UpdateTaxReportWebResponse>> updateTaxReport(@RequestHeader @UserTaxRoleMustMatch(message = "Unauthorized", propName = "token", taxRole = TaxRole.APPROVER) String token,
                                                                                @RequestBody @Valid UpdateTaxReportWebRequest webRequest) {

        Response<UpdateTaxReportWebResponse> userWebResponse = streamingService.updateTaxReport(token, webRequest);

        return new ResponseEntity<>(userWebResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
