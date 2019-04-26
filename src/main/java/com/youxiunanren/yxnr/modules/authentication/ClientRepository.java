package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.model.BaseRepository;
import com.youxiunanren.yxnr.modules.authentication.models.Client;

import javax.inject.Named;

@Named
public class ClientRepository extends BaseRepository<Client> {

    public ClientRepository(){
        super(Client.class);
    }
}
