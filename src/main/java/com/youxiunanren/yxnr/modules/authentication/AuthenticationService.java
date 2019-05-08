package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.db.core.filter.Filter;
import com.youxiunanren.yxnr.model.DataTransferEntity;
import com.youxiunanren.yxnr.modules.authentication.models.*;
import com.youxiunanren.yxnr.rs.core.ValidationResult;
import com.youxiunanren.yxnr.util.RandomUtil;
import org.apache.commons.lang3.time.DateUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
public class AuthenticationService {

    public static final int EXPIRES_IN = 24 * 60 * 60;

    public static final int REFRESH_EXPIRES_IN = 30 * 24 * 60 * 60;

    public static final String TOKEN_TYPE = "Bearer";

    @Inject
    ClientRepository clientRepo;

    @Inject
    CodeRepository codeRepo;

    @Inject
    TokenRepository tokenRepo;

    public String generateUnique(){
        return RandomUtil.unique();
    }

    public AuthorizationCode authorize(String clientId, String redirectUri, String scope, String state) {
        AuthorizationCode ac = new AuthorizationCode();

        String code = generateUnique();

        ac.setClientId(clientId);
        ac.setRedirectUri(redirectUri);
        ac.setScope(scope);
        ac.setCode(code);
        ac.setState(state);

        boolean succeed = codeRepo.create(ac);
        if(!succeed) return null;
        return ac;
    }

    private Token exchangeTokenWithAuthorizationCode(String code, String clientId, String scope){
        Token token = new Token();

        token.setId(tokenRepo.generateId());
        token.setAccessToken(generateUnique());
        token.setClientId(clientId);
        token.setCode(code);
        token.setScope(scope);
        token.setExpiresIn((long) EXPIRES_IN);
        token.setExpireTime(DateUtils.addSeconds(new Date(), EXPIRES_IN));
        token.setRefreshToken(generateUnique());
        token.setTokenType(TOKEN_TYPE);

        boolean succeed = tokenRepo.create(token);
        if(!succeed) return null;
        return token;
    }

    private Token exchangeTokenWithUserCredentials(String username, String clientId, String scope){
        Token token = new Token();

        token.setId(tokenRepo.generateId());
        token.setTokenType(TOKEN_TYPE);
        token.setRefreshToken(generateUnique());
        token.setAccessToken(generateUnique());
        token.setScope(scope);
        token.setExpiresIn((long) EXPIRES_IN);
        token.setExpireTime(DateUtils.addSeconds(new Date(), EXPIRES_IN));
        token.setClientId(clientId);
        token.setUsername(username);

        boolean succeed = tokenRepo.create(token);
        if(!succeed) return null;
        return token;
    }

    private Token exchangeTokenWithClientCredentials(String clientId, String scope){
        Token token = new Token();

        token.setId(tokenRepo.generateId());
        token.setClientId(clientId);
        token.setExpireTime(DateUtils.addSeconds(new Date(), EXPIRES_IN));
        token.setExpiresIn((long) EXPIRES_IN);
        token.setScope(scope);
        token.setAccessToken(generateUnique());
        token.setRefreshToken(generateUnique());
        token.setTokenType(TOKEN_TYPE);

        return token;
    }

    public Token token(TokenExchangeForm form) {
        if(form.getGrantType() == null) return null;

        EGrantType grantType = EGrantType.valueOf(form.getGrantType());

        if(EGrantType.AuthorizationCode.equals(grantType)) {
            return this.exchangeTokenWithAuthorizationCode(form.getCode(), form.getClientId(), form.getScope());
        } else if(EGrantType.ClientCredentials.equals(grantType)){
            return this.exchangeTokenWithClientCredentials(form.getClientId(), form.getScope());
        } else if(EGrantType.Password.equals(grantType)) {
            return this.exchangeTokenWithUserCredentials(form.getUsername(), form.getClientId(), form.getScope());
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
            AuthorizationCode code = codeRepo.find(form.getCode());
            if(code == null) {
                return ValidationResult.badRequest("Code not valid").build();
            }
            Client client = clientRepo.find(form.getClientId());
            if(client == null) {
                return ValidationResult.badRequest("Client not existed").build();
            }
            if(!form.getRedirectUri().equals(client.getRedirectUri())){
                return ValidationResult.badRequest("Redirect URI not matched").build();
            }
            form.setScope(code.getScope());
        }

        if(EGrantType.ClientCredentials.equals(grantType)) {
            if(form.getClientId() == null || form.getClientSecret() == null) {
                return ValidationResult.badRequest("Client ID and client secret are required.").build();
            }
            Client client = clientRepo.find(form.getClientId());
            if(client == null) {
                return ValidationResult.badRequest("Client not exist").build();
            }
            if(!form.getClientSecret().equals(client.getClientSecret())) {
                return ValidationResult.badRequest("Client not valid").build();
            }
        }

        if(EGrantType.Password.equals(grantType)){
            if(form.getUsername() == null || form.getPassword() == null || form.getClientId() == null) {
                return ValidationResult.badRequest("Client ID, user name and password are required.").build();
            }
            Client client = clientRepo.find(form.getClientId());
            if(client == null) {
                return ValidationResult.badRequest("Client not exist").build();
            }
            if(!form.getUsername().equals(client.getUsername()) || !form.getPassword().equals(client.getPassword())) {
                return ValidationResult.badRequest("Username or password is not correct").build();
            }
        }

        return ValidationResult.ok().build();
    }

    public ValidationResult validateForCodeGeneration(String clientId, String redirectUri, String scope, String state){
        if(clientId == null || redirectUri == null || state == null) {
            return ValidationResult.badRequest("Client ID, redirect URI and state are required.").build();
        }
        Client client = clientRepo.find(clientId);
        if(client == null) {
            return ValidationResult.notFound("Client not found.").build();
        }

        if(!redirectUri.equals(client.getRedirectUri())) {
            return ValidationResult.badRequest("Redirect URI not matched.").build();
        }

        return ValidationResult.ok().build();
    }
}
