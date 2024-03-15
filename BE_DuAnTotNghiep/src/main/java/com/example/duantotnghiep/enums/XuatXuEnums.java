package com.example.duantotnghiep.enums;

public enum XuatXuEnums {

    SU_DUNG(1),

    NGUNG_SU_DUNG(2);

    private final int value;

    XuatXuEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
