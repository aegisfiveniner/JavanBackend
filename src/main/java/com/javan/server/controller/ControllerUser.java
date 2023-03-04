package com.javan.server.controller;

import com.google.gson.Gson;
import com.javan.server.model.body_messages.ModelUserAdmin;
import com.javan.server.model.DataReturn;
import com.javan.server.model.body_messages.ModelUserEditor;
import com.javan.server.model.http.http_response;
import com.javan.server.service.service_implementation.ImplementationUserService;
import com.javan.server.table.TableUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(path = "/api/user")
public class ControllerUser {
    @Autowired
    private ImplementationUserService implementationUserService;

    @PostMapping(path = "/create")
    public ResponseEntity<http_response> create(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelUserAdmin modelUserAdmin = new Gson().fromJson(message, ModelUserAdmin.class);
            TableUser model = modelUserAdmin.getModel();
            TableUser admin = modelUserAdmin.getAdmin();
            DataReturn dataReturn = implementationUserService.userCreate(model, admin);

            if(dataReturn.getStatus()){
                resp.setSuccess();
            }else{
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

            TableUser model = new Gson().fromJson(message, TableUser.class);
            DataReturn dataReturn = implementationUserService.userGet(model);

            if(dataReturn.getStatus()){
                resp.setSuccessWithData(dataReturn.getData());
            }else{
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
    public ResponseEntity<http_response> getAll() {

        http_response resp = new http_response();
        try {

            DataReturn dataReturn = implementationUserService.userGetAll();

            if(dataReturn.getStatus()){
                resp.setSuccessWithData(dataReturn.getData());
            }else{
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());
    }

    @PostMapping(path = "/update")
    public ResponseEntity<http_response> update(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelUserEditor modelUserEditor = new Gson().fromJson(message, ModelUserEditor.class);
            TableUser model = modelUserEditor.getModel();
            TableUser editor = modelUserEditor.getEditor();
            DataReturn dataReturn = implementationUserService.userUpdate(model, editor);

            if(dataReturn.getStatus()){
                resp.setSuccessWithData(dataReturn.getData());
            }else{
                resp.setFail();
                resp.setMessage(dataReturn.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail();
        }
        return new ResponseEntity<>(resp, resp.getStatuscode());

    }

    @PostMapping(path = "/delete")
    public ResponseEntity<http_response> edit(@RequestBody String message) {

        http_response resp = new http_response();
        try {

            ModelUserEditor modelUserEditor = new Gson().fromJson(message, ModelUserEditor.class);
            TableUser model = modelUserEditor.getModel();
            TableUser editor = modelUserEditor.getEditor();
            DataReturn dataReturn = implementationUserService.userDelete(model, editor);

            if(dataReturn.getStatus()){
                resp.setSuccessWithData(dataReturn.getData());
            }else{
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
