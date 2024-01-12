package org.acme.bank.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Objects.equals(id, account.id) && Objects.equals(type_account, account.type_account) && Objects.equals(idUsers, account.idUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_account, balance, idUsers);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type_account=" + type_account +
                ", balance=" + balance +
                ", idUsers=" + idUsers +
                '}';
    }
}
