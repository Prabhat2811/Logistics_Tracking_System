package jsp.tracking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Warehouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String location;
	private Integer capacity;
	@Column(unique = true)
	private Long contact;
	
	 @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
	    private List<Shipment> shipments;
}
