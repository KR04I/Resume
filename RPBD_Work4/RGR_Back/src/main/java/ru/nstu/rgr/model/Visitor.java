package ru.nstu.rgr.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visitor")
@Data
public class Visitor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private Integer gender;

    @ManyToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id", nullable = false)
    private Passport passportByPassport;

    @ManyToMany
    @JoinTable(
            name="visitor_service",
            joinColumns = @JoinColumn(name= "visitor_id"),
            inverseJoinColumns = @JoinColumn(name= "service_id")
    )
    private List<Services> services = new ArrayList<>();
}