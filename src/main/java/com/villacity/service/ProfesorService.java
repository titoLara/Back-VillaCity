package com.villacity.service;

import com.villacity.dto.Profesor.ProfesorDTO;
import com.villacity.mapper.Profesor.ProfesorMapper;
import com.villacity.model.ProfesorModel;
import com.villacity.repository.ProfesorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    //METODO MOSTRAR
    public List<ProfesorDTO> getProfesor(){
        try {
            return profesorRepository.findAll().stream().map(ProfesorMapper::convertToDtoProfesor).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo Mostrar la Lista de Profesores");
        }
    }

    //METODOR BUSCAR POR ID
    public ProfesorDTO getById(Long id){
        Optional<ProfesorModel> profesor = profesorRepository.findById(id);
        return profesor.map(ProfesorMapper::convertToDtoProfesor).orElseThrow(()->new IllegalArgumentException("No se encontro al profesor con el id: "+id));
    }

    //METODO GUARDAR PROFESOR
    public ProfesorDTO saveProfesor(ProfesorDTO dto){
        try {
            ProfesorModel profesor = ProfesorMapper.convertToEntityProfesr(dto);
            ProfesorModel profesores = profesorRepository.save(profesor);

            return ProfesorMapper.convertToDtoProfesor(profesores);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo guardar al Profesor Correctamente");
        }
    }

    //METODO ACTUALIZAR
    public ProfesorDTO updateById(ProfesorDTO dto, Long id){
        Optional<ProfesorModel> profesorOpt = profesorRepository.findById(id);
        if (profesorOpt.isPresent()){
            ProfesorModel profesor = profesorOpt.get();

            profesor.setNombreProfesor(dto.getNombreProfesor());
            profesor.setApellidoProfesor(dto.getApellidoProfesor());
            profesor.setDni(dto.getDni());
            profesor.setTelefono(dto.getTelefono());
            profesor.setCorreo(dto.getCorreo());

            return ProfesorMapper.convertToDtoProfesor(profesor);
        }else {
            throw new IllegalArgumentException("No se pudo Actualizar al Profesor con el id: "+id);
        }
    }

    //METODO ELIMINAR
    public Boolean deleteById(Long id){
        try{
            profesorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo Eliminar al Profesor con el ID: "+id);
        }

    }
}
