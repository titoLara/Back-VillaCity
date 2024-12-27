package com.villacity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class SedeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSede;

    @Column
    @NotBlank
    private String nombre;

    @Column
    @NotBlank
    private String direccion;

    //CONEXION CON ALUMNO
    @OneToMany(mappedBy = "sedeModel", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<AlumnoModel> alumnoModels;

    //CONEXION CON Profesor
    @ManyToMany
    @JoinTable(
            name = "sede_profesor",
            joinColumns = @JoinColumn(name = "sede_id"),
            inverseJoinColumns = @JoinColumn(name = "profesor_id")
    )
    private List<ProfesorModel> profesorModels;

    //CONEXION CON HORARIO
    @OneToMany(mappedBy = "sedeModel", cascade = CascadeType.ALL)
    private List<HorarioModel> horarioModel;
}
