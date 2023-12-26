package org.example.entities;
import jakarta.persistence.*;

@Entity
public class Cars {
    @Id
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "prices")
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Cars() {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
