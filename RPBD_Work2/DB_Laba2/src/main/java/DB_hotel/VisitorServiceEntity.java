package DB_hotel;

import jakarta.persistence.*;


@org.hibernate.annotations.NamedQuery(name = "VisitorServiceEntity.byQuantity", query = "From VisitorServiceEntity vs where vs.quantity =?1")
@org.hibernate.annotations.NamedQuery(name = "VisitorServiceEntity.byServiceId", query = "From VisitorServiceEntity vs where vs.serviceByServiceId =?1")
@org.hibernate.annotations.NamedQuery(name = "VisitorServiceEntity.byVisitorId", query = "From VisitorServiceEntity vs where vs.visitorByVisitorId =?1")
@org.hibernate.annotations.NamedQuery(name = "VisitorServiceEntity.all", query = "From VisitorServiceEntity vs")
@Entity
@Table(name = "visitor_service", schema = "public", catalog = "hotel")
public class VisitorServiceEntity {
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @Id
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private ServiceEntity serviceByServiceId;
    @ManyToOne
    @Id
    @JoinColumn(name = "visitor_id", referencedColumnName = "id")
    private VisitorEntity visitorByVisitorId;





    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorServiceEntity that = (VisitorServiceEntity) o;


        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }


    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }

    public VisitorEntity getVisitorByVisitorId() {
        return visitorByVisitorId;
    }

    public void setVisitorByVisitorId(VisitorEntity visitorByVisitorId) {
        this.visitorByVisitorId = visitorByVisitorId;
    }

    @Override
    public String toString() {
        return "VisitorServiceEntity{" +
                "quantity=" + quantity +
                ", serviceByServiceId=" + serviceByServiceId +
                ", visitorByVisitorId=" + visitorByVisitorId +
                '}';
    }
}
