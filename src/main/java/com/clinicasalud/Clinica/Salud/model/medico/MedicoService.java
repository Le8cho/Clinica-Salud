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
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<String> obtenerHorarioMedico(String apellido, String nombre) {
        return medicoRepository.findHorarioMedico(apellido, nombre);
    }

    public long obtenerIdMedicoPorNombreYApellido(String nombre, String apellido) {
        return medicoRepository.getIdMedicoByNombreAndApellido(nombre, apellido);
    }
}
