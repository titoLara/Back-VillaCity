package com.villacity.controller;

import com.villacity.dto.Sede.SedeDTO;
import com.villacity.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sede")
@CrossOrigin(origins ="*")
public class SedeController {

    @Autowired
    SedeService sedeService;

    @GetMapping
    public List<SedeDTO> getSede(){
        return this.sedeService.getSede();
    }

    @GetMapping(path = "/{id}")
    public SedeDTO getById(@PathVariable Long id){
        return this.sedeService.getById(id);
    }

    @PostMapping
    public SedeDTO saveSede(@RequestBody SedeDTO dto){
        return this.sedeService.saveSede(dto);
    }

    @PutMapping(path = "/{id}")
    public  SedeDTO updateSede(@RequestBody SedeDTO dto, @PathVariable Long id){
        return this.sedeService.updateAlumno(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = sedeService.deleteById(id);
        if (ok){
            return "La sede se elimino correctamente";
        }else {
            return "La sede no se pudo eliminar";
        }
    }
}
