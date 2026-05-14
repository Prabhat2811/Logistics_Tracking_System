package jsp.tracking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jsp.tracking.dto.PaymentMethod;
import jsp.tracking.dto.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double amount;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@CreationTimestamp
	private LocalDateTime paymentDateTime;
	
	 @OneToOne
	 @JoinColumn(name = "shipment_id")
	 private Shipment shipment;
}
