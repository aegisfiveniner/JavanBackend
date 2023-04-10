package com.alurkerja.crud.user;

import com.alurkerja.core.util.ExcelWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

public class UserToExcel extends ExcelWriter<User> {
    public UserToExcel(List<User> categories) {
        super(categories);
    }

    @Override
    public List<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Name");
        headers.add("Tag");
        return headers;
    }

    @Override
    public void getRows(Row row, User entity) {
    }
}
