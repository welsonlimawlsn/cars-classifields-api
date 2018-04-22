package br.com.welson.carsclassifieds.exception;

import br.com.welson.carsclassifieds.endpoint.v1.EndpointUtil;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Brand;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Model;
import br.com.welson.carsclassifieds.persistence.model.vehicle.Vehicle;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.BrandRepository;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.ModelRepository;
import br.com.welson.carsclassifieds.persistence.repositoty.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExceptionEndpointUtils {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final VehicleRepository vehicleRepository;
    private final EndpointUtil endpointUtil;

    public ExceptionEndpointUtils(BrandRepository brandRepository, ModelRepository modelRepository, VehicleRepository vehicleRepository, EndpointUtil endpointUtil) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.vehicleRepository = vehicleRepository;
        this.endpointUtil = endpointUtil;
    }

    public Brand throwNotFoundExceptionIfBrandDoNotExists(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand don't exists"));
    }

    public Model throwNotFoundExceptionIfModelDoNotExists(Long id) {
        return modelRepository.findById(id).orElseThrow(() -> new NotFoundException("Model don't exists"));
    }

    public Vehicle throwNotFoundExceptionIfVehicleDoNotExists(Long id) {
        return vehicleRepository.findByIdAndSeller(id, endpointUtil.extractSellerFromToken()).orElseThrow(() -> new NotFoundException("Vehicle don't exists"));
    }
}
