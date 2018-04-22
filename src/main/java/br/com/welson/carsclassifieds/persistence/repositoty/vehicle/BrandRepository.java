package br.com.welson.carsclassifieds.persistence.repositoty.vehicle;

import br.com.welson.carsclassifieds.persistence.model.vehicle.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Iterable<Brand> findByName(String name);
}
