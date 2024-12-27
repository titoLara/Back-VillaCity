package com.villacity.dto.Sede;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {

    private Long idSede;

    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    private List<AlumnoSedeDTO> alumnoSedeDTOS;

    private List<HorarioSedeDTO> horarioSedeDTOS;
}
