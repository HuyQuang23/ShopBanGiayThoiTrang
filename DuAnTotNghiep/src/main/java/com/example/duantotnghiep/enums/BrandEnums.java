package com.example.duantotnghiep.enums;

public enum BrandEnums {

    SU_DUNG(1),

    KHONG_SU_DUNG(2);

    private final int value;

    BrandEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
