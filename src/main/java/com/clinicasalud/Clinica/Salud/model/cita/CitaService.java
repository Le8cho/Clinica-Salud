// CitaService.java
package com.clinicasalud.Clinica.Salud.model.cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public Cita modificarCita(Long idCita, String nuevaFecha, String nuevaHora) {
        Cita cita = citaRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        LocalDate fecha = LocalDate.parse(nuevaFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime horaInicio = LocalTime.parse(nuevaHora, DateTimeFormatter.ofPattern("HH:mm"));

        cita.setFecha(fecha);
        cita.setHoraInicio(horaInicio);

        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitasPorPaciente(Long idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    public boolean cancelarCita(Long idCita) {
        return false;
    }
}

