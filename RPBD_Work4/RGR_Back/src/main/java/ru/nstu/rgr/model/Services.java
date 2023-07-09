package ru.nstu.rgr.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Data
public class Services {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;
}