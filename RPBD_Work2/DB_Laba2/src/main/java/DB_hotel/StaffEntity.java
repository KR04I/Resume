package DB_hotel;

import jakarta.persistence.*;

import java.util.Collection;

@org.hibernate.annotations.NamedQuery(name = "StaffEntity.byPassportId", query = "From StaffEntity st where st.passportByPassportId =?1")
@org.hibernate.annotations.NamedQuery(name = "StaffEntity.byName", query = "From StaffEntity st where upper(st.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "StaffEntity.bySurname", query = "From StaffEntity st where upper(st.surname) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "StaffEntity.byPatronymic", query = "From StaffEntity st where upper(st.patronymic) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "StaffEntity.all", query = "From StaffEntity st")
@Entity
@Table(name = "staff", schema = "public", catalog = "hotel")
public class StaffEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @ManyToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private PassportEntity passportByPassportId;
    @OneToMany(mappedBy = "staffByPassportId")
    private Collection<WorkinghoursEntity> workinghoursById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (id != that.id) return false;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;

        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        return result;
    }

    public PassportEntity getPassportByPassportId() {
        return passportByPassportId;
    }

    public void setPassportByPassportId(PassportEntity passportByPassportId) {
        this.passportByPassportId = passportByPassportId;
    }

    public Collection<WorkinghoursEntity> getWorkinghoursById() {
        return workinghoursById;
    }

    public void setWorkinghoursById(Collection<WorkinghoursEntity> workinghoursById) {
        this.workinghoursById = workinghoursById;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
