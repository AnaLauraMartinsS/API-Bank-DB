package org.acme.bank.exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String mensagem) {
        super(mensagem);
    }
}
