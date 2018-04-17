package br.com.welson.carsclassifieds.persistence.repositoty.user;

import br.com.welson.carsclassifieds.persistence.model.user.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
