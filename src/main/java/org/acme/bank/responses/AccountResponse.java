package org.acme.bank.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.bank.dao.User;

@Setter
@Getter
@NoArgsConstructor

public class AccountResponse {
    private Long id;
    private String typeAccount;
    private double balance;
    private User user;
}
