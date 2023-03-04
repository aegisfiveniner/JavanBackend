package com.javan.server.controller;

import com.google.gson.Gson;
import com.javan.server.model.DataReturn;
import com.javan.server.model.body_messages.ModelTaxApprover;
import com.javan.server.model.body_messages.ModelTaxChecker;
import com.javan.server.model.body_messages.ModelTaxMaker;
import com.javan.server.table.TableTaxReport;
import com.javan.server.table.TableUser;
import com.javan.server.model.http.http_response;
import com.javan.server.service.service_implementation.ImplementationTaxReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(path = "/api/tax")
public class ControllerTax {

    @Autowired
    private ImplementationTaxReportService implementationTaxReportService;

    @PostMapping(path = "/create")
    public ResponseEntity<http_response> create(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelTaxMaker modelUserAdmin = new Gson().fromJson(message, ModelTaxMaker.class);
            TableTaxReport model = modelUserAdmin.getModel();
            TableUser maker = modelUserAdmin.getMaker();
            DataReturn dataReturn = implementationTaxReportService.taxCreate(model, maker);

            if (dataReturn.getStatus()) {
                resp.setSuccess();
            } else {
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());

    }

    @PostMapping(path = "/approve")
    public ResponseEntity<http_response> taxApprove(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelTaxChecker modelUserAdmin = new Gson().fromJson(message, ModelTaxChecker.class);
            TableTaxReport model = modelUserAdmin.getModel();
            TableUser checker = modelUserAdmin.getChecker();
            DataReturn dataReturn = implementationTaxReportService.taxApprove(model, checker);

            if (dataReturn.getStatus()) {
                resp.setSuccess();
            } else {
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());

    }

    @PostMapping(path = "/reject")
    public ResponseEntity<http_response> taxReject(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelTaxChecker modelTaxChecker = new Gson().fromJson(message, ModelTaxChecker.class);
            TableTaxReport model = modelTaxChecker.getModel();
            TableUser checker = modelTaxChecker.getChecker();
            DataReturn dataReturn = implementationTaxReportService.taxReject(model, checker);

            if (dataReturn.getStatus()) {
                resp.setSuccess();
            } else {
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());

    }

    @GetMapping(path = "/get")
    public ResponseEntity<http_response> get(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelTaxApprover modelTaxApprover = new Gson().fromJson(message, ModelTaxApprover.class);
            TableTaxReport model = modelTaxApprover.getModel();
            TableUser approver = modelTaxApprover.getApprover();
            DataReturn dataReturn = implementationTaxReportService.taxApproveGet(model, approver);

            if (dataReturn.getStatus()) {
                resp.setSuccessWithData(dataReturn.getData());
            } else {
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());

    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<http_response> getAll(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelTaxApprover modelTaxApprover = new Gson().fromJson(message, ModelTaxApprover.class);
            TableUser approver = modelTaxApprover.getApprover();
            DataReturn dataReturn = implementationTaxReportService.taxApproveGetAll(approver);

            if (dataReturn.getStatus()) {
                resp.setSuccessWithData(dataReturn.getData());
            } else {
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());
    }

}

