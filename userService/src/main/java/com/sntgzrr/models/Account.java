package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Account")
@Table(name = "\"Accounts\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements Serializable {
    @Id
    @Column(name = "cvu")
    String cvu;
    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    User user;
    @Column(name = "moneyAmount")
    BigDecimal moneyAmount;
    @Column(name = "transactions")
    Integer transactions;
    @Column(name = "activity")
    Integer activity;
}
