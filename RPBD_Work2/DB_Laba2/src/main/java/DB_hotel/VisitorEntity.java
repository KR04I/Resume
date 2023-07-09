package DB_hotel;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.byPassportId", query = "From VisitorEntity ve where ve.passportByPassportId =?1")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.byName", query = "From VisitorEntity ve where upper(ve.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.bySurname", query = "From VisitorEntity ve where upper(ve.surname) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.byPatronymic", query = "From VisitorEntity ve where upper(ve.patronymic) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.byBirthday", query = "FROM VisitorEntity ve where upper(cast(ve.birthday as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.byGender", query = "From VisitorEntity ve where ve.gender =?1")
@org.hibernate.annotations.NamedQuery(name = "VisitorEntity.all", query = "From VisitorEntity ve")
@Entity
@Table(name = "visitor", schema = "public", catalog = "hotel")
public class VisitorEntity {
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
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "gender")
    private Integer gender;
    @OneToMany(mappedBy = "visitorByVisitorId")
    private Collection<RegistrationEntity> registrationsById;
    @ManyToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private PassportEntity passportByPassportId;
    @OneToMany(mappedBy = "visitorByVisitorId")
    private Collection<VisitorServiceEntity> visitorServicesById;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorEntity that = (VisitorEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    public Collection<RegistrationEntity> getRegistrationsById() {
        return registrationsById;
    }

    public void setRegistrationsById(Collection<RegistrationEntity> registrationsById) {
        this.registrationsById = registrationsById;
    }

    public PassportEntity getPassportByPassportId() {
        return passportByPassportId;
    }

    public void setPassportByPassportId(PassportEntity passportByPassportId) {
        this.passportByPassportId = passportByPassportId;
    }

    public Collection<VisitorServiceEntity> getVisitorServicesById() {
        return visitorServicesById;
    }

    public void setVisitorServicesById(Collection<VisitorServiceEntity> visitorServicesById) {
        this.visitorServicesById = visitorServicesById;
    }

    @Override
    public String toString() {
        return "VisitorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                //", registrationsById=" + registrationsById +
                //", passportByPassportId=" + passportByPassportId +
                //", visitorServicesById=" + visitorServicesById +
                '}';
    }

}
