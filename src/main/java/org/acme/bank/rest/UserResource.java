package org.acme.bank.rest;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.bank.exceptions.InvalidAccountException;
import org.acme.bank.model.Account;
import org.acme.bank.model.User;
import org.acme.bank.rest.dto.CreateAccountRequest;
import org.acme.bank.rest.dto.CreateUserRequest;
import org.acme.bank.rest.dto.DepositRequest;
import org.acme.bank.service.AccountService;
import org.acme.bank.service.UserService;


@Path("/users")
@ApplicationScoped
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    AccountService accountService;
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser (CreateUserRequest userRequest) {

        User user = userService.createUser(userRequest);

        return Response.ok(user).build();
    }

    @POST
    @Path("createAccount")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount (CreateAccountRequest accountRequest) {

        Account account = accountService.createAccount(accountRequest);

        return Response.ok(account).build();
    }

    @POST
    @Transactional
    @Path("deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(DepositRequest depositRequest){
        try {
            String massanger = accountService.deposit(depositRequest);
            return Response.ok(depositRequest).build();
        } catch (InvalidAccountException exception){
            return Response.status(400,exception.getMessage()).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listAllUser(){
        PanacheQuery<PanacheEntityBase> query = User.findAll();
        return Response.ok(query.list()).build();
    }
}
