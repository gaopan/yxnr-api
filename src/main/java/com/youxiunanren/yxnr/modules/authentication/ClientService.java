package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.db.core.filter.Filter;
import com.youxiunanren.yxnr.model.Pagination;
import com.youxiunanren.yxnr.modules.authentication.models.AppClientForm;
import com.youxiunanren.yxnr.modules.authentication.models.Client;
import com.youxiunanren.yxnr.modules.authentication.models.EClientType;
import com.youxiunanren.yxnr.modules.authentication.models.UserClientForm;
import com.youxiunanren.yxnr.rs.core.ValidationResult;
import com.youxiunanren.yxnr.util.RandomUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ClientService {

    @Inject
    ClientRepository clientRepo;

    public ValidationResult validateCreateAppClient(AppClientForm form) {
        if(form.getName() == null) {
            return ValidationResult.badRequest("Name is required").build();
        }
        if(form.getRedirectUri() == null) {
            return ValidationResult.badRequest("Redirect URI is required").build();
        }
        List<Client> clients = clientRepo.findAll(Filter.eq("name", form.getName()));
        if(clients.size() > 0) {
            return ValidationResult.badRequest("Duplicated name").build();
        }
        return ValidationResult.ok().build();
    }

    public ValidationResult validateUpdateAppClient(String clientId, AppClientForm form) {
        if(form.getName() == null) {
            return ValidationResult.badRequest("Name is required").build();
        }
        if(form.getRedirectUri() == null) {
            return ValidationResult.badRequest("Redirect URI is required").build();
        }
        Client client = clientRepo.find(clientId);
        if(client == null) {
            return ValidationResult.badRequest("Client not exist").build();
        }
        List<Client> clients = clientRepo.findAll(Filter.eq("name", form.getName()));
        if(clients.size() > 1) {
            return ValidationResult.badRequest("Duplicated name").build();
        }
        return ValidationResult.ok().build();
    }

    public ValidationResult validateCreateUserClient(UserClientForm form) {
        if(form.getUsername() == null) {
            return ValidationResult.badRequest("Username is required.").build();
        }
        if(form.getPassword() == null) {
            return ValidationResult.badRequest("Password is required.").build();
        }
        List<Client> clients = clientRepo.findAll(Filter.eq("username", form.getUsername()));
        if(clients.size() > 0) {
            return ValidationResult.badRequest("Duplicated username").build();
        }
        return ValidationResult.ok().build();
    }

    public ValidationResult validateUpdateUserClient(String clientId, UserClientForm form) {
        if(form.getUsername() == null) {
            return ValidationResult.badRequest("Username is required.").build();
        }
        if(form.getPassword() == null) {
            return ValidationResult.badRequest("Password is required.").build();
        }
        Client client = clientRepo.find(clientId);
        if(client == null) {
            return ValidationResult.badRequest("Client not exist").build();
        }
        List<Client> clients = clientRepo.findAll(Filter.eq("username", form.getUsername()));
        if(clients.size() > 1) {
            return ValidationResult.badRequest("Duplicated username").build();
        }
        return ValidationResult.ok().build();
    }

    private boolean createClient(Client client){
        client.setClientId(RandomUtil.unique());
        return clientRepo.create(client);
    }

    private boolean updateClient(String clientId, Client client){
        return clientRepo.update(clientId, client);
    }

    private boolean deleteClient(String clientId){
        return clientRepo.delete(clientId);
    }

    public List<Client> getClients(EClientType clientType, String filter, Pagination pagination) {
        if(filter == null) {
            filter = "";
        }
        if(EClientType.Application.equals(clientType)) {
            return clientRepo.findByPagination(Filter.contains("name", filter), pagination);
        } else if(EClientType.User.equals(clientType)) {
            return clientRepo.findByPagination(Filter.contains("username", filter), pagination);
        }
        return new ArrayList<>();
    }

    public long getClientsCount(EClientType clientType) {
        return clientRepo.count(Filter.eq("clientType", clientType.toString()));
    }

    public Client getClient(String clientId){
        return clientRepo.find(clientId);
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

    public Client updateUserClient(String clientId, UserClientForm form) {
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
