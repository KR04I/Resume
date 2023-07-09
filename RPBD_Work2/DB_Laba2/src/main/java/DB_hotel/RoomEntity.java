package DB_hotel;

import jakarta.persistence.*;

import java.util.Collection;

@org.hibernate.annotations.NamedQuery(name = "RoomEntity.byRoomNumber", query = "From RoomEntity ro where ro.roomNumber =?1")
@org.hibernate.annotations.NamedQuery(name = "RoomEntity.byLevel", query = "From RoomEntity ro where ro.level =?1")
@org.hibernate.annotations.NamedQuery(name = "RoomEntity.byNumberAvailableSeats", query = "From RoomEntity ro where ro.numberAvailableSeats =?1")
@org.hibernate.annotations.NamedQuery(name = "RoomEntity.byLivingPeople", query = "From RoomEntity ro where ro.livingPeople =?1")
@org.hibernate.annotations.NamedQuery(name = "RoomEntity.all", query = "From RoomEntity ro")
@Entity
@Table(name = "room", schema = "public", catalog = "hotel")
public class RoomEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "room_number")
    private Integer roomNumber;
    @Basic
    @Column(name = "level")
    private Integer level;
    @Basic
    @Column(name = "number_available_seats")
    private Integer numberAvailableSeats;
    @Basic
    @Column(name = "living_people")
    private Integer livingPeople;
    @OneToMany(mappedBy = "roomByRoomNumber")
    private Collection<RegistrationEntity> registrationsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNumberAvailableSeats() {
        return numberAvailableSeats;
    }

    public void setNumberAvailableSeats(Integer numberAvailableSeats) {
        this.numberAvailableSeats = numberAvailableSeats;
    }

    public Integer getLivingPeople() {
        return livingPeople;
    }

    public void setLivingPeople(Integer livingPeople) {
        this.livingPeople = livingPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (roomNumber != null ? !roomNumber.equals(that.roomNumber) : that.roomNumber != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (numberAvailableSeats != null ? !numberAvailableSeats.equals(that.numberAvailableSeats) : that.numberAvailableSeats != null)
            return false;
        if (livingPeople != null ? !livingPeople.equals(that.livingPeople) : that.livingPeople != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (numberAvailableSeats != null ? numberAvailableSeats.hashCode() : 0);
        result = 31 * result + (livingPeople != null ? livingPeople.hashCode() : 0);
        return result;
    }

    public Collection<RegistrationEntity> getRegistrationsById() {
        return registrationsById;
    }

    public void setRegistrationsById(Collection<RegistrationEntity> registrationsById) {
        this.registrationsById = registrationsById;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", level=" + level +
                ", numberAvailableSeats=" + numberAvailableSeats +
                ", livingPeople=" + livingPeople +
                '}';
    }
}
