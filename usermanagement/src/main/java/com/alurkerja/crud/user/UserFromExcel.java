package com.alurkerja.crud.user;

import com.alurkerja.core.util.ExcelReader;

public class UserFromExcel extends ExcelReader<User> {
    @Override
    public int skippedRow() {
        return 0;
    }

    @Override
    public User perLine(Object[] columns) {
        User user = new User();
        return user;
    }
}
