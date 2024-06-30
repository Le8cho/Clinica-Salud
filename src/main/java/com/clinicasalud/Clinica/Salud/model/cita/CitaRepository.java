package com.clinicasalud.Clinica.Salud.model.cita;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Long> {
    List<Cita> findByPacienteIdPaciente(Long idPaciente);
}
