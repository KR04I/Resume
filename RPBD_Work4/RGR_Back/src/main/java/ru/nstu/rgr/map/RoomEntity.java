package ru.nstu.rgr.map;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class RoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;


    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "level")
    private Integer level;

    @Column(name = "number_available_seats")
    private Integer numberAvailableSeats;

    @Column(name = "living_people")
    private Integer livingPeople;

}

