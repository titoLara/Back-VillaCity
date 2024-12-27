package com.villacity.repository;

import com.villacity.model.AlumnoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoModel, Long> {
}
