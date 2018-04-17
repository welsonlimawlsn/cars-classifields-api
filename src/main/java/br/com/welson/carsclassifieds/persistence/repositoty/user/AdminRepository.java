package br.com.welson.carsclassifieds.persistence.repositoty.user;

import br.com.welson.carsclassifieds.persistence.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
