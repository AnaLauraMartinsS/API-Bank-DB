package org.acme.bank.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class WithdrawRequest {

    private Long idAccount;
    private double valueWithdraw;

}
