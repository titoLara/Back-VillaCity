package com.villacity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Getter
@Setter
public class ApoderadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_apoderado;

    @Column
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String nombreApoderado;

    @Column
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String apellidoApoderado;

    @Column
    @NotBlank(message = "Este Campo debe estar Completado")
    private String dni;

    @Column
    @NotBlank
    @Size(min = 9, max = 9, message = "El numero de Telefono debe tener 9 digitos")
    private String telefono;

    @Column
    @Email
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String correo;


    //CONEXION CON ALUMNO
    @OneToMany(mappedBy = "apoderadoModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AlumnoModel> alumnoModels;
}
