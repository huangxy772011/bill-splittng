package com.ascending.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name = "authorities")
public class Authority implements GrantedAuthority {

    @Override
    public String getAuthority() {                  //get Authority's string name
        return null;
    }

    @javax.persistence.Id
    @GeneratedValue(strategy=SEQUENCE, generator = "auth_id_seq")
    @SequenceGenerator(name="auth_id_seq", sequenceName = "auth_id_seq", allocationSize = 1)
    private Long Id;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Column
//    @NotNull
    private String authority;

    // @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Authority(){

    }

    public Authority(User user, String authority){
        this.user = user;
        this.authority = authority;
    }

    public Long getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
