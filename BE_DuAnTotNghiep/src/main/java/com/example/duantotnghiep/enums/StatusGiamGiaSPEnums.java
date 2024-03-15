package com.example.duantotnghiep.enums;

public enum StatusGiamGiaSPEnums {

    CHUA_AP_DUNG(1),

    DANG_AP_DUNG(2),

    HET_HAN(3),

    DA_HUY(4);

    private final int value;

    StatusGiamGiaSPEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
