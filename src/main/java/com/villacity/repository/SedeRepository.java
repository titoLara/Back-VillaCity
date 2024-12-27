package com.villacity.repository;

import com.villacity.model.SedeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<SedeModel, Long> {
    Optional<SedeModel> findByNombre(String nombre);
}
