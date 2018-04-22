package br.com.welson.carsclassifieds.persistence.model.vehicle;

import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Brand extends AbstractEntity {

    @NotEmpty
    private String name;
    @OneToMany(mappedBy = "brand",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Model> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
