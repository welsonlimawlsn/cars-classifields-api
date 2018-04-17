package br.com.welson.carsclassifieds.endpoint.v1.user;

import br.com.welson.carsclassifieds.endpoint.v1.EndpointUtil;
import br.com.welson.carsclassifieds.exception.ForbiddenException;
import br.com.welson.carsclassifieds.exception.NotFoundException;
import br.com.welson.carsclassifieds.persistence.model.user.Seller;
import br.com.welson.carsclassifieds.persistence.repositoty.user.ApplicationUserRepository;
import br.com.welson.carsclassifieds.persistence.repositoty.user.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/seller")
public class SellerEndpoint {

    private final EndpointUtil endpointUtil;
    private final SellerRepository sellerRepository;
    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public SellerEndpoint(EndpointUtil endpointUtil, SellerRepository sellerRepository, ApplicationUserRepository applicationUserRepository) {
        this.endpointUtil = endpointUtil;
        this.sellerRepository = sellerRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping
    public ResponseEntity<?> getSeller() {
        return new ResponseEntity<>(endpointUtil.extractSellerFromToken(), OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Seller seller) {
        sellerRepository.findById(seller.getId()).orElseThrow(() -> new NotFoundException("Seller don't exists"));
        verifyIfUserHasPermission(seller);
        sellerRepository.save(seller);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete() {
        applicationUserRepository.delete(endpointUtil.extractApplicationUserFromToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfUserHasPermission(Seller seller) {
        if (seller != endpointUtil.extractSellerFromToken()) {
            throw new ForbiddenException("You don't have permission");
        }
    }
}
