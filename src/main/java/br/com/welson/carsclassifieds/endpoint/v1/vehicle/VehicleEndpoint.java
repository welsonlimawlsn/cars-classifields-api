package br.com.welson.carsclassifieds.endpoint.v1.vehicle;

import br.com.welson.carsclassifieds.endpoint.v1.EndpointUtil;
import br.com.welson.carsclassifieds.exception.ExceptionEndpointUtils;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Vehicle;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/seller/vehicles")
public class VehicleEndpoint {

    private final VehicleRepository vehicleRepository;
    private final ExceptionEndpointUtils exceptionEndpointUtils;
    private final EndpointUtil endpointUtil;

    public VehicleEndpoint(VehicleRepository vehicleRepository, ExceptionEndpointUtils exceptionEndpointUtils, EndpointUtil endpointUtil) {
        this.vehicleRepository = vehicleRepository;
        this.exceptionEndpointUtils = exceptionEndpointUtils;
        this.endpointUtil = endpointUtil;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Vehicle vehicle) {
        exceptionEndpointUtils.throwNotFoundExceptionIfModelDoNotExists(vehicle.getModel().getId());
        vehicle.setSeller(endpointUtil.extractSellerFromToken());
        return new ResponseEntity<>(vehicleRepository.save(vehicle), CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Vehicle vehicle) {
        exceptionEndpointUtils.throwNotFoundExceptionIfVehicleDoNotExists(vehicle.getId());
        exceptionEndpointUtils.throwNotFoundExceptionIfModelDoNotExists(vehicle.getModel().getId());
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vehicleRepository.delete(exceptionEndpointUtils.throwNotFoundExceptionIfVehicleDoNotExists(id));
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(vehicleRepository.findBySeller(endpointUtil.extractSellerFromToken()), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(exceptionEndpointUtils.throwNotFoundExceptionIfVehicleDoNotExists(id), OK);
    }
}
