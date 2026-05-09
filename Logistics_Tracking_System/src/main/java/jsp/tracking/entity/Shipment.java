package jsp.tracking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@CreationTimestamp
	private LocalDate deliveryDate;
	
	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;
	
}
