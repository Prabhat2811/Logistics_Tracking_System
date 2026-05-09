package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
