package com.villacity.mapper.Horario;

import com.villacity.dto.Horario.HorarioDTO;
import com.villacity.model.HorarioModel;

public class HorarioMapper {

    public static HorarioModel converToEntityHorario(HorarioDTO dto){
        HorarioModel horario = new HorarioModel();
        horario.setId(dto.getId());
        horario.setSede(dto.getSede());
        horario.setDias(dto.getDias());
        horario.setHoraFin(dto.getHoraFin());
        horario.setHoraInicio(dto.getHoraInicio());

        return horario;
    }

    public static HorarioDTO convertToDtoHorario(HorarioModel horario){
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setSede(horario.getSede());
        dto.setDias(horario.getDias());
        dto.setHoraInicio(horario.getHoraInicio());
        dto.setHoraFin(horario.getHoraFin());

        return dto;
    }
}
