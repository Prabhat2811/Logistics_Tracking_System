package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
