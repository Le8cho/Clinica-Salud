package com.clinicasalud.Clinica.Salud.model.cita;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    List<Cita> findByPacienteIdPaciente(Long idPaciente);
  
    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.fecha BETWEEN :startDate AND :endDate")
    List<Cita> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.medico.id = :medicoId")
    List<Cita> findByMedico(@Param("medicoId") Long medicoId);

    @EntityGraph(attributePaths = {"paciente", "medico"})
    @Query("SELECT c FROM Cita c WHERE c.estadoCita = :estado")
    List<Cita> findByEstado(@Param("estado") EstadoCita estado);

    //FUNCION TABLA 1 FABRIZIO
    @Query("SELECT c FROM Cita c WHERE c.medico.id = :medicoId")
    List<Cita> findCitasByMedicoId(@Param("medicoId") Long medicoId);

    //FUNCION TABLA 2 FABRIZIO
    @Query("SELECT c FROM Cita c WHERE c.fechaInicio BETWEEN :startDate AND :endDate")
    List<Cita> findCitasByFecha(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
