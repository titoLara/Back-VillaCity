package com.villacity.dto.Alumno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApoderadoAlumnoDTO {

    @NotBlank(message = "El campo no puede estar vacio!!")
    private String nombreApoderado;

    @NotBlank(message = "El campo no puede estar vacio!!")
    private String apellidoApoderado;

    @NotBlank
    @Size(min = 9, max = 9, message = "El numero de telefono debe tener 9 digitos")
    private String telefono;

}
