package com.villacity.service;

import com.villacity.dto.Sede.SedeDTO;
import com.villacity.mapper.Sede.SedeMapper;
import com.villacity.model.SedeModel;
import com.villacity.repository.SedeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SedeService {

    @Autowired
    SedeRepository sedeRepository;

    //METODO MOSTRAR
    public List<SedeDTO> getSede(){
        try {
            return sedeRepository.findAll().stream().map(SedeMapper::convertToDtoSede).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("No se pudo Mostrar la Lista");
            return Collections.emptyList();
        }
    }

    //METODO BUSCAR POR ID
    public SedeDTO getById(Long id){
        Optional<SedeModel> sede = sedeRepository.findById(id);

        return sede.map(SedeMapper::convertToDtoSede)
                .orElseThrow(()-> new IllegalArgumentException("No se puede encontrar la sede con el ID: "+id));
    }

    //METODO GUARDAR
    public SedeDTO saveSede(SedeDTO dto){
        try {
            SedeModel sede = SedeMapper.convertToEntitySede(dto);
            SedeModel saveSede = sedeRepository.save(sede);
            return SedeMapper.convertToDtoSede(saveSede);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo Guardar la sede correctamente: "+e.getMessage());
        }
    }

    //METODO ACTUALIZAR
    public SedeDTO updateAlumno(SedeDTO dto, Long id){
        Optional<SedeModel> sede = sedeRepository.findById(id);
        if (sede.isPresent()){
            SedeModel sedePresente = sede.get();
            sedePresente.setNombre(dto.getNombre());
            sedePresente.setDireccion(dto.getDireccion());

            SedeModel saveSede = sedeRepository.save(sedePresente);

            return SedeMapper.convertToDtoSede(saveSede);
        }else {
            throw new IllegalArgumentException("No se pudo actualizar La sede con el ID: "+id);
        }
    }

    //METODO ELIMINAR
    public Boolean deleteById(Long id){
        try {
            sedeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo eliminar la Sede con el ID: "+id);
        }
    }
}
