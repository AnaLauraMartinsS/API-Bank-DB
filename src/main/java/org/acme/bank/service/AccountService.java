package org.acme.bank.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.acme.bank.AccountTypeEnum;
import org.acme.bank.controller.dto.DetailsRequest;
import org.acme.bank.dao.User;
import org.acme.bank.exceptions.InvalidAccountException;
import org.acme.bank.dao.Account;
import org.acme.bank.controller.dto.CreateAccountRequest;
import org.acme.bank.controller.dto.DepositRequest;
import org.acme.bank.controller.dto.WithdrawRequest;
import org.acme.bank.responses.AccountResponse;

@ApplicationScoped
public class AccountService {
    public Account createAccount(CreateAccountRequest accountRequest) throws InvalidAccountException {
        Account account = new Account();

        if (account == null) {
            throw new InvalidAccountException("Erro ao criar conta. A conta não pode ser inicializada corretamente.");
        }

        //AccountTypeEnum accountTypeEnum = AccountTypeEnum.fromCode(accountRequest.getType_account());
        account.setType_account(accountRequest.getType_account());
        account.setIdUsers(accountRequest.getIdUsers());

        account.persist();

        String successMessage = "Conta criada com sucesso. ID da conta: " + account.toString() +
                ", ID do usuário: " + account.getIdUsers();

        System.out.println(successMessage);

        return account;
    }

    public String deposit(DepositRequest depositRequest) throws InvalidAccountException {
        Account account = Account.findById(depositRequest.getIdAccount());

        if (account == null) {
            throw new InvalidAccountException("Conta inválida! \nPor favor, certifique-se que o nº da conta está correto!!");
        }
        account.setBalance(depositRequest.getValue());
        account.persist();
        return ("O seu depósito foi realizado com sucesso! No valor de R$: " + account.getBalance());
    }

    public String withdraw(WithdrawRequest withdrawRequest) throws InvalidAccountException {
        Account account = Account.findById(withdrawRequest.getIdAccount());

        if (account == null) {
            throw new InvalidAccountException("Conta inválida! \nPor favor, certifique-se que o nº da conta está correto!!");
        }

        // Verificando se há saldo suficiente para o saque
        double currentBalance = account.getBalance();
        double withdrawAmount = withdrawRequest.getValueWithdraw();

        if (currentBalance < withdrawAmount) {
            throw new InvalidAccountException("Saldo insuficiente para realizar o saque!");
        }

        // Atualização do saldo após o saque
        account.setBalance(currentBalance - withdrawAmount);
        account.persist();

        String successMessage = "Saque realizado com sucesso! Novo saldo: " + account.getBalance();
        System.out.println(successMessage);

        return successMessage;
    }

    public AccountResponse details(DetailsRequest detailsRequest) throws InvalidAccountException {
        Account account = Account.findById(detailsRequest.getAccountId());

        if (account == null) {
            throw new InvalidAccountException("A conta não existe!");
        }

        AccountResponse accountResponse = new AccountResponse();
        AccountTypeEnum accountTypeEnum = AccountTypeEnum.fromCode(account.getType_account());
        User user = User.findById(account.getIdUsers());
        accountResponse.setId(account.getId());
        accountResponse.setTypeAccount(accountTypeEnum.getName());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setUser(user);
        return accountResponse;
    }


}
