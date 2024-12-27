package com.villacity.service;

import com.villacity.dto.Horario.HorarioDTO;
import com.villacity.mapper.Horario.HorarioMapper;
import com.villacity.model.HorarioModel;
import com.villacity.model.SedeModel;
import com.villacity.repository.HorarioRepository;
import com.villacity.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    SedeRepository sedeRepository;

    //METODO MOSTRAR
    public List<HorarioDTO> getHorario(){
        try {
            return horarioRepository.findAll().stream().map(HorarioMapper::convertToDtoHorario)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo encontrar los horarios");
        }
    }

    //METODO MOSTRAT POR ID
    public HorarioDTO getById(Long id){
        Optional<HorarioModel> horario = horarioRepository.findById(id);
        return horario.map(HorarioMapper::convertToDtoHorario)
                .orElseThrow(()-> new IllegalArgumentException("No se encontro al horario con el id: "+id));
    }

    //METODO GUARDAR
    public HorarioDTO saveHorario(HorarioDTO dto){
            HorarioModel horario = HorarioMapper.converToEntityHorario(dto);

            SedeModel sede = sedeRepository.findByNombre(dto.getSede())
                    .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar el nombra de la sede"));
            horario.setSedeModel(sede);

            HorarioModel savedHorario = horarioRepository.save(horario);
            return HorarioMapper.convertToDtoHorario(savedHorario);

    }


    //METODO ACTUALIZAR
    public HorarioDTO updateById(HorarioDTO dto, Long id) {
        HorarioModel horario = horarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No se encontroo al Horario"));

        SedeModel sede = sedeRepository.findByNombre(dto.getSede())
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el nomre de la sede"));

        horario.setSedeModel(sede);
        horario.setDias(dto.getDias());
        horario.setHoraInicio(dto.getHoraInicio());
        horario.setHoraFin(dto.getHoraFin());
        horarioRepository.save(horario);

        return HorarioMapper.convertToDtoHorario(horario);
    }


    //METODO ELIMINAR
    public Boolean deleteById(Long id){
        try {
            horarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo eliminar el horario con el id: "+id);
        }

    }

}
