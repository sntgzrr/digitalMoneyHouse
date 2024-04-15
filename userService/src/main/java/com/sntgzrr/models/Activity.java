package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity(name = "Activity")
@Table(name = "\"Activities\"")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Activity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "description")
    String description;
    @OneToOne
    @JoinColumn(name = "transferID", referencedColumnName = "id")
    Transfer transfer;
    @ManyToOne
    @JoinColumn(name = "accountCVU", referencedColumnName = "cvu")
    Account account;
}
