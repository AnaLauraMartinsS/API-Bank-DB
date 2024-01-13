package org.acme.bank.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class CreateUserRequest {
    private String name;
    private Integer age;
    private String address;
    private String phone;
    private String AccountTypeEnum;

}
