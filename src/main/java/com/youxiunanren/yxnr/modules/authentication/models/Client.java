package com.youxiunanren.yxnr.modules.authentication.models;

import com.youxiunanren.yxnr.model.Entity;
import com.youxiunanren.yxnr.model.annotation.DtoRequired;
import com.youxiunanren.yxnr.model.annotation.ID;
import com.youxiunanren.yxnr.model.annotation.PoRequired;

public class Client extends Entity {
    @PoRequired
    @DtoRequired
    private EClientType clientType;
    // <editor-fold  defaultstate="collapsed" desc="Fields for Application">
    @PoRequired
    @DtoRequired
    private String name;
    @PoRequired
    @DtoRequired
    private String logo;
    @PoRequired
    @DtoRequired
    private String redirectUri;
    //</editor-fold>

    // <editor-fold  defaultstate="collapsed" desc="Fields for User">
    @PoRequired
    @DtoRequired
    private String username;
    @PoRequired
    private String password;
    //</editor-fold>

    @PoRequired
    @DtoRequired
    @ID
    private String clientId;
    @PoRequired
    @DtoRequired
    private String clientSecret;

    public Client(){}

    /**
     *  Used for Application Registration
     */
    public Client(String name, String logo, String redirectUri){
        this.name = name;
        this.logo = logo;
        this.redirectUri = redirectUri;
        this.clientType = EClientType.Application;
    }

    /**
     *  Used for User Registration
     */
    public Client(String username, String password){
        this.username = username;
        this.password = password;
        this.clientType = EClientType.User;
    }

    public EClientType getClientType() {
        return clientType;
    }

    public void setClientType(EClientType clientType) {
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
