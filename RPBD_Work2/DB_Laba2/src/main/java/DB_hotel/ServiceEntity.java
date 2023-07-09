package DB_hotel;

import jakarta.persistence.*;

import java.util.Collection;

@org.hibernate.annotations.NamedQuery(name = "ServiceEntity.byName", query = "From ServiceEntity se where upper(se.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "ServiceEntity.byPrice", query = "From ServiceEntity se where se.price =?1")
@org.hibernate.annotations.NamedQuery(name = "ServiceEntity.all", query = "From ServiceEntity se")
@Entity
@Table(name = "service", schema = "public", catalog = "hotel")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private Integer price;
    @OneToMany(mappedBy = "serviceByServiceId")
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEntity that = (ServiceEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public Collection<VisitorServiceEntity> getVisitorServicesById() {
        return visitorServicesById;
    }

    public void setVisitorServicesById(Collection<VisitorServiceEntity> visitorServicesById) {
        this.visitorServicesById = visitorServicesById;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
