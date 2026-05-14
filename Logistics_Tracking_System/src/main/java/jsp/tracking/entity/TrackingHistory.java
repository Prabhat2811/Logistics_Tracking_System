package jsp.tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jsp.tracking.dto.Status;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrackingHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String location;
	private String remark;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	 @ManyToOne
	 @JoinColumn(name = "shipment_id")
	 private Shipment shipment;
}
