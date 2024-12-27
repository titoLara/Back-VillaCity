package com.villacity.mapper.Apoderado;

import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearAlumnoApoderadoDTO;
import com.villacity.model.AlumnoModel;

public class CrearAlumnoApoderadoMapper {

    public static AlumnoModel convertToEntityAlumnoApoderado (CrearAlumnoApoderadoDTO alumnoApoderadoDTO){
        if (alumnoApoderadoDTO == null){
            return null;
        }
        AlumnoModel alumno = new AlumnoModel();
        alumno.setSede(alumnoApoderadoDTO.getSede());
        alumno.setNombreprofesor(alumnoApoderadoDTO.getNombreprofesor());
        alumno.setNombreAlumno(alumnoApoderadoDTO.getNombreAlumno());
        alumno.setApellidoAlumno(alumnoApoderadoDTO.getApellidoAlumno());
        alumno.setDias(alumnoApoderadoDTO.getDias());
        alumno.setHoraInicio(alumnoApoderadoDTO.getHoraInicio());
        alumno.setHorafin(alumnoApoderadoDTO.getHoraFin());
        alumno.setCondicion_medica(alumnoApoderadoDTO.getCondicion_medica());
        alumno.setFecha_nacimiento(alumnoApoderadoDTO.getFecha_nacimiento());
        alumno.setDni(alumnoApoderadoDTO.getDni());
        return alumno;
    }

    public static CrearAlumnoApoderadoDTO convertToDtoAlumnoApoderado(AlumnoModel alumnoModel){
        if (alumnoModel == null){
            return null;
        }
        CrearAlumnoApoderadoDTO crearAlumnoApoderadoDTO = new CrearAlumnoApoderadoDTO();
        crearAlumnoApoderadoDTO.setSede(alumnoModel.getSede());
        crearAlumnoApoderadoDTO.setNombreprofesor(alumnoModel.getNombreprofesor());
        crearAlumnoApoderadoDTO.setNombreAlumno(alumnoModel.getNombreAlumno());
        crearAlumnoApoderadoDTO.setApellidoAlumno(alumnoModel.getApellidoAlumno());
        crearAlumnoApoderadoDTO.setDias(alumnoModel.getDias());
        crearAlumnoApoderadoDTO.setHoraInicio(alumnoModel.getHoraInicio());
        crearAlumnoApoderadoDTO.setHoraFin(alumnoModel.getHorafin());
        crearAlumnoApoderadoDTO.setCondicion_medica(alumnoModel.getCondicion_medica());
        crearAlumnoApoderadoDTO.setFecha_nacimiento(alumnoModel.getFecha_nacimiento());
        crearAlumnoApoderadoDTO.setDni(alumnoModel.getDni());
        return crearAlumnoApoderadoDTO;
    }
}
