package br.com.welson.carsclassifieds.endpoint.v1.vehicle;

import br.com.welson.carsclassifieds.exception.ExceptionEndpointUtils;
import br.com.welson.carsclassifieds.exception.NotFoundException;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Model;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.BrandRepository;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.ModelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/admin/brands/models")
public class ModelEndpoint {

    private final ModelRepository modelRepository;
    private final ExceptionEndpointUtils exceptionEndpointUtils;

    public ModelEndpoint(ModelRepository modelRepository, ExceptionEndpointUtils exceptionEndpointUtils) {
        this.modelRepository = modelRepository;
        this.exceptionEndpointUtils = exceptionEndpointUtils;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Model model) {
        exceptionEndpointUtils.throwNotFoundExceptionIfBrandDoNotExists(model.getBrand().getId());
        return new ResponseEntity<>(modelRepository.save(model), CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listByBrand(@RequestParam(name = "brandId", defaultValue = "") Long brandId) {
        if(brandId == null) {
            return new ResponseEntity<>(modelRepository.findAll(), OK);
        }
        return new ResponseEntity<>(modelRepository.findAllByBrand(exceptionEndpointUtils.throwNotFoundExceptionIfBrandDoNotExists(brandId)), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listAll(@PathVariable Long id) {
        return new ResponseEntity<>(modelRepository.findById(id).orElseThrow(() -> new NotFoundException("Model don't exists")), OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Model model) {
        exceptionEndpointUtils.throwNotFoundExceptionIfModelDoNotExists(model.getId());
        modelRepository.save(model);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        modelRepository.delete(exceptionEndpointUtils.throwNotFoundExceptionIfModelDoNotExists(id));
        return new ResponseEntity<>(OK);
    }
}
