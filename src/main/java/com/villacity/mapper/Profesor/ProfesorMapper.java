package com.villacity.mapper.Profesor;

import com.villacity.dto.Profesor.AlumnoProfesorDTO;
import com.villacity.dto.Profesor.ProfesorDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ProfesorModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProfesorMapper {

    public static ProfesorModel convertToEntityProfesr(ProfesorDTO dto){
        ProfesorModel profesor = new ProfesorModel();
        profesor.setIdProfesor(dto.getIdProfesor());
        profesor.setNombreProfesor(dto.getNombreProfesor());
        profesor.setApellidoProfesor(dto.getApellidoProfesor());
        profesor.setDni(dto.getDni());
        profesor.setTelefono(dto.getTelefono());
        profesor.setCorreo(dto.getCorreo());
        if (dto.getAlumnoProfesorDTOS()!= null){
            List<AlumnoModel> alumno = dto.getAlumnoProfesorDTOS().stream().map(alumnoProfesorDTO -> {
                AlumnoModel alumnos = new AlumnoModel();
                alumnos.setNombreAlumno(alumnoProfesorDTO.getNombreAlumno());
                alumnos.setApellidoAlumno(alumnoProfesorDTO.getApellidoAlumno());
                alumnos.setCondicion_medica(alumnoProfesorDTO.getCondicion_medica());
                alumnos.setFecha_nacimiento(alumnoProfesorDTO.getFecha_nacimiento());
                alumnos.setDni(alumnoProfesorDTO.getDni());

                alumnos.setProfesorModel(profesor);

                return alumnos;
            }).collect(Collectors.toList());
            profesor.setAlumnoModels(alumno);
        }

        return profesor;
    }

    public static ProfesorDTO convertToDtoProfesor(ProfesorModel profesor) {
        ProfesorDTO dto = new ProfesorDTO();
        dto.setIdProfesor(profesor.getIdProfesor());
        dto.setNombreProfesor(profesor.getNombreProfesor());
        dto.setApellidoProfesor(profesor.getApellidoProfesor());
        dto.setDni(profesor.getDni());
        dto.setTelefono(profesor.getTelefono());
        dto.setCorreo(profesor.getCorreo());

        if (profesor.getAlumnoModels() != null) {
            List<AlumnoProfesorDTO> profesordto = profesor.getAlumnoModels().stream().map(alumnoModel -> {
                AlumnoProfesorDTO dtos = new AlumnoProfesorDTO();
                dtos.setNombreAlumno(alumnoModel.getNombreAlumno());
                dtos.setApellidoAlumno(alumnoModel.getApellidoAlumno());
                dtos.setCondicion_medica(alumnoModel.getCondicion_medica());
                dtos.setFecha_nacimiento(alumnoModel.getFecha_nacimiento());
                dtos.setDni(alumnoModel.getDni());

                return dtos;

            }).collect(Collectors.toList());
            dto.setAlumnoProfesorDTOS(profesordto);
        }
        return dto;
    }
}
