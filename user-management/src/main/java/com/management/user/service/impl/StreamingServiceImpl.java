package com.management.user.service.impl;

import com.google.gson.Gson;
import com.management.user.authentication.JwtAuthentication;
import com.management.user.service.StreamingService;
import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.UpdateTaxReportWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.UpdateTaxReportWebResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StreamingServiceImpl implements StreamingService {

    @Autowired
    private JwtAuthentication jwtAuthentication;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public StreamingServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Response<InputTaxReportWebResponse> inputTaxReport(String token, InputTaxReportWebRequest webRequest) {
        String username = jwtAuthentication.claimTokenUsername(token);
        TaxReportEvent taxReportEvent = TaxReportEvent.builder()
                .receipt(webRequest.getReceipt())
                .taxStatus("CREATED")
                .username(username)
                .taxRole("MAKER")
                .createdDate(LocalDateTime.now().toLocalDate().toString())
                .build();
        kafkaTemplate.send("tax-report", (new Gson().toJson(taxReportEvent)));
        return Response.<InputTaxReportWebResponse>builder().data(toWebResponse(username, webRequest.getReceipt())).build();
    }

    @Override
    public Response<UpdateTaxReportWebResponse> updateTaxReport(String token, UpdateTaxReportWebRequest webRequest) {
        String username = jwtAuthentication.claimTokenUsername(token);
        TaxReportEvent taxReportEvent = TaxReportEvent.builder()
                .receipt(webRequest.getReceipt())
                .taxStatus(webRequest.getTaxStatus())
                .username(username)
                .taxRole("APPROVER")
                .createdDate(LocalDateTime.now().toLocalDate().toString())
                .build();
        kafkaTemplate.send("tax-report", (new Gson().toJson(taxReportEvent)));
        return Response.<UpdateTaxReportWebResponse>builder().data(toWebResponse(username, webRequest.getReceipt(), webRequest.getTaxStatus())).build();
    }

    private InputTaxReportWebResponse toWebResponse(String username, String receipt){
        return InputTaxReportWebResponse.builder()
                .username(username)
                .receipt(receipt)
                .message("Tax Report Event successfully sent!")
                .build();
    }

    private UpdateTaxReportWebResponse toWebResponse(String username, String receipt, String taxStatus){
        return UpdateTaxReportWebResponse.builder()
                .username(username)
                .receipt(receipt)
                .taxStatus(taxStatus)
                .message("Tax Report Event successfully sent!")
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaxReportEvent {

        private String receipt;

        private String taxStatus;

        private String createdDate;

        private String username;

        private String taxRole;
    }
}
