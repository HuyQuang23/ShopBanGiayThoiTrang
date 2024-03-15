package com.example.duantotnghiep.enums;

public enum StatusCartEnums {

    CHUA_CO_SAN_PHAM(1),

    DANG_CO_SAN_PHAM(2),

    DA_THANH_TOAN(3),

    HET_HAN(4),

    DA_HUY(5);

    private final int value;

    StatusCartEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
