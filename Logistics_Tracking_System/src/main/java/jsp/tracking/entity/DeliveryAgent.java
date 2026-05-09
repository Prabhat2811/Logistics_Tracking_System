package jsp.tracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeliveryAgent {
	private Integer id;
	private String name;
	@Column(unique = true)
	private Long contact;
	@Column(unique = true)
	private String vehicleNumber;
	private Boolean availabilityStatus;
	private Double rating;
}
