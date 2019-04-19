package com.youxiunanren.yxnr.modules.authentication.models;

import com.youxiunanren.yxnr.model.Entity;
import com.youxiunanren.yxnr.model.annotation.DtoRequired;
import com.youxiunanren.yxnr.model.annotation.PoRequired;

public class AuthorizationCode extends Entity {
    @PoRequired
    @DtoRequired
    private String code;
    @PoRequired
    @DtoRequired
    private String state;
    @PoRequired
    @DtoRequired
    private String scope;
    @PoRequired
    private String clientId;
    @PoRequired
    @DtoRequired
    private String redirectUri;

    private Client client;

    public AuthorizationCode(){}


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
