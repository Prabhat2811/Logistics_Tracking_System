package jsp.tracking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jsp.tracking.dto.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer trackingNumber;

    private String source;
    private String destination;
    private Double weight;

    @CreationTimestamp
    private LocalDateTime shipmentDate;

    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private DeliveryAgent deliveryAgent;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private PackageEntity packageEntity;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<TrackingHistory> trackingHistories;
}