package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {


    @Id @GeneratedValue
    @Column(name = "OWNER_ID")
    private long id;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    public List<Cars> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<Cars> ownedCars) {
        this.ownedCars = ownedCars;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cars> ownedCars;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    public Owner(String ownerName) {
        this.ownerName = ownerName;
    }
}
