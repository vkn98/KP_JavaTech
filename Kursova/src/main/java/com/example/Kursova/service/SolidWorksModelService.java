package com.example.Kursova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Kursova.entity.SolidWorksModel;
import com.example.Kursova.repository.SolidWorksModelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolidWorksModelService {

    @Autowired
    private SolidWorksModelRepository repository;

    public List<SolidWorksModel> getAllModels() {
        return repository.findAll();
    }

    public Optional<SolidWorksModel> getModelById(Long id) {
        return repository.findById(id);
    }

    public SolidWorksModel saveModel(SolidWorksModel model) {
        return repository.save(model);
    }

    public void deleteModel(Long id) {
        repository.deleteById(id);
    }

    public Optional<SolidWorksModel> getModelByName(String modelName) {
        return repository.findByModelName(modelName);
    }
}
