package br.com.welson.carsclassifieds.persistence.model.vehicle;

import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Brand extends AbstractEntity {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
