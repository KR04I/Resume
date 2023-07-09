package ru.nstu.rgr.map;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "passport")
@Data
public class PassportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "date_extradition")
    private Date dateExtradition;

    @Column(name = "number")
    private Integer number;

    @Column(name = "address")
    private String address;

    @Column(name = "passport_issuance")
    private String passportIssuance;
}
