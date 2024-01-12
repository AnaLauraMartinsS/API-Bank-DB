package org.acme.bank.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class WithdrawRequest {

    private Long idAccount;
    private double valueWithdraw;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawRequest that = (WithdrawRequest) o;
        return Double.compare(valueWithdraw, that.valueWithdraw) == 0 && Objects.equals(idAccount, that.idAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccount, valueWithdraw);
    }
}
