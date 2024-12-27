package com.villacity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ProfesorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProfesor;

    @Column
    @NotBlank
    private String nombreProfesor;

    @Column
    @NotBlank
    private String apellidoProfesor;

    @Column
    @NotBlank
    @Size(min = 8, max = 8, message = "El numero de Telefono debe tener 9 digitos")
    private String Dni;

    @Column
    @NotBlank
        @Size(min = 9, max = 9, message = "El numero de Telefono debe tener 9 digitos")
    private String telefono;

    @Column
    @Email
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String correo;

    //CONEXION CON SEDE
    @ManyToMany(mappedBy = "profesorModels")
    private List<SedeModel> sedeModels;

    //CONEXION CON ALUMNO
    @OneToMany(mappedBy = "profesorModel")
    private List<AlumnoModel> alumnoModels;

}
