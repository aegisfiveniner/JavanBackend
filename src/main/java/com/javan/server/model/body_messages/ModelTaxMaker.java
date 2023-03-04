package com.javan.server.model.body_messages;

import com.javan.server.table.TableTaxReport;
import com.javan.server.table.TableUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelTaxMaker {
    private TableTaxReport model;
    private TableUser maker;
}