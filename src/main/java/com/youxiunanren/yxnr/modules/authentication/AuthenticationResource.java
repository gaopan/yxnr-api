package com.youxiunanren.yxnr.modules.authentication;

import com.youxiunanren.yxnr.modules.authentication.models.TokenExchangeForm;
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

    @GET
    @Path("/code")
    public Response authorize(@QueryParam("responseType") String responseType,
                              @QueryParam("clientId") String clientId,
                              @QueryParam("redirectUri") String redirectUri,
                              @QueryParam("scope") String scope,
                              @QueryParam("state") String state){
        if(responseType == null || clientId == null || redirectUri == null || scope == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(ResponseOptimizer.optimize(authService.authorize(responseType, clientId, redirectUri, scope, state))).build();
    }

    @POST
    @Path("/token")
    public Response token(@NotNull @Valid TokenExchangeForm form){
        ValidationResult vr = authService.validateForTokenGeneration(form);
        if(!vr.isOK()) {
            return Response.ok(vr).build();
        }

        return Response.ok(authService.token(form)).build();
    }


}
