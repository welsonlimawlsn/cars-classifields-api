package br.com.welson.carsclassifieds.persistence.repositoty.user;

import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);
}
