package br.com.welson.carsclassifieds.persistence.repositoty.user;

import br.com.welson.carsclassifieds.persistence.model.user.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Long> {
}
