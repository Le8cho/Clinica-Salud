package com.clinicasalud.Clinica.Salud.model.cita;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    List<Cita> findByPacienteIdPaciente(Long idPaciente);
  
    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.fecha BETWEEN :startDate AND :endDate")
    List<Cita> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.medico.id = :medicoId")
    List<Cita> findByMedico(@Param("medicoId") Long medicoId);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.medico.id = :medicoId AND c.fecha= :fecha")
    List<Cita> findByMedicoandFecha(@Param("medicoId") Long medicoId, @Param("fecha") LocalDate fecha);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.estadoCita = :estado")
    List<Cita> findByEstado(@Param("estado") EstadoCita estado);

    @Query("SELECT COUNT(c) > 0 FROM Cita c WHERE c.medico.id = :idMedico AND c.fecha = :fecha AND c.horaInicio = :horaInicio")
    boolean existsByMedicoAndFechaAndHoraInicio(Long idMedico,LocalDate fecha, LocalTime horaInicio);
}
