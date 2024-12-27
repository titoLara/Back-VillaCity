package com.villacity.mapper.Apoderado;

import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearAlumnoApoderadoDTO;
import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearApoderadoDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ApoderadoModel;

import java.util.List;
import java.util.stream.Collectors;

public class CrearApoderadoMapper {

    public static ApoderadoModel converToEntityApoderado(CrearApoderadoDTO apoderadoDTO){
        if (apoderadoDTO== null) {
            return null; // Manejo de nulos en entidad
        }
        ApoderadoModel apoderado = new ApoderadoModel();
        apoderado.setId_apoderado(apoderadoDTO.getId_apoderado());
        apoderado.setNombreApoderado(apoderadoDTO.getNombreApoderado());
        apoderado.setApellidoApoderado(apoderadoDTO.getApellidoApoderado());
        apoderado.setDni(apoderadoDTO.getDni());
        apoderado.setCorreo(apoderadoDTO.getCorreo());
        apoderado.setTelefono(apoderadoDTO.getTelefono());
        if (apoderadoDTO.getCrearAlumnoModels() != null){
            List<AlumnoModel> alumno = apoderadoDTO.getCrearAlumnoModels().stream().map(crearAlumnoApoderadoDTO -> {
                AlumnoModel alumnos = new AlumnoModel();
                alumnos.setSede(crearAlumnoApoderadoDTO.getSede());
                alumnos.setNombreprofesor(crearAlumnoApoderadoDTO.getNombreprofesor());
                alumnos.setNombreAlumno(crearAlumnoApoderadoDTO.getNombreAlumno());
                alumnos.setApellidoAlumno(crearAlumnoApoderadoDTO.getApellidoAlumno());
                alumnos.setDias(crearAlumnoApoderadoDTO.getDias());
                alumnos.setHoraInicio(crearAlumnoApoderadoDTO.getHoraInicio());
                alumnos.setHorafin(crearAlumnoApoderadoDTO.getHoraFin());
                alumnos.setCondicion_medica(crearAlumnoApoderadoDTO.getCondicion_medica());
                alumnos.setFecha_nacimiento(crearAlumnoApoderadoDTO.getFecha_nacimiento());
                alumnos.setDni(crearAlumnoApoderadoDTO.getDni());
                alumnos.setApoderadoModel(apoderado);
                return alumnos;
            }).collect(Collectors.toList());
            apoderado.setAlumnoModels(alumno);
        }
        return apoderado;
    }

    public static CrearApoderadoDTO convertToDTO(ApoderadoModel apoderado) {
        if (apoderado == null) {
            return null;
        }
        CrearApoderadoDTO apoderadoDTO = new CrearApoderadoDTO();
        apoderadoDTO.setId_apoderado(apoderado.getId_apoderado());
        apoderadoDTO.setNombreApoderado(apoderado.getNombreApoderado());
        apoderadoDTO.setApellidoApoderado(apoderado.getApellidoApoderado());
        apoderadoDTO.setDni(apoderado.getDni());
        apoderadoDTO.setCorreo(apoderado.getCorreo());
        apoderadoDTO.setTelefono(apoderado.getTelefono());

        if (apoderado.getAlumnoModels() != null) {
            List<CrearAlumnoApoderadoDTO> alumnoDTOs = apoderado.getAlumnoModels().stream()
                    .map(alumno -> {
                        CrearAlumnoApoderadoDTO alumnoDTO = new CrearAlumnoApoderadoDTO();
                        alumnoDTO.setSede(alumno.getSede());
                        alumnoDTO.setNombreprofesor(alumno.getNombreprofesor());
                        alumnoDTO.setNombreAlumno(alumno.getNombreAlumno());
                        alumnoDTO.setApellidoAlumno(alumno.getApellidoAlumno());
                        alumnoDTO.setDias(alumno.getDias());
                        alumnoDTO.setHoraInicio(alumno.getHoraInicio());
                        alumnoDTO.setHoraFin(alumno.getHorafin());
                        alumnoDTO.setCondicion_medica(alumno.getCondicion_medica());
                        alumnoDTO.setFecha_nacimiento(alumno.getFecha_nacimiento());
                        alumnoDTO.setDni(alumno.getDni());
                        return alumnoDTO;
                    })
                    .collect(Collectors.toList());
            apoderadoDTO.setCrearAlumnoModels(alumnoDTOs);
        }

        return apoderadoDTO;
    }

}
