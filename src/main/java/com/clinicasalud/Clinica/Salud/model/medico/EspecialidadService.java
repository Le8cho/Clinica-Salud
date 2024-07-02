package com.clinicasalud.Clinica.Salud.model.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<String> obtenerEspecialidadesDisponibles() {
        return medicoRepository.findEspecialidadesDisponibles();
    }

    public List<String> obtenerMedicoEspecialidad(Especialidad especialidad){
        return medicoRepository.findByNombreEspecialidad(especialidad);
    }

}
