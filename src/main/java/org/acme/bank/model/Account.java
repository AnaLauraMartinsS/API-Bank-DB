package org.acme.bank.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "account")
public class Account extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_account")
    private Integer type_account;
    @Column(name = "balance")
    private double balance = 0;
    @Column(name = "id_users")
    private Long idUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType_account() {
        return type_account;
    }

    public void setType_account(Integer type_account) {
        this.type_account = type_account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Objects.equals(id, account.id)
                && Objects.equals(type_account, account.type_account) && Objects.equals(idUsers, account.idUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_account, balance, idUsers);
    }
}
