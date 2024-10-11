package com.example.Kursova.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Kursova.entity.SolidWorksModel;
@Repository
public interface SolidWorksModelRepository extends JpaRepository<SolidWorksModel, Long> {
    Optional<SolidWorksModel> findByModelName(String modelName);
}

