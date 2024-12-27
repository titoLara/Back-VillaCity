package com.villacity.dto.ClasePrueba;

import com.villacity.model.ClasePruebaModel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoClaseDTO {

    @Column
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String nombreApoderado;

    @Column
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String apellidoApoderado;

    @Column
    @NotBlank
    @Size(min = 9, max = 9, message = "El numero de Telefono debe tener 9 digitos")
    private String telefono;

    @Column
    @Email
    @NotBlank(message = "El Campo No Puede Estar Vacio!!")
    private String correo;


}
