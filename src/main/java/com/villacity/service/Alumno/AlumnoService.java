package com.villacity.service.Alumno;

import com.villacity.dto.Alumno.AlumnoDTO;
import com.villacity.mapper.Alumno.AlumnoMapper;
import com.villacity.model.AlumnoModel;
import com.villacity.model.SedeModel;
import com.villacity.repository.AlumnoRepository;
import com.villacity.repository.ProfesorRepository;
import com.villacity.repository.SedeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    AlumnoHelperService helperr;



    //METODO MOSTRAR
    public List<AlumnoDTO> getAlumno(){
        try {
            return alumnoRepository.findAll().stream().map(AlumnoMapper::convertToDtoAlumno).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("No se pudo mostrar la Lista de Alumno");
            return Collections.emptyList();
        }
    }

    //METODO BUSCAR POR ID
    public AlumnoDTO getById(Long id){
        Optional<AlumnoModel> alumno = alumnoRepository.findById(id);
        return alumno.map(AlumnoMapper::convertToDtoAlumno)
                .orElseThrow(()-> new IllegalArgumentException("No se Pudo encontrar al almno con el id: "+id));
    }

    //METODO GUARDAR
    public AlumnoDTO saveAlumno(AlumnoDTO dto){
        AlumnoModel alumno = AlumnoMapper.convertToEntityAlumno(dto);
        helperr.saveAlumnoHelper(alumno, dto);
        AlumnoModel saveAlumno = alumnoRepository.save(alumno);
        return AlumnoMapper.convertToDtoAlumno(saveAlumno);
    }

        @Transactional
        //METODO ACTUALIZAr
        public AlumnoDTO updateAlumno(AlumnoDTO dto, Long id){
            AlumnoModel alumno = alumnoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontro al alumno"));
            SedeModel sedenueva = sedeRepository.findByNombre(dto.getSede())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontro el nombre de la SEDE"));
            // Obtiene la sede actual del alumno para actualizar la relación.
            SedeModel sedeactual = alumno.getSedeModel();
            // Si la sede actual no es nula, elimina al alumno de la lista de alumnos de esa sede.
            if (sedeactual != null){
                sedeactual.getAlumnoModels().remove(alumno);
            }

            alumno.setSedeModel(sedenueva);
            if (!sedenueva.getAlumnoModels().contains(alumno)){
                sedenueva.getAlumnoModels().add(alumno);
            }

            // Actualiza los demás atributos del alumno con los valores del DTO.
            alumno.setSede(dto.getSede());
            alumno.setNombreprofesor(dto.getProfesor());
            alumno.setNombreAlumno(dto.getNombreAlumno());
            alumno.setApellidoAlumno(dto.getApellidoAlumno());
            alumno.setHoraInicio(dto.getHoraInicio());
            alumno.setHorafin(dto.getHoraFin());
            alumno.setCondicion_medica(dto.getCondicion_medica());
            alumno.setFecha_nacimiento(dto.getFecha_nacimiento());
            alumno.setDni(dto.getDni());

            AlumnoModel saveAlumno = alumnoRepository.save(alumno);
            return AlumnoMapper.convertToDtoAlumno(saveAlumno);
        }

    //METODO ELIMINAR
    public Boolean deleteById(Long id){
        try {
             alumnoRepository.deleteById(id);
             return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo eiminar el ID: "+id);
        }
    }
}
