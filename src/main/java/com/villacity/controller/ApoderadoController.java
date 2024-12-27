package com.villacity.controller;

import com.villacity.dto.Apoderado.ApoderadoDTO;
import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearApoderadoDTO;
import com.villacity.service.Apoderado.ApoderadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apoderados")
@CrossOrigin(origins ="*")
public class ApoderadoController {

    @Autowired
    ApoderadoService apoderadoService;

    @GetMapping("/mostrar")
    public List<ApoderadoDTO> getApoderado(){
        return this.apoderadoService.getApoderado();
    }

    @GetMapping(path = "/{id}")
    public ApoderadoDTO getById(@PathVariable Long id){
        return this.apoderadoService.getById(id);
    }

    @PostMapping("/crear")
    public ApoderadoDTO crearClase(@Valid @RequestBody ApoderadoDTO apoderadoDTO){
        return this.apoderadoService.crearClase(apoderadoDTO);
    }

    @PostMapping
    public CrearApoderadoDTO crearApoderado(@Valid @RequestBody CrearApoderadoDTO apoderadoDTO){
        return this.apoderadoService.crearApoderado(apoderadoDTO);
    }

    @PutMapping(path = "/{id}")
    public ApoderadoDTO updateApoderado(@Valid @RequestBody ApoderadoDTO request,@PathVariable Long id){
        return this.apoderadoService.updateApoderado(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable Long id){
        Boolean ok = apoderadoService.deleteById(id);
        if (ok){
            return "El apoderado con el ID: "+id+" se elimino correctamente";
        }else {
            throw new IllegalArgumentException("No se pudo eliminar al apoderado con el ID: "+id);
        }
    }
}
