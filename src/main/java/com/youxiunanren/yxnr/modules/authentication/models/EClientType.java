package com.youxiunanren.yxnr.modules.authentication.models;

public enum EClientType {
    Application("app"),
    User("user");

    private final String value;

    EClientType(String value) {
        this.value = value;
    }

    public static EClientType fromValue(String value){
        if("app".equals(value)) {
            return Application;
        }
        if("user".equals(value)) {
            return User;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
