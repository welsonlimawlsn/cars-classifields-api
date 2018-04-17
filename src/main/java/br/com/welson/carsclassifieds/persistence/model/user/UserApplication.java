package br.com.welson.carsclassifieds.persistence.model.user;

import br.com.welson.carsclassifieds.persistence.model.util.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserApplication extends AbstractEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;
    @NotEmpty
    @Column(nullable = false)
    private String password;
    @OneToOne
    private Admin admin;
    @OneToOne
    private Seller seller;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}
