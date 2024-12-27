package com.villacity.service.clasePrueba;

import com.villacity.dto.ClasePrueba.AlumnoClaseDTO;
import com.villacity.dto.ClasePrueba.ApoderadoClaseDTO;
import com.villacity.dto.ClasePrueba.ClasePruebaDTO;
import com.villacity.model.*;
import com.villacity.repository.AlumnoRepository;
import com.villacity.repository.HorarioRepository;
import com.villacity.repository.ProfesorRepository;
import com.villacity.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasePruebaHelper {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    public void actualizarAlumno(AlumnoModel alumno, AlumnoClaseDTO alumnoDto) {
        // Verifica si el objeto alumno no es nulo antes de proceder con la actualización.
        if (alumno != null) {
            // Actualiza los atributos básicos del alumno a partir del DTO recibido.
            alumno.setSede(alumnoDto.getSede());
            alumno.setNombreAlumno(alumnoDto.getNombreAlumno());
            alumno.setApellidoAlumno(alumnoDto.getApellidoAlumno());
            alumno.setCondicion_medica(alumnoDto.getCondicion_medica());
            alumno.setFecha_nacimiento(alumnoDto.getFecha_nacimiento());
            // Conectar con profesor si es necesario
            if (alumnoDto.getProfesor() != null) {
                ProfesorModel profesor = profesorRepository.findByNombreProfesor(alumnoDto.getProfesor())
                        .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado: " + alumnoDto.getProfesor()));
                alumno.setProfesorModel(profesor);
                alumno.setNombreprofesor(profesor.getNombreProfesor());
            }

            // Actualizar apoderado
            if (alumnoDto.getApoderadoClaseDTO() != null && alumno.getApoderadoModel() != null) {
                // Extrae el DTO del apoderado.
                ApoderadoClaseDTO apoderadoDto = alumnoDto.getApoderadoClaseDTO();
                // Obtiene el modelo de apoderado asociado al alumno.
                ApoderadoModel apoderado = alumno.getApoderadoModel();
                // Actualiza la información del apoderado a partir del DTO.
                apoderado.setNombreApoderado(apoderadoDto.getNombreApoderado());
                apoderado.setApellidoApoderado(apoderadoDto.getApellidoApoderado());
                apoderado.setTelefono(apoderadoDto.getTelefono());
                apoderado.setCorreo(apoderadoDto.getCorreo());
            }
        }
    }

    public void actualizarAlumnoHorario(AlumnoModel alumno, ClasePruebaDTO clasePruebaDTO){
        AlumnoClaseDTO alumnodto = clasePruebaDTO.getAlumnoClaseDTO();
        String sedeNombre = alumnodto.getSede();

        SedeModel sede = sedeRepository.findByNombre(sedeNombre)
                .orElseThrow(()-> new IllegalArgumentException("Sede no encontrada "+ sedeNombre));
        // Recupera la lista de horarios disponibles para la sede.
        List<HorarioModel> horariosSede = horarioRepository.findBySede(sedeNombre);
        // Verifica si hay horarios disponibles. Lanza una excepción si la lista está vacía.
        if (horariosSede.isEmpty()) {
            throw new IllegalArgumentException("No hay horarios disponibles para la sede: " + sedeNombre);
        }

        String diaSemana = clasePruebaDTO.getDiaSemana();
        // Filtra los horarios para encontrar aquellos que coinciden con el día especificado.
        List<HorarioModel> horarioFiltrados = horariosSede.stream()
                .filter(h -> h.getDias().contains(diaSemana))
                .collect(Collectors.toList());
        // Verifica si hay horarios disponibles para el día especificado. Lanza una excepción si no hay coincidencias.
        if (horarioFiltrados.isEmpty()){
            throw new IllegalArgumentException("No hay horarios para el día " + diaSemana + " en la sede: " + sedeNombre);
        }
        // Busca un horario específico que coincida con la hora de inicio y fin proporcionadas.
        HorarioModel horarioEncontrado = horarioFiltrados.stream()
                .filter(h -> h.getHoraInicio().equals(alumnodto.getHoraInicio())
                        && h.getHoraFin().equals(alumnodto.getHoraFin())).findFirst().
                orElseThrow(() -> new IllegalArgumentException("Horario no encontrado con horas especificas"));

        alumno.setHorarioModel(horarioEncontrado);
        alumno.setHoraInicio(horarioEncontrado.getHoraInicio());
        alumno.setHorafin(horarioEncontrado.getHoraFin());

        alumnoRepository.save(alumno);
    }
}
