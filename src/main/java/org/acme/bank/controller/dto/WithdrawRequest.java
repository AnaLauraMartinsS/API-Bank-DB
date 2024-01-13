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
public class WithdrawRequest {

    private Long idAccount;
    private double valueWithdraw;

}
