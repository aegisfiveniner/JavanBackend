package com.management.user.service;

import com.management.user.web.model.request.InputTaxReportWebRequest;
import com.management.user.web.model.request.UpdateTaxReportWebRequest;
import com.management.user.web.model.response.InputTaxReportWebResponse;
import com.management.user.web.model.response.Response;
import com.management.user.web.model.response.UpdateTaxReportWebResponse;

public interface StreamingService {
    Response<InputTaxReportWebResponse> inputTaxReport(String token, InputTaxReportWebRequest webRequest);

    Response<UpdateTaxReportWebResponse> updateTaxReport(String token, UpdateTaxReportWebRequest webRequest);
}
