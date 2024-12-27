package com.villacity.dto.ClasePrueba;

import com.villacity.model.ClasePruebaModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClasePruebaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClasePrueba;

    // Campo para recibir el día de la semana
    private String diaSemana;


    @Column
    @Enumerated(EnumType.STRING)
    private ClasePruebaModel.Estado estado;

    private AlumnoClaseDTO alumnoClaseDTO;

}
