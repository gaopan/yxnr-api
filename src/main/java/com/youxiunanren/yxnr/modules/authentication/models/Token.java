package com.youxiunanren.yxnr.modules.authentication.models;

import com.youxiunanren.yxnr.model.Entity;

import java.util.Date;

public class Token extends Entity {
    /* DTO required, Used for data transfer to client*/
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

    //<editor-fold defaultstate="collapsed" desc="PO required in addition">
    /* PO required in addition, used for data persistent */
    private String clientId;
    private String tokenType;
    private Date expireTime;
    private String code;
    // Used for password grant
    private String username;
    private String password;
    //</editor-fold>

    /* Ignored by DTO and PO */
    private Client client;

    public Token(){}

    /**
     * Used for data transfer with client
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     */
    public Token(String accessToken, String refreshToken, Long expiresIn){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    /**
     * Used to persist password grant token
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     * @param clientId
     * @param username
     * @param password
     * @param tokenType
     * @param expireTime
     */
    public Token(String accessToken, String refreshToken, Long expiresIn, String clientId, String username, String password, String tokenType, Date expireTime){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;

        this.clientId = clientId;
        this.username = username;
        this.password = password;
        this.tokenType = tokenType;
        this.expireTime = expireTime;
    }

    /**
     * Used to persist code grant token
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     * @param clientId
     * @param tokenType
     * @param expireTime
     * @param code
     */
    public Token(String accessToken, String refreshToken, Long expiresIn, String clientId, String tokenType, Date expireTime, String code){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;

        this.clientId = clientId;
        this.tokenType = tokenType;
        this.expireTime = expireTime;
        this.code = code;
    }

    /**
     * Used to persist client credentials grant token
     * @param accessToken
     * @param refreshToken
     * @param expiresIn
     * @param clientId
     * @param tokenType
     * @param expireTime
     */
    public Token(String accessToken, String refreshToken, Long expiresIn, String clientId, String tokenType, Date expireTime){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;

        this.clientId = clientId;
        this.tokenType = tokenType;
        this.expireTime = expireTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        tokenType = tokenType;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
