package com.ascending.project.domain;


import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "friends")
public class Friend {


    @javax.persistence.Id
    @GeneratedValue(strategy=SEQUENCE, generator = "friends_id_seq")
    @SequenceGenerator(name="friends_id_seq", sequenceName = "friends_id_seq", allocationSize = 1)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
