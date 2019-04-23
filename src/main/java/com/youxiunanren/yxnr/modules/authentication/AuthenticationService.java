package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.rs.core.ValidationResult;
import com.youxiunanren.yxnr.modules.authentication.models.AuthorizationCode;
import com.youxiunanren.yxnr.modules.authentication.models.EGrantType;
import com.youxiunanren.yxnr.modules.authentication.models.Token;
import com.youxiunanren.yxnr.modules.authentication.models.TokenExchangeForm;
import com.youxiunanren.yxnr.util.RandomUtil;

import javax.inject.Named;

@Named
public class AuthenticationService {


    public AuthorizationCode authorize(String clientId, String redirectUri, String scope, String state) {
        AuthorizationCode ac = new AuthorizationCode();

        // TODO generate code and store it, which will be used by exchanging token
        String code = RandomUtil.unique();

        ac.setClientId(clientId);
        ac.setRedirectUri(redirectUri);
        ac.setScope(scope);
        ac.setCode(code);
        ac.setState(state);
        return ac;
    }

    private Token exchangeTokenWithAuthorizationCode(String code, String redirectUri, String clientId, String clientSecret){
        Token token = new Token();

        // TODO generate token and store it


        return token;
    }

    private Token exchangeTokenWithUserCredentials(String username, String password, String clientId){
        Token token = new Token();


        return token;
    }

    private Token exchangeTokenWithClientCredentials(String clientId, String clientSecret){
        Token token = new Token();

        return token;
    }

    public Token token(TokenExchangeForm form) {
        if(form.getGrantType() == null) return null;

        EGrantType grantType = EGrantType.valueOf(form.getGrantType());

        if(EGrantType.AuthorizationCode.equals(grantType)) {
            return this.exchangeTokenWithAuthorizationCode(form.getCode(), form.getRedirectUri(), form.getClientId(), form.getClientSecret());
        } else if(EGrantType.ClientCredentials.equals(grantType)){
            return this.exchangeTokenWithClientCredentials(form.getClientId(), form.getClientSecret());
        } else if(EGrantType.Password.equals(grantType)) {
            return this.exchangeTokenWithUserCredentials(form.getUsername(), form.getPassword(), form.getClientId());
        }

        return null;
    }

    public ValidationResult validateForTokenGeneration(TokenExchangeForm form){
        EGrantType grantType = null;
        if(form.getGrantType() == null || (grantType = EGrantType.valueOf(form.getGrantType())) == null) {
            return ValidationResult.badRequest("Grant type is required").build();
        }

        if(EGrantType.AuthorizationCode.equals(grantType)) {
            if(form.getClientId() == null || form.getCode() == null || form.getRedirectUri() == null) {
                return ValidationResult.badRequest("Client ID, authorization code and redirect URI are required.").build();
            }
        }

        if(EGrantType.ClientCredentials.equals(grantType)) {
            if(form.getClientId() == null || form.getClientSecret() == null) {
                return ValidationResult.badRequest("Client ID and client secret are required.").build();
            }
        }

        if(EGrantType.Password.equals(grantType)){
            if(form.getUsername() == null || form.getPassword() == null || form.getClientSecret() == null) {
                return ValidationResult.badRequest("Client ID, user name and password are required.").build();
            }
        }

        return ValidationResult.ok().build();
    }

    public ValidationResult validateForCodeGeneration(String clientId, String redirectUri, String scope, String state){
        if(clientId == null || redirectUri == null || state == null) {
            return ValidationResult.badRequest("Client ID, redirect URI and state are required.").build();
        }
        return ValidationResult.ok().build();
    }
}
