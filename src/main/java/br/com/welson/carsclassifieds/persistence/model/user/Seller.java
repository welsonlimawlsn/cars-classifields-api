package br.com.welson.carsclassifieds.persistence.model.user;

import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Seller extends AbstractEntity {

    @NotEmpty
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String identificationDocument;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }
}
