package jsp.tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jsp.tracking.dto.PackageType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PackageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private PackageType packageType;
	private Boolean fragile;
	private String dimension;
	
	 @OneToOne
	 @JoinColumn(name = "shipment_id")
	 private Shipment shipment;
}
