package com.clinicasalud.Clinica.Salud.model.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("SELECT p.idPaciente FROM Paciente p WHERE p.persona.dni = :dni")
    Long getIdPacienteByDni(@Param("dni") String dni);

    @Query("SELECT COUNT(p) > 0 FROM Paciente p WHERE p.persona.dni = :dni")
    boolean existsByDni(@Param("dni") String dni);
}

