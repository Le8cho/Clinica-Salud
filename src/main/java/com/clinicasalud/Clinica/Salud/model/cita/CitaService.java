package com.clinicasalud.Clinica.Salud.model.cita;

import com.clinicasalud.Clinica.Salud.model.medico.Medico;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoRepository;
import com.clinicasalud.Clinica.Salud.model.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinicasalud.Clinica.Salud.model.cita.CitaRepository;

import com.clinicasalud.Clinica.Salud.model.paciente.PacienteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public List<Cita> obtenerCitasPorRangoDeFechas(LocalDate startDate, LocalDate endDate) {
        return citaRepository.findByDateRange(startDate, endDate);
    }

    public List<Cita> obtenerCitasPorMedico(Long medicoId) {
        return citaRepository.findByMedico(medicoId);
    }
    public List<Cita> obtenerCitasPorMedicoyFecha(Long medicoId, LocalDate fecha) {
        return citaRepository.findByMedicoandFecha(medicoId,fecha);
    }

    public List<Cita> obtenerCitasPorEstado(EstadoCita estado) {
        return citaRepository.findByEstado(estado);
    }

    public List<Cita> obtenerCitasPorPaciente(Long idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    @Transactional
    public Cita modificarCita(Long idCita, String nuevaFecha, String nuevaHora) {
        Cita cita = citaRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        LocalDate fecha = LocalDate.parse(nuevaFecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime horaInicio = LocalTime.parse(nuevaHora, DateTimeFormatter.ofPattern("HH:mm"));

        cita.setFecha(fecha);
        cita.setHoraInicio(horaInicio);

        return citaRepository.save(cita);
    }

    @Transactional
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
    public boolean existeCita(Long idMedico, LocalDate fecha, LocalTime horaInicio){
        return (citaRepository.existsByMedicoAndFechaAndHoraInicio(idMedico, fecha, horaInicio));
    }
    @Transactional
    public void registrarCita(Long idPaciente, Long idMedico, LocalTime horaInicio, LocalDate fecha, String motivoConsulta, EstadoCita estadoCita) {

        Paciente paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + idPaciente));
        Medico medico = medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + idMedico));

        // Crear una nueva instancia de Cita
        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setHoraInicio(horaInicio);
        cita.setFecha(fecha);
        cita.setMotivoConsulta(motivoConsulta);
        cita.setEstadoCita(estadoCita);

        // Guardar la cita en la base de datos
        citaRepository.save(cita);

        System.out.println("Cita registrada correctamente.");
    }
}



