package com.villacity.controller;

import com.villacity.dto.ClasePrueba.ClasePruebaDTO;
import com.villacity.service.clasePrueba.ClasePruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claseprueba")
public class ClasePruebaController {

    @Autowired
    ClasePruebaService clasePruebaService;

    @GetMapping
    public List<ClasePruebaDTO> getClase(){
        return this.clasePruebaService.getClase();
    }

    @GetMapping(path = "/{id}")
    public ClasePruebaDTO getById(@PathVariable Long id){
        return this.clasePruebaService.getById(id);
    }

    @PostMapping
    public ClasePruebaDTO saveClase(@RequestBody ClasePruebaDTO dto){
        return this.clasePruebaService.saveClase(dto);
    }

    @PutMapping(path = "/{id}")
    public ClasePruebaDTO updateClase(@RequestBody ClasePruebaDTO dto, @PathVariable Long id){
        return this.clasePruebaService.updateClase(dto,id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = clasePruebaService.deleteById(id);
        if (ok){
            return "La clase de Prueba se elimno Correctamente";
        }else {
            return "La clase de Prueba no se pudo eliminar";
        }
    }
}
