package com.villacity.mapper.Alumno;

import com.villacity.dto.Alumno.AlumnoDTO;
import com.villacity.dto.Alumno.ApoderadoAlumnoDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ApoderadoModel;

public class AlumnoMapper {

    public static AlumnoModel convertToEntityAlumno(AlumnoDTO dto){
        AlumnoModel alumno = new AlumnoModel();
        alumno.setId_alumno(dto.getId());
        alumno.setSede(dto.getSede());
        alumno.setNombreprofesor(dto.getProfesor());
        alumno.setNombreAlumno(dto.getNombreAlumno());
        alumno.setApellidoAlumno(dto.getApellidoAlumno());
        alumno.setHoraInicio(dto.getHoraInicio());
        alumno.setHorafin(dto.getHoraFin());
        alumno.setCondicion_medica(dto.getCondicion_medica());
        alumno.setFecha_nacimiento(dto.getFecha_nacimiento());
        alumno.setDni(dto.getDni());
        if (dto.getApoderado()!=null){
            ApoderadoModel apoderado = new ApoderadoModel();
            apoderado.setNombreApoderado(dto.getApoderado().getNombreApoderado());
            apoderado.setApellidoApoderado(dto.getApoderado().getApellidoApoderado());
            apoderado.setTelefono(dto.getApoderado().getTelefono());

            alumno.setApoderadoModel(apoderado);

        }

        return alumno;
    }

        public static AlumnoDTO convertToDtoAlumno(AlumnoModel alumno){
            AlumnoDTO dto = new AlumnoDTO();
            dto.setId(alumno.getId_alumno());
            dto.setSede(alumno.getSede());
            dto.setProfesor(alumno.getNombreprofesor());
            dto.setNombreAlumno(alumno.getNombreAlumno());
            dto.setApellidoAlumno(alumno.getApellidoAlumno());
            dto.setHoraInicio(alumno.getHoraInicio());
            dto.setHoraFin(alumno.getHorafin());
            dto.setCondicion_medica(alumno.getCondicion_medica());
            dto.setFecha_nacimiento(alumno.getFecha_nacimiento());
            dto.setDni(alumno.getDni());
            if(alumno.getApoderadoModel()!=null){
                ApoderadoAlumnoDTO apoderado = new ApoderadoAlumnoDTO();
                apoderado.setNombreApoderado(alumno.getApoderadoModel().getNombreApoderado());
                apoderado.setApellidoApoderado(alumno.getApoderadoModel().getApellidoApoderado());
                apoderado.setTelefono(alumno.getApoderadoModel().getTelefono());
                dto.setApoderado(apoderado);
            }
            return dto;
        }
    }
