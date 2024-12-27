package com.villacity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class HorarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100) // Example size constraint
    @Column
    private String sede;

    @NotNull
    @Column
    private List<String> dias;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaInicio;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaFin;

    // CONEXION CON SEDE
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private SedeModel sedeModel;

    // CONEXION CON HORARIO
    @OneToMany(mappedBy = "horarioModel", cascade = CascadeType.ALL) // Consider cascade type based on your requirements
    private List<AlumnoModel> alumnoModels;
}
