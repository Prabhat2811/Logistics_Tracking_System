package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {

}
