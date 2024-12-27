package com.villacity.repository;

import com.villacity.model.ProfesorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<ProfesorModel, Long> {
    Optional<ProfesorModel> findByNombreProfesor(String nombreProfesor);
}
