package br.com.welson.carsclassifieds.persistence.model.vehicle;

import br.com.welson.carsclassifieds.persistence.model.user.Seller;
import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Vehicle extends AbstractEntity {

    @ManyToOne(optional = false)
    @NotNull
    private Model model;
    @NotNull
    private Integer yearManufacture;
    @NotNull
    private Integer modelYear;
    @NotNull
    @Column(precision = 2)
    private Double price;
    @NotNull
    private Long miles;
    @NotNull
    @ManyToOne(optional = false)
    private Seller seller;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(Integer yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMiles() {
        return miles;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
