package com.villacity.service.Alumno;

import com.villacity.dto.Alumno.AlumnoDTO;
import com.villacity.mapper.Alumno.AlumnoMapper;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ProfesorModel;
import com.villacity.model.SedeModel;
import com.villacity.repository.AlumnoRepository;
import com.villacity.repository.ProfesorRepository;
import com.villacity.repository.SedeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlumnoHelperService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SedeRepository  sedeRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public void saveAlumnoHelper (AlumnoModel alumno ,AlumnoDTO alumnodto){
        // Busca la sede por nombre en la base de datos. Si no se encuentra, lanza una excepción.
        SedeModel sede = sedeRepository.findByNombre(alumnodto.getSede())
                .orElseThrow(() -> new IllegalArgumentException("No se Pudo encontrar la Sede"));
        // Busca el profesor por nombre en la base de datos. Si no se encuentra, lanza una excepción.
        ProfesorModel profe = profesorRepository.findByNombreProfesor(alumnodto.getProfesor())
                .orElseThrow(()-> new IllegalArgumentException("No se pudo encontrar al Profesor"));
        // Asigna el modelo de sede encontrado al modelo de alumno.
            alumno.setSedeModel(sede);
        // Asigna el modelo de profesor encontrado al modelo de alumno.
            alumno.setProfesorModel(profe);
    }

    /*@Transactional
    public void updateAlumnoHelper(AlumnoModel alumno, AlumnoDTO alumnoDTO) {
        // Obtener la nueva sede
        SedeModel sedeNueva = sedeRepository.findByNombre(alumnoDTO.getSede())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el nombre de la sede"));

        // Obtener la sede actual del alumno
        SedeModel sedeActual = alumno.getSedeModel();

        // Eliminar la relación con la sede actual (si existe)
        if (sedeActual != null && sedeActual.getAlumnoModels().contains(alumno)) {
            sedeActual.getAlumnoModels().remove(alumno); // Remover del conjunto de alumnos de la sede actual
            sedeRepository.save(sedeActual); // Persistir cambios en la sede actual
        }

        // Asociar el alumno con la nueva sede solo si aún no está asociado
        if (!sedeNueva.getAlumnoModels().contains(alumno)) {
            alumno.setSedeModel(sedeNueva); // Actualizar la relación en el alumno
            sedeNueva.getAlumnoModels().add(alumno); // Añadir al conjunto de alumnos de la nueva sede
            sedeRepository.save(sedeNueva); // Persistir cambios en la nueva sede
        }
    }*/


}
