package br.com.welson.carsclassifieds.persistence.model.vehicle;

import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Model extends AbstractEntity {

    @ManyToOne(optional = false)
    @NotNull
    private Brand brand;
    @NotEmpty
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private TypeOfVehicle typeOfVehicle;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

}
