package DB_hotel;

import jakarta.persistence.*;

import java.sql.Date;


@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byVisitorId", query = "From RegistrationEntity re where re.visitorByVisitorId =?1")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byRoomNumber", query = "From RegistrationEntity re where re.roomByRoomNumber =?1")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byParkingNumber", query = "From RegistrationEntity re where re.parkingNumber =?1")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byCarRegistrationNumber", query = "From RegistrationEntity re where upper(re.carRegistrationNumber) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byDateOfEntry", query = "FROM RegistrationEntity re where upper(cast(re.dateOfEntry as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.byDateOfDeparture", query = "FROM RegistrationEntity re where upper(cast(re.dateOfDeparture as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "RegistrationEntity.all", query = "From RegistrationEntity re")


@Entity
@Table(name = "registration", schema = "public", catalog = "hotel")
public class RegistrationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "parking_number")
    private Integer parkingNumber;
    @Basic
    @Column(name = "car_registration_number")
    private String carRegistrationNumber;
    @Basic
    @Column(name = "date_of_entry")
    private Date dateOfEntry;
    @Basic
    @Column(name = "date_of_departure")
    private Date dateOfDeparture;
    @ManyToOne
    @JoinColumn(name = "visitor_id", referencedColumnName = "id")
    private VisitorEntity visitorByVisitorId;
    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "id")
    private RoomEntity roomByRoomNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(Integer parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationEntity that = (RegistrationEntity) o;

        if (id != that.id) return false;

        if (parkingNumber != null ? !parkingNumber.equals(that.parkingNumber) : that.parkingNumber != null)
            return false;
        if (carRegistrationNumber != null ? !carRegistrationNumber.equals(that.carRegistrationNumber) : that.carRegistrationNumber != null)
            return false;
        if (dateOfEntry != null ? !dateOfEntry.equals(that.dateOfEntry) : that.dateOfEntry != null) return false;
        if (dateOfDeparture != null ? !dateOfDeparture.equals(that.dateOfDeparture) : that.dateOfDeparture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parkingNumber != null ? parkingNumber.hashCode() : 0);
        result = 31 * result + (carRegistrationNumber != null ? carRegistrationNumber.hashCode() : 0);
        result = 31 * result + (dateOfEntry != null ? dateOfEntry.hashCode() : 0);
        result = 31 * result + (dateOfDeparture != null ? dateOfDeparture.hashCode() : 0);
        return result;
    }

    public VisitorEntity getVisitorByVisitorId() {
        return visitorByVisitorId;
    }

    public void setVisitorByVisitorId(VisitorEntity visitorByVisitorId) {
        this.visitorByVisitorId = visitorByVisitorId;
    }

    public RoomEntity getRoomByRoomNumber() {
        return roomByRoomNumber;
    }

    public void setRoomByRoomNumber(RoomEntity roomByRoomNumber) {
        this.roomByRoomNumber = roomByRoomNumber;
    }

    @Override
    public String toString() {
        return "RegistrationEntity{" +
                "id=" + id +
                ", parkingNumber=" + parkingNumber +
                ", carRegistrationNumber='" + carRegistrationNumber + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                ", dateOfDeparture=" + dateOfDeparture +
                '}';
    }
}
