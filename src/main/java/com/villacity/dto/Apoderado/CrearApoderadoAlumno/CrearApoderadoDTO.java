package com.villacity.dto.Apoderado.CrearApoderadoAlumno;

import com.villacity.dto.Apoderado.AlumnoApoderadoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrearApoderadoDTO {

    private Long id_apoderado;

    @NotBlank(message = "El campo no puede estar vacio!!")
    private String nombreApoderado;

    @NotBlank(message = "El campo no puede estar vacio!!")
    private String apellidoApoderado;

    @NotBlank(message = "Este Campo debe estar Completado")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    private String dni;

    @NotBlank
    @Size(min = 9, max = 9, message = "El numero de telefono debe tener 9 digitos")
    private String telefono;

    @Email
    @NotBlank(message = "El campo no puede estar vacio!!")
    private String correo;

    private List<CrearAlumnoApoderadoDTO> CrearAlumnoModels ;

}
