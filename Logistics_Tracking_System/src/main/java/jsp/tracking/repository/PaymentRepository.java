package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
