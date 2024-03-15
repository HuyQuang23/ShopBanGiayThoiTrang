package com.example.duantotnghiep.enums;

public enum StatusProductDetailEnums {

    DA_HET(1),

    NGUNG_BAN(2),

    CON_HANG(3);

    private final int value;

    StatusProductDetailEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
