package org.acme.bank.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.bank.dao.Account;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String phone;
    private Account account; // achei tendência do próximo verão

}
