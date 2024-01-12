package org.acme.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccountRequest {
    private Integer type_account;

    private Long idUsers;
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
