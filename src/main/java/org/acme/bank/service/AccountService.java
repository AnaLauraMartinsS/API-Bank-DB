package org.acme.bank.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.bank.exceptions.InvalidAccountException;
import org.acme.bank.model.Account;
import org.acme.bank.rest.dto.CreateAccountRequest;
import org.acme.bank.rest.dto.DepositRequest;

@ApplicationScoped
public class AccountService {
    public Account createAccount(CreateAccountRequest accountRequest) {
        Account account = new Account();
        account.setType_account(accountRequest.getType_account());
        account.setIdUsers(accountRequest.getIdUsers());

        account.persist();
        return account;
    }

    public String deposit(DepositRequest depositRequest) throws InvalidAccountException {
        Account account = Account.findById(depositRequest.getIdAccount());

        if (account == null) {
            throw new InvalidAccountException("Conta inválida! \nPor favor, certifique-se que o nº da conta está correto!!");
        }
        account.setBalance(depositRequest.getValue());
        account.persist();
        return ("O seu depósito foi realizado com sucesso");
    }

}
