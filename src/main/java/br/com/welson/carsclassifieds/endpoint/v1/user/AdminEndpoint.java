package br.com.welson.carsclassifieds.endpoint.v1.user;

import br.com.welson.carsclassifieds.endpoint.v1.EndpointUtil;
import br.com.welson.carsclassifieds.exception.BadRequestException;
import br.com.welson.carsclassifieds.exception.ForbiddenException;
import br.com.welson.carsclassifieds.exception.NotFoundException;
import br.com.welson.carsclassifieds.persistence.model.user.Admin;
import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import br.com.welson.carsclassifieds.persistence.repositoty.user.AdminRepository;
import br.com.welson.carsclassifieds.persistence.repositoty.user.ApplicationUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/admin")
public class AdminEndpoint {

    private final EndpointUtil endpointUtil;
    private final AdminRepository adminRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public AdminEndpoint(EndpointUtil endpointUtil, AdminRepository adminRepository, ApplicationUserRepository applicationUserRepository) {
        this.endpointUtil = endpointUtil;
        this.adminRepository = adminRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAdmin() {
        return new ResponseEntity<>(endpointUtil.extractAdminFromToken(), OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Admin admin) {
        adminRepository.findById(admin.getId()).orElseThrow(() -> new NotFoundException("Admin don't exists"));
        verifyIfAdminHasPermission(admin);
        adminRepository.save(admin);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        applicationUserRepository.delete(endpointUtil.extractApplicationUserFromToken());
        return new ResponseEntity<>(OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ApplicationUser applicationUser) {
        verifyIfAdminIsNotNull(applicationUser);
        applicationUser.setPassword(endpointUtil.encodePassword(applicationUser.getPassword()));
        ApplicationUser applicationUserSaved = applicationUserRepository.save(applicationUser);
        applicationUserSaved.setPassword(null);
        return new ResponseEntity<>(applicationUserSaved, CREATED);
    }

    private void verifyIfAdminHasPermission(Admin admin) {
        if (admin != endpointUtil.extractAdminFromToken()) {
            throw new ForbiddenException("You don't have permission");
        }
    }

    private void verifyIfAdminIsNotNull(ApplicationUser applicationUser) {
        if (!(applicationUser.getAdmin() != null && applicationUser.getSeller() == null)) {
            throw new BadRequestException("Admin cannot be null");
        }
    }
}
