package com.ascending.project.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "groups")
public class Group {

    @javax.persistence.Id
    @GeneratedValue(strategy=SEQUENCE, generator = "groups_id_seq")
    @SequenceGenerator(name="groups_id_seq", sequenceName = "groups_id_seq", allocationSize = 1)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "balances")
    private Integer balances;
}
