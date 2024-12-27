package com.villacity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ClasePruebaModel {

    public enum Estado{
        solicitada,
        proceso,
        finalizada
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClasePrueba;

    @Future
    @Column
    private LocalDate fecha;

    @Column
    private String diaSemana;


    @Column
    @Enumerated(EnumType.STRING)
    private Estado estado;

    //CONEXION CON ALUMNO
    @OneToOne(mappedBy = "clasePruebaModel")
    private AlumnoModel alumnoModel;


    private DayOfWeek convertirDiaSemana(String diaSemana) {
        switch (diaSemana.toLowerCase()) {
            case "lunes": return DayOfWeek.MONDAY;
            case "martes": return DayOfWeek.TUESDAY;
            case "miércoles": return DayOfWeek.WEDNESDAY;
            case "jueves": return DayOfWeek.THURSDAY;
            case "viernes": return DayOfWeek.FRIDAY;
            case "sábado": return DayOfWeek.SATURDAY;
            case "domingo": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Día de la semana no válido: " + diaSemana);
        }
    }
}
