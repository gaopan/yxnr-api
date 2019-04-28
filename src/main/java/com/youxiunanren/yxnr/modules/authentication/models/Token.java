package com.youxiunanren.yxnr.modules.authentication.models;

import com.youxiunanren.yxnr.model.Entity;
import com.youxiunanren.yxnr.model.annotation.DtoRequired;
import com.youxiunanren.yxnr.model.annotation.PoRequired;

import java.util.Date;

public class Token extends Entity {
    /* DTO required, Used for data transfer to client*/
    @PoRequired
    @DtoRequired
    private String accessToken;
    @PoRequired
    @DtoRequired
    private String refreshToken;
    @PoRequired
    @DtoRequired
    private Long expiresIn;
    @PoRequired
    @DtoRequired
    private String tokenType;

    //<editor-fold defaultstate="collapsed" desc="PO required in addition">
    /* PO required in addition, used for data persistent */
    @PoRequired
    private String clientId;
    @PoRequired
    private Date expireTime;
    @PoRequired
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
    public Token(String accessToken, String refreshToken, Long expiresIn, String tokenType){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
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
    public Token(String accessToken, String refreshToken, Long expiresIn, String tokenType, String clientId, String username, String password, Date expireTime){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;

        this.clientId = clientId;
        this.username = username;
        this.password = password;
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
    public Token(String accessToken, String refreshToken, Long expiresIn, String tokenType, String clientId, Date expireTime, String code){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;

        this.clientId = clientId;
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
    public Token(String accessToken, String refreshToken, Long expiresIn, String tokenType, String clientId, Date expireTime){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;

        this.clientId = clientId;
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
