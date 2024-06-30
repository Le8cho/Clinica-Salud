package com.clinicasalud.Clinica.Salud.service;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.cita.CitaRepository;
import com.clinicasalud.Clinica.Salud.model.cita.EstadoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> obtenerCitasPorRangoDeFechas(LocalDateTime startDate, LocalDateTime endDate) {
        return citaRepository.findByDateRange(startDate, endDate);
    }

    public List<Cita> obtenerCitasPorMedico(Long medicoId) {
        return citaRepository.findByMedico(medicoId);
    }

    public List<Cita> obtenerCitasPorEstado(EstadoCita estado) {
        return citaRepository.findByEstado(estado);
    }

    public List<Cita> obtenerCitasPorPaciente(Long idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    public Cita modificarCita(Long idCita, String nuevaFecha, String nuevaHora) {
        Cita cita = citaRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        LocalDate fecha = LocalDate.parse(nuevaFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime horaInicio = LocalTime.parse(nuevaHora, DateTimeFormatter.ofPattern("HH:mm"));

        cita.setFecha(fecha);
        cita.setHoraInicio(horaInicio);

        return citaRepository.save(cita);
    }

    public boolean cancelarCita(Long idCita) {
        return false;
    }

}
