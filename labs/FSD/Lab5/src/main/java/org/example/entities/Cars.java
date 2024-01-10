package org.example.entities;
import jakarta.persistence.*;

@Entity
public class Cars {
    @Id @GeneratedValue
    @Column(name = "CAR_ID")
    private int id;

    @Column(name = "CAR_NAME")
    private String carName;

    @Column(name = "CAR_PRICE")
    private int carPrice;

//    public Owner getCarOwner() {
//        return carOwner;
//    }
//
//    public void setCarOwner(Owner carOwner) {
//        this.carOwner = carOwner;
//    }
//
//    @JoinColumn(name = "OWNER_FK")
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Owner carOwner;

    public Engine getEngineDetails() {
        return engineDetails;
    }

    public void setEngineDetails(Engine engineDetails) {
        this.engineDetails = engineDetails;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ENGINE_FK")
    private Engine engineDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }
}
