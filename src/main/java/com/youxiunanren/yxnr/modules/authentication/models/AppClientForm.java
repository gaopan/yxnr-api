package com.youxiunanren.yxnr.modules.authentication.models;

public class AppClientForm {

    private String clientId;
    private String name;
    private String logo;
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Client convertToClient(){
        Client client = new Client(this.name, this.logo, this.redirectUri);
        client.setClientId(this.clientId);
        return client;
    }
}
