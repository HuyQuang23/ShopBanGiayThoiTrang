package com.example.duantotnghiep.enums;

public enum VoucherEnums {

    CHUA_KICH_HOAT(1),

    DANG_KICH_HOAT(2),

    HET_HAN(3),

    NGHUNG_KICH_HOAT(4);

    private final int value;

    VoucherEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
