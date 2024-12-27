package com.villacity.controller;

import com.villacity.dto.Horario.HorarioDTO;
import com.villacity.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioService horarioService;

    @GetMapping
    public List<HorarioDTO> getHorario() {
        return this.horarioService.getHorario();
    }

    @GetMapping(path = "{id}")
    public HorarioDTO getById(@PathVariable Long id) {
        return this.horarioService.getById(id);
    }

    @PostMapping
    public HorarioDTO saveHorario(@RequestBody HorarioDTO dto) {
        return this.horarioService.saveHorario(dto);
    }
    @PutMapping(path = "{id}")
    public HorarioDTO updateById(@RequestBody HorarioDTO dto, @PathVariable Long id){
        return this.horarioService.updateById(dto, id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = horarioService.deleteById(id);
        if (ok){
            return "El horario con el id: "+id+" se elimino correctamente";
        }else {
            return "El horario con el id: "+id+" no se pudo eliminar";
        }
    }


}
