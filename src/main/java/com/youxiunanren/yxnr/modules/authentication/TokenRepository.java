package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.model.BaseRepository;
import com.youxiunanren.yxnr.modules.authentication.models.Token;

import javax.inject.Named;

@Named
public class TokenRepository extends BaseRepository<Token> {
    public TokenRepository() {
        super(Token.class);
    }
}
