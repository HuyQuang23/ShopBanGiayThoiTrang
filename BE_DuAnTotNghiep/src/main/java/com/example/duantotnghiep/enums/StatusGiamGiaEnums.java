package com.example.duantotnghiep.enums;

public enum StatusGiamGiaEnums {

    CHUA_KICH_HOAT(1),

    DANG_KICH_HOAT(2),

    HET_HAN(3);

    private final int value;

    StatusGiamGiaEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
