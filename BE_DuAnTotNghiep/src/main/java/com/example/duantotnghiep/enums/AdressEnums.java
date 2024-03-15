package com.example.duantotnghiep.enums;

public enum AdressEnums {

    SU_DUNG(1),

    KHONG_SU_DUNG(2);

    private final int value;

    AdressEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
