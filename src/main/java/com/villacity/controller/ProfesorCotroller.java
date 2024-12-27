package com.villacity.controller;

import com.villacity.dto.Profesor.ProfesorDTO;
import com.villacity.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
@CrossOrigin(origins ="*")
public class ProfesorCotroller {

    @Autowired
    ProfesorService profesorService;

    @GetMapping
    public List<ProfesorDTO> getProfesor(){
        return this.profesorService.getProfesor();
    }

    @GetMapping(path = "/{id}")
    public ProfesorDTO getById(@PathVariable Long id){
        return this.profesorService.getById(id);
    }

    @PostMapping
    public ProfesorDTO saveProfesor(@RequestBody ProfesorDTO dto){
        return this.profesorService.saveProfesor(dto);
    }

    @PutMapping(path = "/{id}")
    public ProfesorDTO updateProfesor(@RequestBody ProfesorDTO dto , @PathVariable long id){
        return this.profesorService.updateById(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = profesorService.deleteById(id);
        if (ok){
            return "El profesor con el ID: "+id+" se elimino correctamente";
        }else{
            return "No se pudo eliminar el ID: "+id;
        }
    }
}
