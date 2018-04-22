package br.com.welson.carsclassifieds.persistence.repositoty.vehicle;

import br.com.welson.carsclassifieds.persistence.model.vehicle.Brand;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Iterable<Model> findAllByBrand(Brand brand);
}
