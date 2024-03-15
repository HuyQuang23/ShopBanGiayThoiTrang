package com.example.duantotnghiep.enums;

public enum TypeAccountEnum {

    MANAGER(1),

    STAFF(2),

    USER(3),

    KHACH_LE(4),

    ADMIN(5);

    private final int value;

    TypeAccountEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
