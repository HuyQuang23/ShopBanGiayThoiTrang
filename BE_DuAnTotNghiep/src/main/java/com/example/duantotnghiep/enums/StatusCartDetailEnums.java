package com.example.duantotnghiep.enums;

public enum StatusCartDetailEnums {

    DANG_CO_SAN_PHAM(1),

    DA_THANH_TOAN(2),

    HET_HAN(3),

    DA_HUY(4);

    private final int value;

    StatusCartDetailEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
