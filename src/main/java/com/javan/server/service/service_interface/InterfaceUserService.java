package com.javan.server.service.service_interface;

import com.javan.server.model.DataReturn;
import com.javan.server.table.TableUser;

public interface InterfaceUserService {
    DataReturn userCreate(TableUser model, TableUser admin);
    public DataReturn userGet(TableUser model);
    public DataReturn userGetAll();
    public DataReturn userUpdate(TableUser model, TableUser editor);
    public DataReturn userDelete(TableUser model, TableUser editor);
}
