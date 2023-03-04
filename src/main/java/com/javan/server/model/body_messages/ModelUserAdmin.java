package com.javan.server.model.body_messages;

import com.javan.server.table.TableUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelUserAdmin {
    private TableUser model;
    private TableUser admin;
}