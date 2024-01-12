package org.acme.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private Integer age;
    private String address;
    private String phone;
    private String AccountTypeEnum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(AccountTypeEnum, that.AccountTypeEnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, phone, AccountTypeEnum);
    }
}
