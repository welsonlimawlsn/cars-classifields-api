package br.com.welson.carsclassifieds.persistence.repositoty.vehicle;

import br.com.welson.carsclassifieds.persistence.model.user.Seller;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByIdAndSeller(Long id, Seller seller);
    Iterable<Vehicle> findBySeller(Seller seller);
}
