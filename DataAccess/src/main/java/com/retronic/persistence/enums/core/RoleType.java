package com.retronic.persistence.enums.core;

public enum RoleType {

    SYSTEM(100, "System"),
    ADMIN(80, "Admin"),
    OPERATOR(60, "Operator"),
    ADVANCED(40, "Advanced"),
    BASE(20, "Base");

    private final int access;
    private final String name;

    RoleType(int access, String name) {
        this.access = access;
        this.name = name;
    }

    public int getAccess() {
        return access;
    }

    public String getName() {
        return name;
    }
}
