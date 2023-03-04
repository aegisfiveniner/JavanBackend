package com.javan.server.service.service_implementation;

import com.javan.server.model.DataReturn;
import com.javan.server.service.service_interface.InterfaceUserService;
import com.javan.server.table.TableUser;
import com.javan.server.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImplementationUserService implements InterfaceUserService {
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public DataReturn userCreate(TableUser model, TableUser admin) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkAdmin = repositoryUser.findById(admin.getId());

        if(checkAdmin.isPresent()){

            admin = checkAdmin.get();
            if (admin.getRole().compareTo("ADMIN") == 0) {

                TableUser newUser = new TableUser();
                newUser.setName(model.getName());
                newUser.setEmail(model.getEmail());
                newUser.setPassword(model.getPassword());
                newUser.setRole(model.getRole());
                newUser.setTaxrole(model.getTaxrole());
                newUser.setCreatedBy(admin.getCreatedBy());
                newUser.setCreatedAt(new Date());
                repositoryUser.save(newUser);

                dataReturn.setSuccess();
            }else{
                dataReturn.setFalse("editor is not valid");
            }

        }else{
            dataReturn.setFalse("editor does not exist");
        }

        return dataReturn;
    }

    @Override
    public DataReturn userGet(TableUser model) {
        DataReturn dataReturn = new DataReturn();

        List<TableUser> data = new ArrayList<>();
        Optional<TableUser> checkUser = repositoryUser.findById(model.getId());

        if (checkUser.isPresent()) {
            data.add(checkUser.get());
            dataReturn.setSuccess(data);
        }else{
            dataReturn.setFalse("user does not exist");
        }
        return dataReturn;
    }

    @Override
    public DataReturn userGetAll() {
        DataReturn dataReturn = new DataReturn();

        List<TableUser> data = repositoryUser.findAll();
        dataReturn.setSuccess(data);

        return dataReturn;
    }

    @Override
    public DataReturn userUpdate(TableUser model, TableUser editor) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkEditor = repositoryUser.findById(editor.getId());

        if (checkEditor.isPresent()) {

            editor = checkEditor.get();
            Optional<TableUser> checkUser = repositoryUser.findById(model.getId());

            if (checkUser.isPresent()) {

                TableUser user = checkUser.get();

                if (editor.getRole().compareTo("ADMIN") == 0 || editor.getId().compareTo(model.getId()) == 0) {

                    if (user.getVersion().compareTo(model.getVersion()) == 0) {

                        user.setName(model.getName());
                        user.setEmail(model.getEmail());
                        user.setPassword(model.getPassword());
                        if (editor.getRole().compareTo("ADMIN") == 0) {
                            user.setRole(model.getRole());
                            user.setTaxrole(model.getTaxrole());
                        }
                        user.setLastModifiedBy(editor.getId());
                        user.setLastModifiedAt(new Date());
                        user.setVersion(user.getVersion() + 1);
                        repositoryUser.save(user);
                        dataReturn.setSuccess();

                    } else {
                        dataReturn.setFalse("version is not match");
                    }

                } else {
                    dataReturn.setFalse("editor is not valid");
                }

            } else {
                dataReturn.setFalse("user does not exist");
            }

        } else {
            dataReturn.setFalse("editor does not exist");
        }

        return  dataReturn;

    }

    @Override
    public DataReturn userDelete(TableUser model, TableUser editor) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkEditor = repositoryUser.findById(editor.getId());

        if (checkEditor.isPresent()) {

            editor = checkEditor.get();
            Optional<TableUser> checkUser = repositoryUser.findById(model.getId());

            if (checkUser.isPresent()) {

                TableUser user = checkUser.get();

                if (editor.getRole().compareTo("ADMIN") == 0 || editor.getId().compareTo(model.getId()) == 0) {

                    if (user.getVersion().compareTo(model.getVersion()) == 0) {

                        repositoryUser.delete(user);
                        dataReturn.setSuccess();

                    } else {
                        dataReturn.setFalse("version is not match");
                    }

                } else {
                    dataReturn.setFalse("editor is not valid");
                }

            } else {
                dataReturn.setFalse("user does not exist");
            }

        } else {
            dataReturn.setFalse("editor does not exist");
        }

        return  dataReturn;

    }

}
