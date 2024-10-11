package com.example.Kursova.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "solidworks_models")
public class SolidWorksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name", unique = true, nullable = false)
    private String modelName;

    @Lob
    @Column(name = "model_data", columnDefinition = "BLOB")
    private byte[] modelData;

    @Column(name = "description")
    private String description;

    public SolidWorksModel() {}

    public SolidWorksModel(String modelName, byte[] modelData, String description) {
        this.modelName = modelName;
        this.modelData = modelData;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public byte[] getModelData() {
        return modelData;
    }

    public void setModelData(byte[] modelData) {
        this.modelData = modelData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



