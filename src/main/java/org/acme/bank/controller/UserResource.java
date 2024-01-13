package org.acme.bank.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.bank.AccountTypeEnum;
import org.acme.bank.controller.dto.*;
import org.acme.bank.exceptions.InvalidAccountException;
import org.acme.bank.dao.Account;
import org.acme.bank.dao.User;
import org.acme.bank.responses.AccountResponse;
import org.acme.bank.service.AccountService;
import org.acme.bank.service.UserService;


@Path("api/v1/users")
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
    public Response createAccount(CreateAccountRequest accountRequest) {
       try{
          Account account = accountService.createAccount(accountRequest);

           AccountTypeEnum accountTypeEnum = AccountTypeEnum.fromCode(account.getType_account());

           User user = User.findById(account.getIdUsers());

           AccountResponse accountResponse = new AccountResponse();
           accountResponse.setId(account.getId());
           accountResponse.setTypeAccount(accountTypeEnum.getName());
           accountResponse.setUser(user);
           accountResponse.setBalance(account.getBalance());

           return Response.ok(accountResponse).build();
       } catch(Exception exception) {
           return Response.status(400, exception.getMessage()).build();
       }
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
        } catch (InvalidAccountException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }
    @GET
    @Path("listAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listAllAccount() {
        PanacheQuery<PanacheEntityBase> query = Account.findAll();
        return Response.ok(query.list()).build();
    }
    @GET
    @Path("details")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listUser(DetailsRequest detailsRequest){
        try{
            AccountResponse accountResponse = accountService.details(detailsRequest);
            return Response.ok(accountResponse).build();
        }catch (InvalidAccountException exception){
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
