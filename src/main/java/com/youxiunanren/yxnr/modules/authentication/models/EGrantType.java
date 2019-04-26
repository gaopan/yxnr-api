package com.youxiunanren.yxnr.modules.authentication.models;

public enum EGrantType {

    AuthorizationCode("authorization_code"),
    Password("password"),
    ClientCredentials("client_credentials");

    private final String value;

    EGrantType(String gt) {
        this.value = gt;
    }

    public static EGrantType fromValue(String value){
        if("authorization_code".equals(value)) {
            return AuthorizationCode;
        }
        if("password".equals(value)) {
            return Password;
        }
        if("client_credentials".equals(value)) {
            return ClientCredentials;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
