package org.acme.bank.rest.dto;

import java.util.Objects;

public class CreateAccountRequest {
    private Integer type_account;

    private Long idUsers;

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }


    public Integer getType_account() {
        return type_account;
    }

    public void setType_account(Integer type_account) {
        this.type_account = type_account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return Objects.equals(type_account, that.type_account) && Objects.equals(idUsers, that.idUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_account, idUsers);
    }
}
