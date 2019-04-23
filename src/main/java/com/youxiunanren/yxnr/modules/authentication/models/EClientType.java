package com.youxiunanren.yxnr.modules.authentication.models;

public enum EClientType {
    Application("app"),
    User("user");

    private final String value;

    EClientType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
