package com.villacity.mapper.Apoderado;

import com.villacity.dto.Apoderado.AlumnoApoderadoDTO;
import com.villacity.model.AlumnoModel;

public class AlumnoApoderadoMapper {

    public static AlumnoModel convertToEntityAlumnoApoderado(AlumnoApoderadoDTO alumnoApoderadoDTO) {
        if (alumnoApoderadoDTO == null) {
            return null;
        }
        AlumnoModel alumno = new AlumnoModel();
        alumno.setSede(alumnoApoderadoDTO.getSede());
        alumno.setNombreAlumno(alumnoApoderadoDTO.getNombreAlumno());
        alumno.setApellidoAlumno(alumnoApoderadoDTO.getApellidoAlumno());
        alumno.setCondicion_medica(alumnoApoderadoDTO.getCondicion_medica());
        alumno.setFecha_nacimiento(alumnoApoderadoDTO.getFecha_nacimiento());
        alumno.setDni(alumnoApoderadoDTO.getDni());

        // Si necesitas asignar otros campos, agrégalo aquí

        return alumno;
    }

    public static AlumnoApoderadoDTO convertToDtoAlumnoApoderado(AlumnoModel alumnoModel) {
        if (alumnoModel == null) {
            return null;
        }
        AlumnoApoderadoDTO alumnoApoderadoDTO = new AlumnoApoderadoDTO();
        alumnoApoderadoDTO.setSede(alumnoModel.getSedeModel().getNombre());

        alumnoApoderadoDTO.setNombreAlumno(alumnoModel.getNombreAlumno());
        alumnoApoderadoDTO.setApellidoAlumno(alumnoModel.getApellidoAlumno());
        alumnoApoderadoDTO.setCondicion_medica(alumnoModel.getCondicion_medica());
        alumnoApoderadoDTO.setFecha_nacimiento(alumnoModel.getFecha_nacimiento());
        alumnoApoderadoDTO.setDni(alumnoModel.getDni());

        return alumnoApoderadoDTO;
    }
}
