package br.com.welson.carsclassifieds.endpoint.v1.vehicle;

import br.com.welson.carsclassifieds.exception.ExceptionEndpointUtils;
import br.com.welson.carsclassifieds.exception.NotFoundException;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Brand;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.BrandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/admin/brands")
public class BrandEndpoint {

    private final BrandRepository brandRepository;
    private final ExceptionEndpointUtils exceptionEndpointUtils;

    public BrandEndpoint(BrandRepository brandRepository, ExceptionEndpointUtils exceptionEndpointUtils) {
        this.brandRepository = brandRepository;
        this.exceptionEndpointUtils = exceptionEndpointUtils;
    }

    @GetMapping
    public ResponseEntity<?> listAllByName(@RequestParam(name = "name", defaultValue = "") String name) {
        return new ResponseEntity<>(brandRepository.findByName(name), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand don't exists")), OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Brand brand) {
        return new ResponseEntity<>(brandRepository.save(brand), CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Brand brand) {
        exceptionEndpointUtils.throwNotFoundExceptionIfBrandDoNotExists(brand.getId());
        brandRepository.save(brand);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        brandRepository.delete(exceptionEndpointUtils.throwNotFoundExceptionIfBrandDoNotExists(id));
        return new ResponseEntity<>(OK);
    }
}
