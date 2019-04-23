package com.youxiunanren.yxnr.modules.authentication.models;

public class UserClientForm {

    private String clientId;
    private String username;
    private String password;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Client convertToClient(){
        Client client = new Client(this.username, this.password);
        client.setClientId(this.clientId);
        return client;
    }
}
