package com.clinicasalud.Clinica.Salud.model.cita;

import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import com.clinicasalud.Clinica.Salud.service.ProcedimientoAlmacenadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ProcedimientoAlmacenadoService procedimientoAlmacenadoService;

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

    public List<Cita> obtenerCitasPorFechaYMedico(Long medicoId, String fechaInicio, String fechaFin) {
        return procedimientoAlmacenadoService.obtenerCitasPorFechaYMedico(medicoId, fechaInicio, fechaFin);
    }

    public void actualizarEstadoCita(Long citaId, String nuevoEstado) {
        procedimientoAlmacenadoService.actualizarEstadoCita(citaId, nuevoEstado);
    }

    public List<Diagnostico> obtenerDiagnosticosPorPaciente(Long pacienteId) {
        return procedimientoAlmacenadoService.obtenerDiagnosticosPorPaciente(pacienteId);
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
        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setEstadoCita(EstadoCita.Cancelada); // Establecer el estado como Cancelada
            citaRepository.save(cita); // Guardar la cita actualizada con el estado cancelado
            return true;
        }
        else {
            return false; // La cita no existe en la base de datos
        }

    }
    //FUNCION ESCALAR 1 FABRIZIO
    public double calcularDuracionPromedio() {
        List<Cita> citas = citaRepository.findAll();
        long totalDuration = 0;
        for (Cita cita : citas) {
            long duration = cita.getFechaFin().getTimeInMillis() - cita.getFechaInicio().getTimeInMillis();
            totalDuration += duration;
        }
        return (double) totalDuration / citas.size() / TimeUnit.MINUTES.toMillis(1);
    }
}

