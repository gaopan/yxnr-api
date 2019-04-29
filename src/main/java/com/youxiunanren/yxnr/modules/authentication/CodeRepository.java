package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.model.BaseRepository;
import com.youxiunanren.yxnr.modules.authentication.models.AuthorizationCode;

import javax.inject.Named;

@Named
public class CodeRepository extends BaseRepository<AuthorizationCode> {
    public CodeRepository() {
        super(AuthorizationCode.class);
    }
}
