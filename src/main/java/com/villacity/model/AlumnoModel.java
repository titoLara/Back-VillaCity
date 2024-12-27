package com.villacity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class AlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alumno;

    @Column
    private String sede;

    @Column
    private String nombreprofesor;

    @Column
    @NotBlank
    private String nombreAlumno;

    @Column
    @NotBlank
    private String apellidoAlumno;

    @Column
    private List<String> dias;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horafin;

    @Column
    @Size(min = 2, max = 2)
    private String condicion_medica;

    @Column
    @Past
    private LocalDate fecha_nacimiento;

    @Column
    @NotBlank(message = "El DNI no debe estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    private String dni;


    //CONEXION CON APODERADO
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apoderado_id")
    private ApoderadoModel apoderadoModel;

    //CONEXION CON SEDE
    @ManyToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
    @JoinColumn(name = "sede_id")
    private SedeModel sedeModel;

    //CONEXION CON CLASE PRUEBA
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clasePrueba_id")
    private ClasePruebaModel clasePruebaModel;

    //CONEXION CON PROFESOR
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesor_id")
    private ProfesorModel profesorModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario_id")
    private HorarioModel horarioModel;

}
