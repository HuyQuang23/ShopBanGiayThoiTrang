package com.example.duantotnghiep.enums;

public enum TypeOrderEnums {

    ONLINE(1),

    TAI_QUAY(2);

    private final int value;

    TypeOrderEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
