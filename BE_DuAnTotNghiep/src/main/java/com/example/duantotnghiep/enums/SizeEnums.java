package com.example.duantotnghiep.enums;

public enum SizeEnums {

    SU_DUNG(1),

    NGUNG_SU_DUNG(2);

    private final int value;

    SizeEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
