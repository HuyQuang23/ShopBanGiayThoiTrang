package com.example.duantotnghiep.enums;

public enum TypeTransactionEnums {

    TIEN_MAT(1),

    QR_CODE(2),

    CHUYEN_KHOAN(3);

    private final int value;

    TypeTransactionEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
