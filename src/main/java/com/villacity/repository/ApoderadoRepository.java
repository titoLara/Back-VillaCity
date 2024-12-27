package com.villacity.repository;

import com.villacity.model.ApoderadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoderadoRepository extends JpaRepository<ApoderadoModel, Long> {
}
