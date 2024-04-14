package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Transaction")
@Table(name = "\"Transactions\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "amount", nullable = false)
    BigDecimal amount;
    @Column(name = "details")
    String details;
    @ManyToOne
    @JoinColumn(name = "accountCVU", referencedColumnName = "cvu")
    Account account;
}
