package com.example.duantotnghiep.enums;

public enum StatusProductEnums {

    SU_DUNG(1),

    NGUNG_SU_DUNG(2);

    private final int value;

    StatusProductEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
