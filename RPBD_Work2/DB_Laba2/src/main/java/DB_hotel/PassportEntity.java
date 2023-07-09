package DB_hotel;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;


@org.hibernate.annotations.NamedQuery(name = "PassportEntity.byAddress", query = "From PassportEntity pa where upper(pa.address) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "PassportEntity.byNumber", query = "From PassportEntity pa where pa.number =?1")
@org.hibernate.annotations.NamedQuery(name = "PassportEntity.byDateExtradition", query = "FROM PassportEntity pa where upper(cast(pa.dateExtradition as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "PassportEntity.byPassportIssuance", query = "From PassportEntity pa where upper(pa.passportIssuance) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "PassportEntity.all", query = "From PassportEntity pa")
@Entity
@Table(name = "passport", schema = "public", catalog = "hotel")
public class PassportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "date_extradition")
    private Date dateExtradition;
    @Basic
    @Column(name = "passport_issuance")
    private String passportIssuance;
    @OneToMany(mappedBy = "passportByPassportId")
    private Collection<StaffEntity> staffById;
    @OneToMany(mappedBy = "passportByPassportId")
    private Collection<VisitorEntity> visitorsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDateExtradition() {
        return dateExtradition;
    }

    public void setDateExtradition(Date dateExtradition) {
        this.dateExtradition = dateExtradition;
    }

    public String getPassportIssuance() {
        return passportIssuance;
    }

    public void setPassportIssuance(String passportIssuance) {
        this.passportIssuance = passportIssuance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassportEntity that = (PassportEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (dateExtradition != null ? !dateExtradition.equals(that.dateExtradition) : that.dateExtradition != null)
            return false;
        if (passportIssuance != null ? !passportIssuance.equals(that.passportIssuance) : that.passportIssuance != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (dateExtradition != null ? dateExtradition.hashCode() : 0);
        result = 31 * result + (passportIssuance != null ? passportIssuance.hashCode() : 0);
        return result;
    }

    public Collection<StaffEntity> getStaffById() {
        return staffById;
    }

    public void setStaffById(Collection<StaffEntity> staffById) {
        this.staffById = staffById;
    }

    public Collection<VisitorEntity> getVisitorsById() {
        return visitorsById;
    }

    public void setVisitorsById(Collection<VisitorEntity> visitorsById) {
        this.visitorsById = visitorsById;
    }

    @Override
    public String toString() {
        return "PassportEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", dateExtradition=" + dateExtradition +
                ", passportIssuance='" + passportIssuance + '\'' +
                ", staffById=" + staffById +
                ", visitorsById=" + visitorsById +
                '}';
    }
}
