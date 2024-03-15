package com.example.duantotnghiep.enums;

public enum HinhThucGiamGiaEnums {

    PHAN_TRAM(1),

    TIEN(2);

    private final int value;

    HinhThucGiamGiaEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
