package com.villacity.mapper.ClasePrueba;

import com.villacity.dto.ClasePrueba.AlumnoClaseDTO;
import com.villacity.dto.ClasePrueba.ClasePruebaDTO;
import com.villacity.model.ClasePruebaModel;

public class ClasePruebaMapper {

    public static ClasePruebaModel convertToEntityClase(ClasePruebaDTO dto){
        ClasePruebaModel clase = new ClasePruebaModel();
        clase.setIdClasePrueba(dto.getIdClasePrueba());
        clase.setDiaSemana(dto.getDiaSemana());
        clase.setEstado(dto.getEstado());
        if (dto.getAlumnoClaseDTO()!=null){
            clase.setAlumnoModel(AlumnoClaseMapper.convertToEntityAlumnoClase(dto.getAlumnoClaseDTO()));
        }
        return clase;
    }

    public  static ClasePruebaDTO convertToDto(ClasePruebaModel clase){
        ClasePruebaDTO dto = new ClasePruebaDTO();
        dto.setIdClasePrueba(clase.getIdClasePrueba());
        dto.setDiaSemana(clase.getDiaSemana());
        dto.setEstado(clase.getEstado());
        if (clase.getAlumnoModel()!=null){
            AlumnoClaseDTO alumnoDto = AlumnoClaseMapper.convertToDtoAlumnoClase(clase.getAlumnoModel());
            dto.setAlumnoClaseDTO(alumnoDto);

            alumnoDto.setProfesor(clase.getAlumnoModel().getNombreprofesor());
        }
        return dto;
    }
}
