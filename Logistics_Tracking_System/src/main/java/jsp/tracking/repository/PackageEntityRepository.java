package jsp.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.tracking.entity.PackageEntity;

public interface PackageEntityRepository extends JpaRepository<PackageEntity, Integer> {

}
