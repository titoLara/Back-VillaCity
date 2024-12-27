package com.villacity.service.clasePrueba;

import com.villacity.dto.ClasePrueba.ClasePruebaDTO;
import com.villacity.mapper.ClasePrueba.ClasePruebaMapper;
import com.villacity.model.*;
import com.villacity.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClasePruebaService {

    @Autowired
    ClasePruebaRepository clasePruebaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    private ClasePruebaHelper clasePruebaHelper;



    //METODO MOSTRAR
    public List<ClasePruebaDTO> getClase(){
        try {
            return clasePruebaRepository.findAll().stream().map(ClasePruebaMapper::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo mostrar la lista de las Clases de Prueba");
        }
    }

    //METODO BUSCAR POR ID
    public ClasePruebaDTO getById(Long id){
        Optional<ClasePruebaModel> clase = clasePruebaRepository.findById(id);
        return clase.map(ClasePruebaMapper::convertToDto)
                .orElseThrow(()-> new IllegalArgumentException("No se encontro la Clase con el id: "+id));
    }

    //METODO GUARDAR
    public ClasePruebaDTO saveClase(ClasePruebaDTO dto){
        try {
            ClasePruebaModel clase = ClasePruebaMapper.convertToEntityClase(dto);
            ClasePruebaModel clases = clasePruebaRepository.save(clase);

            return ClasePruebaMapper.convertToDto(clases);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo guardar la clase correctamente");
        }
    }



    public ClasePruebaDTO updateClase(ClasePruebaDTO dto, Long id) {
        // 1. Obtener la clase existente
        ClasePruebaModel claseExistente = clasePruebaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada con el ID: " + id));

        // 2. Actualizar fecha y estado
        if (dto.getDiaSemana() != null) {
            claseExistente.setDiaSemana(dto.getDiaSemana());
            claseExistente.setEstado(ClasePruebaModel.Estado.proceso);
        }

        // 3. Actualizar el alumno si está presente
        if (dto.getAlumnoClaseDTO() != null) {
            clasePruebaHelper.actualizarAlumno(claseExistente.getAlumnoModel(), dto.getAlumnoClaseDTO());
        }

        // 4. Actualizar horario y hacer conexión con horario
        if (dto.getAlumnoClaseDTO() != null) {
            // Cambia aquí: Asegúrate de pasar `dto` y el modelo de alumno
            clasePruebaHelper.actualizarAlumnoHorario(claseExistente.getAlumnoModel(), dto);
        }

        // 5. Guardar los cambios en la clase existente
        ClasePruebaModel claseActualizada = clasePruebaRepository.save(claseExistente);

        // 6. Retornar el DTO actualizado
        return ClasePruebaMapper.convertToDto(claseActualizada);
    }


    //METODO ELIMINAR
    public Boolean deleteById(Long id){
        try {
            clasePruebaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo Eliminar la clase");
        }
    }


}
