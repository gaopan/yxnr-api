package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.model.Pagination;
import com.youxiunanren.yxnr.modules.authentication.models.AppClientForm;
import com.youxiunanren.yxnr.modules.authentication.models.EClientType;
import com.youxiunanren.yxnr.modules.authentication.models.TokenExchangeForm;
import com.youxiunanren.yxnr.modules.authentication.models.UserClientForm;
import com.youxiunanren.yxnr.modules.authentication.models.Client;
import com.youxiunanren.yxnr.rs.core.ResponseOptimizer;
import com.youxiunanren.yxnr.rs.core.ValidationResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Inject
    AuthenticationService authService;

    @Inject
    ClientService clientService;

    @POST
    @Path("/client/app")
    public Response createAppClient(@NotNull @Valid AppClientForm form){
        ValidationResult vr = clientService.validateCreateAppClient(form);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok(ResponseOptimizer.optimize(clientService.createAppClient(form))).build();
    }

    @PUT
    @Path("/client/app/{clientId}")
    public Response updateAppClient(@PathParam("clientId") String clientId, @NotNull @Valid AppClientForm form){
        ValidationResult vr = clientService.validateUpdateAppClient(clientId, form);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok(ResponseOptimizer.optimize(clientService.updateAppClient(clientId, form))).build();
    }

    @DELETE
    @Path("/client/app/{clientId}")
    public Response deleteAppClient(@PathParam("clientId") String clientId){
        ValidationResult vr = clientService.deleteAppClient(clientId);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/client/app")
    public Response getAppClients(@QueryParam("pageSize") int pageSize,
                                  @QueryParam("pageIndex") int pageIndex,
                                  @QueryParam("sort") String sort,
                                  @QueryParam("sortBy") String sortBy,
                                  @QueryParam("filter") String filter){
        return Response.ok(ResponseOptimizer.optimize(Client.class, clientService.getClients(EClientType.Application, new Pagination(pageSize, pageIndex, sort, sortBy, filter)))).build();
    }

    @GET
    @Path("/client/count/app")
    public Response getAppClientCount(){
        return Response.ok(clientService.getClientsCount(EClientType.Application)).build();
    }

    @GET
    @Path("/client/app/{clientId}")
    public Response getAppClient(@PathParam("clientId") String clientId){
        return Response.ok(ResponseOptimizer.optimize(clientService.getClient(clientId))).build();
    }

    @POST
    @Path("/client/user")
    public Response createUserClient(@NotNull @Valid UserClientForm form){
        ValidationResult vr = clientService.validateCreateUserClient(form);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok(ResponseOptimizer.optimize(clientService.createUserClient(form))).build();
    }

    @PUT
    @Path("/client/user/{clientId}")
    public Response updateUserClient(@PathParam("clientId") String clientId, @NotNull @Valid AppClientForm form){
        ValidationResult vr = clientService.validateUpdateAppClient(clientId, form);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok(ResponseOptimizer.optimize(clientService.updateAppClient(clientId, form))).build();
    }

    @DELETE
    @Path("/client/user/{clientId}")
    public Response deleteUserClient(@PathParam("clientId") String clientId){
        ValidationResult vr = clientService.deleteUserClient(clientId);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/client/user")
    public Response getUserClients(@QueryParam("pageSize") int pageSize,
                                   @QueryParam("pageIndex") int pageIndex,
                                   @QueryParam("sort") String sort,
                                   @QueryParam("sortBy") String sortBy,
                                   @QueryParam("filter") String filter){
        return Response.ok(ResponseOptimizer.optimize(Client.class, clientService.getClients(EClientType.User, new Pagination(pageSize, pageIndex, sort, sortBy, filter)))).build();
    }

    @GET
    @Path("/client/count/user")
    public Response getUserClientCount(){
        return Response.ok(clientService.getClientsCount(EClientType.User)).build();
    }

    @GET
    @Path("/client/user/{clientId}")
    public Response getUserClient(@PathParam("clientId") String clientId){
        return Response.ok(ResponseOptimizer.optimize(clientService.getClient(clientId))).build();
    }

    @GET
    @Path("/code")
    public Response authorize(@QueryParam("clientId") String clientId,
                              @QueryParam("redirectUri") String redirectUri,
                              @QueryParam("scope") String scope,
                              @QueryParam("state") String state){
        ValidationResult vr = authService.validateForCodeGeneration(clientId, redirectUri, scope, state);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }

        return Response.ok(ResponseOptimizer.optimize(authService.authorize(clientId, redirectUri, scope, state))).build();
    }

    @POST
    @Path("/token")
    public Response token(@NotNull @Valid TokenExchangeForm form){
        ValidationResult vr = authService.validateForTokenGeneration(form);
        if(!vr.isOK()) {
            return Response.status(vr.getCode().getValue()).entity(vr).build();
        }

        return Response.ok(authService.token(form)).build();
    }


}
