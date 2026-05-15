package jsp.tracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.PackageEntity;

public interface PackageEntityRepository extends JpaRepository<PackageEntity, Integer> {

	Optional<PackageEntity> findByShipment_id(Integer id);

}
