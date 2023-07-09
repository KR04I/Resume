package DB_hotel;

import jakarta.persistence.*;

import java.sql.Date;

@org.hibernate.annotations.NamedQuery(name = "WorkinghoursEntity.byPassportId", query = "From WorkinghoursEntity wo where wo.staffByPassportId =?1")
@org.hibernate.annotations.NamedQuery(name = "WorkinghoursEntity.byDateStart", query = "FROM WorkinghoursEntity wo where upper(cast(wo.datestart as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "WorkinghoursEntity.byDateEnd", query = "FROM WorkinghoursEntity wo where upper(cast(wo.dateend as string)) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "WorkinghoursEntity.all", query = "From WorkinghoursEntity wo")

@Entity
@Table(name = "workinghours", schema = "public", catalog = "hotel")
public class WorkinghoursEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "datestart")
    private Date datestart;
    @Basic
    @Column(name = "dateend")
    private Date dateend;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private StaffEntity staffByPassportId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkinghoursEntity that = (WorkinghoursEntity) o;

        if (id != that.id) return false;
        if (datestart != null ? !datestart.equals(that.datestart) : that.datestart != null) return false;
        if (dateend != null ? !dateend.equals(that.dateend) : that.dateend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (datestart != null ? datestart.hashCode() : 0);
        result = 31 * result + (dateend != null ? dateend.hashCode() : 0);
        return result;
    }

    public StaffEntity getStaffByPassportId() {
        return staffByPassportId;
    }

    public void setStaffByPassportId(StaffEntity staffByPassportId) {
        this.staffByPassportId = staffByPassportId;
    }

    @Override
    public String toString() {
        return "WorkinghoursEntity{" +
                "id=" + id +
                ", datestart=" + datestart +
                ", dateend=" + dateend +
                ", staffByPassportId=" + staffByPassportId +
                '}';
    }
}