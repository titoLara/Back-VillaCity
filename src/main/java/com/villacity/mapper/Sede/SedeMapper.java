package com.villacity.mapper.Sede;

import com.villacity.dto.Sede.AlumnoSedeDTO;
import com.villacity.dto.Sede.HorarioSedeDTO;
import com.villacity.dto.Sede.SedeDTO;
import com.villacity.model.AlumnoModel;
import com.villacity.model.HorarioModel;
import com.villacity.model.SedeModel;

import java.util.List;
import java.util.stream.Collectors;

public class SedeMapper {

    public static SedeModel convertToEntitySede(SedeDTO dto){
        SedeModel sede = new SedeModel();
        sede.setIdSede(dto.getIdSede());
        sede.setNombre(dto.getNombre());
        sede.setDireccion(dto.getDireccion());
        if(dto.getAlumnoSedeDTOS()!=null){
            List<AlumnoModel> alumno = dto.getAlumnoSedeDTOS().stream().map(AlumnoSede ->{
                AlumnoModel alumnos = new AlumnoModel();
                alumnos.setNombreAlumno(AlumnoSede.getNombreAlumno());
                alumnos.setApellidoAlumno(AlumnoSede.getApellidoAlumno());
                alumnos.setCondicion_medica(AlumnoSede.getCondicion_medica());
                alumnos.setFecha_nacimiento(AlumnoSede.getFecha_nacimiento());
                alumnos.setDni(AlumnoSede.getDni());
                alumnos.setSedeModel(sede);
                return alumnos;
            }).collect(Collectors.toList());
            sede.setAlumnoModels(alumno);
        }
        if (dto.getHorarioSedeDTOS()!=null){
            List<HorarioModel> horario = dto.getHorarioSedeDTOS().stream().map(horarioSedeDTO -> {
                HorarioModel horarios = new HorarioModel();
                horarios.setDias(horarios.getDias());
                horarios.setHoraInicio(horarioSedeDTO.getHoraInicio());
                horarios.setHoraFin(horarioSedeDTO.getHoraFin());
                return horarios;
            }).collect(Collectors.toList());
            sede.setHorarioModel(horario);
        }
        return sede;
    }

    public static SedeDTO convertToDtoSede(SedeModel sede){
        SedeDTO dto = new SedeDTO();
        dto.setIdSede(sede.getIdSede());
        dto.setNombre(sede.getNombre());
        dto.setDireccion(sede.getDireccion());
        if(sede.getAlumnoModels()!=null){
            List<AlumnoSedeDTO> alumno = sede.getAlumnoModels().stream().map(alumnoModel -> {
                AlumnoSedeDTO alumnos = new AlumnoSedeDTO();
                alumnos.setNombreAlumno(alumnoModel.getNombreAlumno());
                alumnos.setApellidoAlumno(alumnoModel.getApellidoAlumno());
                alumnos.setCondicion_medica(alumnoModel.getCondicion_medica());
                alumnos.setFecha_nacimiento(alumnoModel.getFecha_nacimiento());
                alumnos.setDni(alumnoModel.getDni());
                return alumnos;
            }).collect(Collectors.toList());
            dto.setAlumnoSedeDTOS(alumno);
        }
        if (sede.getHorarioModel()!=null){
            List<HorarioSedeDTO> horario = sede.getHorarioModel().stream().map(horarioModel -> {
                HorarioSedeDTO horarios = new HorarioSedeDTO();
                horarios.setDias(horarioModel.getDias());
                horarios.setHoraInicio(horarioModel.getHoraInicio());
                horarios.setHoraFin(horarioModel.getHoraFin());
                return horarios;
            }).collect(Collectors.toList());
            dto.setHorarioSedeDTOS(horario);
        }
        return dto;
    }
}
