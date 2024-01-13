package org.acme.bank;

import lombok.Getter;

@Getter
public enum AccountTypeEnum {
    CONTA_CORRENTE(1, "Conta corrente"),
    CONTA_POUPANCA(2, "Conta poupança");

    private final Integer code;
    private final String name;
    AccountTypeEnum(Integer code, String name) {
         this.code = code;
         this.name = name;
    }

    public static AccountTypeEnum fromCode(Integer code){
        for(AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()){
            if(accountTypeEnum.getCode() == code){
                return accountTypeEnum;
            }
        }
        throw new IllegalArgumentException("Código de tipo de conta inválido!");
    }
}
