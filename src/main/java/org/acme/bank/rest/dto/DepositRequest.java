package org.acme.bank.rest.dto;

import java.util.Objects;

public class DepositRequest {
    private Long idAccount;
    private double value;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositRequest that = (DepositRequest) o;
        return Double.compare(value, that.value) == 0 && Objects.equals(idAccount, that.idAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccount, value);
    }
}
