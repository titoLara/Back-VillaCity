package com.villacity.dto.Alumno;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlumnoDTO {

    private Long id;


    @NotBlank
    private String sede;

    @Column
    private String profesor;

    @NotBlank
    private String nombreAlumno;

    @NotBlank
    private String apellidoAlumno;

    @NotBlank
    private LocalTime horaInicio;

    @NotBlank
    private LocalTime horaFin;

    @Size(min = 2, max = 2)
    private String condicion_medica;

    @Past
    private LocalDate fecha_nacimiento;

    @NotBlank(message = "El DNI no debe estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    private String dni;

    private ApoderadoAlumnoDTO apoderado;


}
