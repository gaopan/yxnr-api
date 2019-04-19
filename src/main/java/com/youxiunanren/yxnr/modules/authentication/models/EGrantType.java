package com.youxiunanren.yxnr.modules.authentication.models;

public enum EGrantType {

    AuthorizationCode("authorization_code"),
    Password("password"),
    ClientCredentials("client_credentials");

    private final String value;

    EGrantType(String gt) {
        this.value = gt;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
