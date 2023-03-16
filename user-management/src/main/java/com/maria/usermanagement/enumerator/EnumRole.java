package com.maria.usermanagement.enumerator;

import java.util.Arrays;
import java.util.Optional;

public enum EnumRole {

    USER_MAKER("USER_MAKER"),
    USER_CHECKER("USER_CHECKER"),
    USER_APPROVER("USER_APPROVER"),
    ADMIN("ADMIN");

    private String text;

    EnumRole(String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }

    public static Optional<EnumRole> getEnumRole(String text) {
        return Arrays.stream(values()).filter(x -> x.text.equalsIgnoreCase(text)).findFirst();
    }
}
