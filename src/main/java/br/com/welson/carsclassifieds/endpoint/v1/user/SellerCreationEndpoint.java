package br.com.welson.carsclassifieds.endpoint.v1.user;

import br.com.welson.carsclassifieds.endpoint.v1.EndpointUtil;
import br.com.welson.carsclassifieds.exception.BadRequestException;
import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import br.com.welson.carsclassifieds.persistence.repositoty.user.ApplicationUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/account")
public class SellerCreationEndpoint {

    private final ApplicationUserRepository applicationUserRepository;
    private final EndpointUtil endpointUtil;

    public SellerCreationEndpoint(ApplicationUserRepository applicationUserRepository, EndpointUtil endpointUtil) {
        this.applicationUserRepository = applicationUserRepository;
        this.endpointUtil = endpointUtil;
    }

    @PostMapping(path = "seller")
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody ApplicationUser applicationUser) {
        verifyIfSellerIsNotNull(applicationUser);
        applicationUser.setPassword(endpointUtil.encodePassword(applicationUser.getPassword()));
        ApplicationUser applicationUserSaved = applicationUserRepository.save(applicationUser);
        return new ResponseEntity<>(applicationUserSaved, CREATED);
    }

    private void verifyIfSellerIsNotNull(ApplicationUser applicationUser) {
        if (!(applicationUser.getAdmin() == null && applicationUser.getSeller() != null)) {
            throw new BadRequestException("Seller cannot be null");
        }
    }
}
