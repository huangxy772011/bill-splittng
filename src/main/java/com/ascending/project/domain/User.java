package com.ascending.project.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy=SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long Id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill",cascade = CascadeType.ALL)
    private List<Bill> bill;
}
