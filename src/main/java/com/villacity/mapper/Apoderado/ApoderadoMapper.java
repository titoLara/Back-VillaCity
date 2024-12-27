package com.villacity.mapper.Apoderado;

import com.villacity.dto.Apoderado.AlumnoApoderadoDTO;
import com.villacity.dto.Apoderado.ApoderadoDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ApoderadoModel;

import java.util.List;
import java.util.stream.Collectors;

public class ApoderadoMapper {

    public static ApoderadoModel convertToEntity(ApoderadoDTO dto){
        ApoderadoModel apoderado= new ApoderadoModel();
        apoderado.setId_apoderado(dto.getId_apoderado());
        apoderado.setNombreApoderado(dto.getNombreApoderado());
        apoderado.setApellidoApoderado(dto.getApellidoApoderado());
        apoderado.setDni(dto.getDni());
        apoderado.setTelefono(dto.getTelefono());
        apoderado.setCorreo(dto.getCorreo());
        if (dto.getAlumnoModels()!= null){
            List<AlumnoModel> alumnos = dto.getAlumnoModels().stream().map(alumnoApoderadoDTO-> {
                AlumnoModel alumnoModel = new AlumnoModel();
                alumnoModel.setSede(alumnoApoderadoDTO.getSede());
                alumnoModel.setNombreAlumno(alumnoApoderadoDTO.getNombreAlumno());
                alumnoModel.setApellidoAlumno(alumnoApoderadoDTO.getApellidoAlumno());
                alumnoModel.setCondicion_medica(alumnoApoderadoDTO.getCondicion_medica());
                alumnoModel.setFecha_nacimiento(alumnoApoderadoDTO.getFecha_nacimiento());
                alumnoModel.setDni(alumnoApoderadoDTO.getDni());
                alumnoModel.setApoderadoModel(apoderado);
                return alumnoModel;
            }).collect(Collectors.toList());
            apoderado.setAlumnoModels(alumnos);
        }
        return apoderado;
    }

    public static ApoderadoDTO convertToDto(ApoderadoModel apoderado){
        ApoderadoDTO dto=new ApoderadoDTO();
        dto.setId_apoderado(apoderado.getId_apoderado());
        dto.setNombreApoderado(apoderado.getNombreApoderado());
        dto.setApellidoApoderado(apoderado.getApellidoApoderado());
        dto.setDni(apoderado.getDni());
        dto.setTelefono(apoderado.getTelefono());
        dto.setCorreo(apoderado.getCorreo());

        if (apoderado.getAlumnoModels()!=null){
            List<AlumnoApoderadoDTO> alumno = apoderado.getAlumnoModels().stream().map(alumnoModel -> {
                AlumnoApoderadoDTO dto1 = new AlumnoApoderadoDTO();
                dto1.setSede(alumnoModel.getSede());
                dto1.setNombreAlumno(alumnoModel.getNombreAlumno());
                dto1.setApellidoAlumno(alumnoModel.getApellidoAlumno());
                dto1.setCondicion_medica(alumnoModel.getCondicion_medica());
                dto1.setFecha_nacimiento(alumnoModel.getFecha_nacimiento());
                dto1.setDni(alumnoModel.getDni());
                return dto1;
            }).collect(Collectors.toList());
            dto.setAlumnoModels(alumno);
        }
        return dto;
    }
}
