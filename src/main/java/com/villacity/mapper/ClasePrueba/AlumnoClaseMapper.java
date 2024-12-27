package com.villacity.mapper.ClasePrueba;

import com.villacity.dto.ClasePrueba.AlumnoClaseDTO;
import com.villacity.dto.ClasePrueba.ApoderadoClaseDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ApoderadoModel;

import java.time.LocalTime;

public class AlumnoClaseMapper {

    public static AlumnoModel convertToEntityAlumnoClase(AlumnoClaseDTO dto) {
        AlumnoModel alumno = new AlumnoModel();
        alumno.setSede(dto.getSede());
        alumno.setNombreprofesor(dto.getProfesor());
        alumno.setNombreAlumno(dto.getNombreAlumno());
        alumno.setApellidoAlumno(dto.getApellidoAlumno());
        alumno.setHoraInicio(dto.getHoraInicio());
        alumno.setHorafin(dto.getHoraFin());
        alumno.setCondicion_medica(dto.getCondicion_medica());
        alumno.setFecha_nacimiento(dto.getFecha_nacimiento());
        if (dto.getApoderadoClaseDTO() != null) {
            ApoderadoModel apoderado = new ApoderadoModel();
            apoderado.setNombreApoderado(dto.getApoderadoClaseDTO().getNombreApoderado());
            apoderado.setApellidoApoderado(dto.getApoderadoClaseDTO().getApellidoApoderado());
            apoderado.setTelefono(dto.getApoderadoClaseDTO().getTelefono());
            apoderado.setCorreo(dto.getApoderadoClaseDTO().getCorreo());
            alumno.setApoderadoModel(apoderado);
        }
        return alumno;
    }

    public static AlumnoClaseDTO convertToDtoAlumnoClase(AlumnoModel alumno) {
        AlumnoClaseDTO dto = new AlumnoClaseDTO();
        dto.setSede(alumno.getSede());
        dto.setProfesor(alumno.getNombreprofesor());
        dto.setNombreAlumno(alumno.getNombreAlumno());
        dto.setApellidoAlumno(alumno.getApellidoAlumno());
        dto.setHoraInicio(alumno.getHoraInicio());
        dto.setHoraFin(alumno.getHorafin());
        dto.setCondicion_medica(alumno.getCondicion_medica());
        dto.setFecha_nacimiento(alumno.getFecha_nacimiento());

        if (alumno.getApoderadoModel() != null) {
            ApoderadoClaseDTO apoderado = new ApoderadoClaseDTO();
            apoderado.setNombreApoderado(alumno.getApoderadoModel().getNombreApoderado());
            apoderado.setApellidoApoderado(alumno.getApoderadoModel().getApellidoApoderado());
            apoderado.setTelefono(alumno.getApoderadoModel().getTelefono());
            apoderado.setCorreo(alumno.getApoderadoModel().getCorreo());
            dto.setApoderadoClaseDTO(apoderado);
        }
        return dto;
    }
}

