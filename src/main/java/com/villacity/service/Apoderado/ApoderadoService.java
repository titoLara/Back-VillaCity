package com.villacity.service.Apoderado;

import com.villacity.dto.Apoderado.ApoderadoDTO;
import com.villacity.dto.Apoderado.CrearApoderadoAlumno.CrearApoderadoDTO;
import com.villacity.mapper.Apoderado.ApoderadoMapper;
import com.villacity.mapper.Apoderado.CrearAlumnoApoderadoMapper;
import com.villacity.mapper.Apoderado.CrearApoderadoMapper;
import com.villacity.model.AlumnoModel;
import com.villacity.model.ApoderadoModel;
import com.villacity.model.ProfesorModel;
import com.villacity.repository.AlumnoRepository;
import com.villacity.repository.ApoderadoRepository;
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
public class ApoderadoService {

    @Autowired
    ApoderadoRepository apoderadoRepository;

    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    private ApoderadoHelper helper;

    @Autowired
    private AlumnoRepository alumnoRepository;

    //METODO MOSTRAR
    public List<ApoderadoDTO> getApoderado(){
        try {
            return apoderadoRepository.findAll().stream().map(ApoderadoMapper::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("No se pudo mostrar la lista de  apoderados"+e);
            return Collections.emptyList();
        }
    }

    //METODO BUSCAR POR ID
    public ApoderadoDTO getById(Long id){
        Optional<ApoderadoModel> apoderado = apoderadoRepository.findById(id);
        return apoderado.map(ApoderadoMapper::convertToDto)
                .orElseThrow(()->new IllegalArgumentException("No se pudo Enocontrar el I "+id));
    }
    //CREAR APODERADO Y ALUMNO
    public CrearApoderadoDTO crearApoderado(CrearApoderadoDTO apoderadoDTO) {
        // Convierte el DTO de apoderado en un modelo de entidad Apoderado.
        ApoderadoModel apoderado = CrearApoderadoMapper.converToEntityApoderado(apoderadoDTO);
        List<AlumnoModel> alumnos = helper.crearApoderadoHelper(apoderadoDTO);
        // Asocia cada alumno creado con el apoderado correspondiente.
        alumnos.forEach(alumnoModel -> alumnoModel.setApoderadoModel(apoderado));

        //alumnoRepository.saveAll(alumnos);
        apoderado.setAlumnoModels(alumnos);

        ApoderadoModel saveApoderado = apoderadoRepository.save(apoderado);
        return CrearApoderadoMapper.convertToDTO(saveApoderado);
    }


    //METODO CREAR CLASE
    public ApoderadoDTO crearClase(ApoderadoDTO apoderadoDTO) {
        ApoderadoModel apoderado = ApoderadoMapper.convertToEntity(apoderadoDTO);
            List<AlumnoModel> alumnos = helper.crearClase( apoderadoDTO );
            alumnos.forEach(alumno -> alumno.setApoderadoModel(apoderado));
            apoderado.setAlumnoModels(alumnos);
            ApoderadoModel newApoderado = apoderadoRepository.save(apoderado);
            return ApoderadoMapper.convertToDto(newApoderado);
    }


    //METODO ACTUALIZAR
    public ApoderadoDTO updateApoderado(ApoderadoDTO request, Long id){
        Optional<ApoderadoModel> apoderado = apoderadoRepository.findById(id);

        if(apoderado.isPresent()){
            ApoderadoModel apoderadoExiste = apoderado.get();
            apoderadoExiste.setNombreApoderado(request.getNombreApoderado());
            apoderadoExiste.setApellidoApoderado(request.getApellidoApoderado());
            apoderadoExiste.setDni(request.getDni());
            apoderadoExiste.setTelefono(request.getTelefono());
            apoderadoExiste.setCorreo(request.getCorreo());

            ApoderadoModel saveApoderado = apoderadoRepository.save(apoderadoExiste);

            return ApoderadoMapper.convertToDto(saveApoderado);
        } else {
            throw new IllegalArgumentException("No se pudo Actualizar correctamente el Apoderado");
        }
    }

    public Boolean deleteById(Long id){
        try{
            apoderadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo eliminar el id: "+id);
        }
    }


}
