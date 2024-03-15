package com.example.duantotnghiep.enums;

public enum TransactionEnums {

    ONLINE(1),

    TIEN_MAT(2),

    TIEN_MAT_AND_ONLINE(3);

    private final int value;

    TransactionEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
