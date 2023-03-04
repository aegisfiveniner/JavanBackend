package com.javan.server.service.service_interface;

import com.javan.server.model.DataReturn;
import com.javan.server.table.TableTaxReport;
import com.javan.server.table.TableUser;

public interface InterfaceTaxReportService {
    public DataReturn taxCreate(TableTaxReport model, TableUser maker);
    public DataReturn taxApprove(TableTaxReport model, TableUser checker);
    public DataReturn taxReject(TableTaxReport model, TableUser checker);
    public DataReturn taxApproveGet(TableTaxReport model, TableUser approver);
    public DataReturn taxApproveGetAll(TableUser approver);
}