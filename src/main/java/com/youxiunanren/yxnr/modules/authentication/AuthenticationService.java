package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.rs.core.ValidationResult;
import com.youxiunanren.yxnr.modules.authentication.models.AuthorizationCode;
import com.youxiunanren.yxnr.modules.authentication.models.EGrantType;
import com.youxiunanren.yxnr.modules.authentication.models.Token;
import com.youxiunanren.yxnr.modules.authentication.models.TokenExchangeForm;

import javax.inject.Named;

@Named
public class AuthenticationService {


    public AuthorizationCode authorize(String responseType, String clientId, String redirectUri, String scope, String state) {
        AuthorizationCode code = new AuthorizationCode();

        // TODO generate code and store it, which will be used by exchanging token
        code.setState(state);

        return code;
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
        return ValidationResult.ok().build();
    }

    public ValidationResult validateForCodeGeneration(String responseType, String clientId, String redirectUri, String scope, String state){
        return ValidationResult.ok().build();
    }
}
