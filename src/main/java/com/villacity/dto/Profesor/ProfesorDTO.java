package com.villacity.dto.Profesor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({ "idProfesor", "nombreProfesor", "apellidoProfesor", "dni", "telefono", "correo", "alumnoProfesorDTOS" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO {

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

    private List<AlumnoProfesorDTO> alumnoProfesorDTOS;

}
