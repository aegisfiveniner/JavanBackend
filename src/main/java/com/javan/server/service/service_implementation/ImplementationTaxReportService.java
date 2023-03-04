package com.javan.server.service.service_implementation;

import com.javan.server.model.DataReturn;
import com.javan.server.table.TableTaxReport;
import com.javan.server.table.TableUser;
import com.javan.server.repository.RepositoryTaxReport;
import com.javan.server.repository.RepositoryUser;
import com.javan.server.service.service_interface.InterfaceTaxReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImplementationTaxReportService implements InterfaceTaxReportService {
    @Autowired
    private RepositoryTaxReport repositoryTaxReport;
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public DataReturn taxCreate(TableTaxReport model, TableUser maker) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkMaker = repositoryUser.findById(maker.getId());

        if (checkMaker.isPresent()) {

            maker = checkMaker.get();

            if (maker.getTaxrole().compareTo("MAKER") == 0) {

                TableTaxReport newTaxReport = new TableTaxReport();
                Calendar cal = Calendar.getInstance();
                newTaxReport.setNumber(cal.get(cal.MONTH) + "-" + cal.get(cal.YEAR) + "-" + cal.get(cal.HOUR) + "-" + cal.get(cal.MINUTE) + "-" + cal.get(cal.SECOND) + "-" + cal.get(cal.MILLISECOND));
                newTaxReport.setStatus("DRAFT");
                newTaxReport.setCreatedBy(maker.getCreatedBy());
                newTaxReport.setCreatedAt(new Date());
                repositoryTaxReport.save(newTaxReport);

                dataReturn.setSuccess();
            } else {
                dataReturn.setFalse("maker is not valid");
            }

        } else {
            dataReturn.setFalse("maker does not exist");
        }

        return dataReturn;

    }

    @Override
    public DataReturn taxApprove(TableTaxReport model, TableUser checker) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkChecker = repositoryUser.findById(checker.getId());

        if (checkChecker.isPresent()) {

            checker = checkChecker.get();
            Optional<TableTaxReport> checkTaxReport = repositoryTaxReport.findById(model.getId());

            if (checkTaxReport.isPresent()) {

                TableTaxReport tax = checkTaxReport.get();

                if (checker.getTaxrole().compareTo("CHECKER") == 0) {

                    if (tax.getVersion().compareTo(model.getVersion()) == 0 && tax.getStatus().compareTo("DRAFT") == 0) {

                        tax.setStatus("APPROVED");
                        tax.setLastModifiedAt(new Date());
                        tax.setLastModifiedBy(checker.getId());
                        tax.setVersion(tax.getVersion()+1);
                        repositoryTaxReport.save(tax);
                        dataReturn.setSuccess();

                    } else {
                        dataReturn.setFalse("version is not match or status is not valid");
                    }

                } else {
                    dataReturn.setFalse("checker is not valid");
                }

            } else {
                dataReturn.setFalse("tax report does not exist");
            }

        } else {
            dataReturn.setFalse("checker does not exist");
        }

        return dataReturn;

    }

    @Override
    public DataReturn taxReject(TableTaxReport model, TableUser checker) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkChecker = repositoryUser.findById(checker.getId());

        if (checkChecker.isPresent()) {

            checker = checkChecker.get();
            Optional<TableTaxReport> checkTaxReport = repositoryTaxReport.findById(model.getId());

            if (checkTaxReport.isPresent()) {

                TableTaxReport tax = checkTaxReport.get();

                if (checker.getTaxrole().compareTo("CHECKER") == 0) {

                    if (tax.getVersion().compareTo(model.getVersion()) == 0 && tax.getStatus().compareTo("DRAFT") == 0) {

                        tax.setStatus("REJECTED");
                        tax.setLastModifiedAt(new Date());
                        tax.setLastModifiedBy(checker.getId());
                        tax.setVersion(tax.getVersion()+1);
                        repositoryTaxReport.save(tax);
                        dataReturn.setSuccess();

                    } else {
                        dataReturn.setFalse("version is not match or status is not valid");
                    }

                } else {
                    dataReturn.setFalse("checker is not valid");
                }

            } else {
                dataReturn.setFalse("tax report does not exist");
            }

        } else {
            dataReturn.setFalse("checker does not exist");
        }

        return dataReturn;

    }

    @Override
    public DataReturn taxApproveGet(TableTaxReport model, TableUser approver) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkApprover = repositoryUser.findById(approver.getId());

        if (checkApprover.isPresent()) {

            approver = checkApprover.get();

            if (approver.getTaxrole().compareTo("APPROVER") == 0) {

                List<TableTaxReport> data = new ArrayList<>();
                Optional<TableTaxReport> checkTax = repositoryTaxReport.findById(model.getId());
                if (checkTax.isPresent()) {
                    data.add(checkTax.get());
                    dataReturn.setSuccess(data);
                } else {
                    dataReturn.setFalse("tax report does not exist");
                }

            } else {
                dataReturn.setFalse("approver is not valid");
            }

        } else {
            dataReturn.setFalse("approver does not exist");
        }

        return dataReturn;

    }

    @Override
    public DataReturn taxApproveGetAll(TableUser approver) {
        DataReturn dataReturn = new DataReturn();

        Optional<TableUser> checkApprover = repositoryUser.findById(approver.getId());

        if (checkApprover.isPresent()) {

            approver = checkApprover.get();

            if (approver.getTaxrole().compareTo("APPROVER") == 0) {

                List<TableTaxReport> data = new ArrayList<>();
                List<TableTaxReport> taxes = repositoryTaxReport.findAll();
                for (TableTaxReport tax : taxes) {
                    if (tax.getStatus().compareTo("APPROVED") == 0) {
                        data.add(tax);
                    }
                }
                dataReturn.setSuccess(data);

            } else {
                dataReturn.setFalse("approver is not valid");
            }

        } else {
            dataReturn.setFalse("approver does not exist");
        }

        return dataReturn;

    }
}
