package com.villacity.service.Apoderado;

import com.villacity.dto.Apoderado.ApoderadoDTO;
import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearAlumnoApoderadoDTO;
import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearApoderadoDTO;
import com.villacity.mapper.Apoderado.AlumnoApoderadoMapper;
import com.villacity.mapper.Apoderado.CrearAlumnoApoderadoMapper;
import com.villacity.model.*;
import com.villacity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApoderadoHelper {
    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

     @Autowired
     private HorarioRepository horarioRepository;

    public List<AlumnoModel> crearApoderadoHelper(CrearApoderadoDTO apoderadoDTO){
        return apoderadoDTO.getCrearAlumnoModels().stream().map(alumnoApoderadoDTO -> {
            AlumnoModel alumno = CrearAlumnoApoderadoMapper.convertToEntityAlumnoApoderado(alumnoApoderadoDTO);

            String sedeNombre = alumnoApoderadoDTO.getSede();

            // Busca los horarios disponibles para la sede especificada.
            List<HorarioModel> horariosSede = horarioRepository.findBySede(sedeNombre);
            if (horariosSede.isEmpty()){
                throw new IllegalArgumentException("No se encontro Horarios para la sede "+sedeNombre);
            }

            List<String> dias= alumnoApoderadoDTO.getDias();
            // Buscando un horario que coincida con los días y horas proporcionadas en el DTO.
            HorarioModel horarioEncontrado = horariosSede.stream()
                    .filter(horarioModel -> dias.stream().allMatch(dia -> horarioModel.getDias().contains(dia))
                            && horarioModel.getHoraInicio().equals(alumnoApoderadoDTO.getHoraInicio())
                            && horarioModel.getHoraFin().equals(alumnoApoderadoDTO.getHoraFin()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró un horario que coincida con los días "
                            +dias+" y las horas "+alumnoApoderadoDTO.getHoraInicio()+ " - "+ alumnoApoderadoDTO.getHoraFin()
                            + "en la sede: "+sedeNombre));

            alumno.setHorarioModel(horarioEncontrado);
            alumno.setHoraInicio(horarioEncontrado.getHoraInicio());
            alumno.setHorafin(horarioEncontrado.getHoraFin());

            SedeModel sede = sedeRepository.findByNombre(alumnoApoderadoDTO.getSede())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontro la Sede"));
            alumno.setSedeModel(sede);

            ProfesorModel profesor = profesorRepository.findByNombreProfesor(alumnoApoderadoDTO.getNombreprofesor())
                    .orElseThrow(() -> new IllegalArgumentException("No se Encontro el Nombre del Profesor"));
            alumno.setProfesorModel(profesor);
            alumnoRepository.save(alumno);
            return alumno;
        }).collect(Collectors.toList());
    }


    public List<AlumnoModel> crearClase(ApoderadoDTO apoderadoDto){
        return apoderadoDto.getAlumnoModels().stream().map(alumnoApoderadoDTO -> {
            AlumnoModel alumno = AlumnoApoderadoMapper.convertToEntityAlumnoApoderado(alumnoApoderadoDTO);

            SedeModel sede = sedeRepository.findByNombre(alumnoApoderadoDTO.getSede())
                    .orElseThrow(()->new IllegalArgumentException("No se encontro la Sede Escogida"));
            alumno.setSedeModel(sede);

            //Asiganar Clase de prueba
            ClasePruebaModel clasePrueba = new ClasePruebaModel();
            alumno.setClasePruebaModel(clasePrueba);
            clasePrueba.setEstado(ClasePruebaModel.Estado.solicitada);
            return alumno;
        }).collect(Collectors.toList());
    }

}
