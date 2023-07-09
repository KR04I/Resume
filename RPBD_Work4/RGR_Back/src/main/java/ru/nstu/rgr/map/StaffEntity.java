package ru.nstu.rgr.map;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "staff")
@Data
public class StaffEntity {

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

    @ManyToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id", nullable = false)
    private PassportEntity passportByPassport;
}

