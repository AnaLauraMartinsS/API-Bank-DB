package org.acme.bank.exceptions;

public class InvalidAccountException extends Exception {
    public InvalidAccountException (String mensagem){
        super(mensagem);
    }
}
