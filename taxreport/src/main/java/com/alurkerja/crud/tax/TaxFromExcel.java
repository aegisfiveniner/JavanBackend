package com.alurkerja.crud.tax;

import com.alurkerja.core.util.ExcelReader;

public class TaxFromExcel extends ExcelReader<Tax> {
    @Override
    public int skippedRow() {
        return 0;
    }

    @Override
    public Tax perLine(Object[] columns) {
        Tax tax = new Tax();
        return tax;
    }
}
