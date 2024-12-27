package com.villacity.repository;

import com.villacity.model.HorarioModel;
import com.villacity.model.SedeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioModel, Long> {
    Optional<HorarioModel> findByHoraInicio(LocalTime horaInicio);
    Optional<HorarioModel> findByHoraFin(LocalTime horaFin);
    List<HorarioModel> findBySede(String sede);
}
