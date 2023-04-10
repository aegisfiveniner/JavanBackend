package com.alurkerja.crud.tax;

import com.alurkerja.core.util.ExcelWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

public class TaxToExcel extends ExcelWriter<Tax> {
    public TaxToExcel(List<Tax> categories) {
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
    public void getRows(Row row, Tax entity) {
    }
}
