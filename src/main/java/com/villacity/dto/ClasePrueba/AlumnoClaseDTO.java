package com.villacity.dto.ClasePrueba;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "sede", "profesor", "nombreAlumno", "apellidoAlumno", "horaInicio","horaFin","condicion_medica", "fecha_nacimiento", "apoderadoClaseDTO" })
public class AlumnoClaseDTO {

    @Column
    private String sede;

    @Column
    private String profesor;

    @Column
    @NotBlank(message = "El nombre del alumno no puede estar vacío.")
    private String nombreAlumno;

    @Column
    @NotBlank(message = "El apellido del alumno no puede estar vacío.")
    private String apellidoAlumno;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaInicio;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaFin;


    @Column
    @Size(min = 2, max = 2, message = "La condición médica debe tener 2 caracteres.")
    private String condicion_medica;

    @Column
    @Past(message = "La fecha de nacimiento debe estar en el pasado.")
    private LocalDate fecha_nacimiento;

    private ApoderadoClaseDTO apoderadoClaseDTO;
}

