package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity(name = "Card")
@Table(name = "\"Cards\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "type")
    String type;
    @Column(name = "number")
    String number;
    @Column(name = "cvv")
    String cvv;
    @ManyToOne
    @JoinColumn(name = "accountCVU", referencedColumnName = "cvu")
    Account accountCVU;
}
