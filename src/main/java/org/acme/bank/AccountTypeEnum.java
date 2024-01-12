package org.acme.bank;

public enum AccountTypeEnum {
    CONTA_CORRENTE(1),
    CONTA_POUPANÇA(2);

    public Integer typeAccount;
    AccountTypeEnum(Integer description) {
        typeAccount = description;
    }
}
