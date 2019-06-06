package com.ascending.project.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "bills")
public class Bill {

    @javax.persistence.Id
    @GeneratedValue(strategy=SEQUENCE, generator = "bills_id_seq")
    @SequenceGenerator(name="bills_id_seq", sequenceName = "bills_id_seq", allocationSize = 1)
    private Long Id;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
