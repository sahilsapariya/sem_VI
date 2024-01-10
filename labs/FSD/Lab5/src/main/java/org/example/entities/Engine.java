package org.example.entities;

import jakarta.persistence.*;

@Entity
public class Engine {
    @Id @GeneratedValue
    @Column(name = "ENGINE_ID")
    private long id;

    @Column(name = "ENGINE_NAME")
    private String engineName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public Engine() {

    }
}
