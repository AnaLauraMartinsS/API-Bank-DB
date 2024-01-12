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
import org.acme.bank.rest.dto.WithdrawRequest;
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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser (CreateUserRequest userRequest) {

        User user = userService.createUser(userRequest);
        String successMessage = "Usuário criado com sucesso! \n" + user.toString();
        return Response.ok(user).entity(successMessage).build();
    }

    @POST
    @Path("createAccount")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount (CreateAccountRequest accountRequest) {
        Account account = new Account();
        account.setType_account(accountRequest.getType_account());
        account.setIdUsers(accountRequest.getIdUsers());

        account.persist();
        return account;
    }

    @POST
    @Transactional
    @Path("deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(DepositRequest depositRequest){
        try {
            String message = accountService.deposit(depositRequest);
            return Response.ok(message).build();
        } catch (InvalidAccountException exception){
            return Response.status(400,exception.getMessage()).build();
        }
    }

    @POST
    @Transactional
    @Path("withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response withdraw(WithdrawRequest withdrawRequest){
        try {
            String result = accountService.withdraw(withdrawRequest);
            return Response.ok(result).build();
        } catch (InvalidAccountException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

//    @GET
//    @Path("listAccounts")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response listAllAccount(){
//
//    }
//

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listAllUser(){
        PanacheQuery<PanacheEntityBase> query = User.findAll();
        return Response.ok(query.list()).build();
    }
}
