package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Transfer")
@Table(name = "\"Transferences\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "details")
    String details;
    @Column(name = "moneyAmount")
    BigDecimal moneyAmount;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    Card card;
}
