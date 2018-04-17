package br.com.welson.carsclassifieds.endpoint.v1;

import br.com.welson.carsclassifieds.persistence.model.user.Admin;
import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import br.com.welson.carsclassifieds.persistence.model.user.Seller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EndpointUtil {

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public Admin extractAdminFromToken() {
        return extractApplicationUserFromToken().getAdmin();
    }

    public Seller extractSellerFromToken() {
        return extractApplicationUserFromToken().getSeller();
    }

    public ApplicationUser extractApplicationUserFromToken() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
