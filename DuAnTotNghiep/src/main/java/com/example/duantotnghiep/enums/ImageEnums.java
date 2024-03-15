package com.example.duantotnghiep.enums;

public enum ImageEnums {

    SU_DUNG(1),

    NGUNG_SU_DUNG(2);

    private final int value;

    ImageEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
