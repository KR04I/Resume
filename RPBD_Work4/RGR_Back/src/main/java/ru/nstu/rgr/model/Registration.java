package ru.nstu.rgr.model;

import lombok.Data;
import ru.nstu.rgr.model.Room;
import ru.nstu.rgr.model.Visitor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "registration")
@Data
public class Registration{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "parking_number")
    private Integer parkingNumber;
    @Column(name = "car_registration_number")
    private String carRegistrationNumber;
    @Column(name = "date_of_entry")
    private Date dateOfEntry;
    @Column(name = "date_of_departure")
    private Date dateOfDeparture;

    @ManyToOne
    @JoinColumn(name = "visitor_id", referencedColumnName = "id", nullable = false)
    private Visitor visitorByVisitor;

    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "id", nullable = false)
    private Room roomByRoom;
}