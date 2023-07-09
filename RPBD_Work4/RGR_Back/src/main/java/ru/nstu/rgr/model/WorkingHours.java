package ru.nstu.rgr.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "workinghours")
@Data
public class WorkingHours {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "datestart")
    private Date datestart;
    @Column(name = "dateend")
    private Date dateend;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = false)
    private Staff staffByStaff;
}