package com.villacity.controller;

import com.villacity.dto.Alumno.AlumnoDTO;
import com.villacity.service.Alumno.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoDTO> getAlumno(){

        return this.alumnoService.getAlumno();
    }

    @GetMapping("/{id}")
    public AlumnoDTO getById(@PathVariable Long id){

        return this.alumnoService.getById(id);
    }

    @PostMapping
    public AlumnoDTO saveAlumno(@RequestBody AlumnoDTO dto){
        return this.alumnoService.saveAlumno(dto);
    }

    @PutMapping(path = "/{id}")
    public AlumnoDTO updateAlumno(@RequestBody AlumnoDTO dto, @PathVariable Long id){
        return this.alumnoService.updateAlumno(dto, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = alumnoService.deleteById(id);
        if (ok){
            return "Alumno con el  ID: "+id+" se elimino correctamente";
        }else {
            throw new IllegalArgumentException("El alumno se elimino correctamente");
        }
    }
}
