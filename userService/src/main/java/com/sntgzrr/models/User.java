package com.sntgzrr.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Entity(name = "User")
@Table(name = "\"Users\"", schema = "public")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "firstName", nullable = false)
    String firstName;
    @Column(name = "lastName", nullable = false)
    String lastName;
    @Column(name = "dni", nullable = false, unique = true)
    String dni;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "cvu", nullable = false)
    String cvu;
    @Column(name = "username", nullable = false)
    String username;
}
