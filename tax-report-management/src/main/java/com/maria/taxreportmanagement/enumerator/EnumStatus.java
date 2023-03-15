package com.maria.taxreportmanagement.enumerator;

import java.util.Arrays;
import java.util.Optional;

public enum EnumStatus {

    SUBMITTED("SUBMITTED"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private String text;

    EnumStatus(String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }

    public static Optional<EnumStatus> getEnumStatus(String text) {
        return Arrays.stream(values()).filter(x -> x.text.equalsIgnoreCase(text)).findFirst();
    }
}
