package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.model.Pagination;
import com.youxiunanren.yxnr.modules.authentication.models.AppClientForm;
import com.youxiunanren.yxnr.modules.authentication.models.Client;
import com.youxiunanren.yxnr.modules.authentication.models.EClientType;
import com.youxiunanren.yxnr.modules.authentication.models.UserClientForm;
import com.youxiunanren.yxnr.rs.core.ValidationResult;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ClientService {

    public ValidationResult validateCreateAppClient(AppClientForm form) {
        return ValidationResult.ok().build();
    }

    public ValidationResult validateUpdateAppClient(String clientId, AppClientForm form) {
        return ValidationResult.ok().build();
    }

    public ValidationResult validateCreateUserClient(UserClientForm form) {
        return ValidationResult.ok().build();
    }

    private boolean createClient(Client client){
        return true;
    }

    private boolean updateClient(String clientId, Client client){
        return true;
    }

    private boolean deleteClient(String clientId){
        return true;
    }

    public List<Client> getClients(EClientType clientType, Pagination pagination) {
        ArrayList<Client> clients = new ArrayList<>();
        return clients;
    }

    public long getClientsCount(EClientType clientType) {
        return 0;
    }

    public Client getClient(String clientId){
        return new Client();
    }

    public Client createAppClient(AppClientForm form) {
        if(form == null) return null;
        Client client = form.convertToClient();
        if(createClient(client)) return client;
        return null;
    }

    public Client updateAppClient(String clientId, AppClientForm form) {
        if(form == null) return null;
        Client client = form.convertToClient();
        if(updateClient(clientId, client)) return client;
        return null;
    }

    public Client createUserClient(UserClientForm form) {
        if(form == null) return null;
        Client client = form.convertToClient();
        if(createClient(client)) return client;
        return null;
    }

    public Client uppdateUserClient(String clientId, UserClientForm form) {
        if(form == null) return null;
        Client client = form.convertToClient();
        if(updateClient(clientId, client)) return client;
        return null;
    }

    public ValidationResult deleteAppClient(String clientId) {
        if(clientId == null) {
            return ValidationResult.badRequest("Client ID is required").build();
        }
        if(deleteClient(clientId)) {
            return ValidationResult.ok().build();
        }
        return ValidationResult.interalException("Failed to delete Application client").build();
    }

    public ValidationResult deleteUserClient(String clientId) {
        if(clientId == null) {
            return ValidationResult.badRequest("Client ID is required").build();
        }
        if(deleteClient(clientId)) {
            return ValidationResult.ok().build();
        }
        return ValidationResult.interalException("Failed to delete User client").build();
    }
}
