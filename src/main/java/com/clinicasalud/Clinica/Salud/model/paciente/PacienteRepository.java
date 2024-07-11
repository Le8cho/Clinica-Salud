package com.clinicasalud.Clinica.Salud.model.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("SELECT p.idPaciente FROM Paciente p WHERE p.persona.dni = :dni")
    Long getIdPacienteByDni(@Param("dni") String dni);

    @Query("SELECT COUNT(p) > 0 FROM Paciente p WHERE p.persona.dni = :dni")
    boolean existsByDni(@Param("dni") String dni);

/*
    SELECT p.* FROM Paciente p
    WHERE EXISTS (
            SELECT 1
            FROM Cita c
            WHERE c.ID_Paciente = p.ID_Paciente
    );*/
    @Query("SELECT p FROM Paciente p WHERE EXISTS (SELECT c FROM Cita c WHERE c.paciente.idPaciente = p.idPaciente)")
    List<Paciente> encontrarPacientesConCitas();

    /*SELECT p.ID_Paciente, p.Nombres AS Nombre_Paciente, p.Apellidos AS Apellido_Paciente,
       (SELECT CONCAT(m.Nombres, ' ', m.Apellidos) FROM Medico m WHERE m.ID_Medico = c.ID_Medico) AS Nombre_Medico,
       c.ID_Cita, c.Hora_Inicio, c.Fecha, c.Motivo_Consulta, c.Estado_Cita
FROM Paciente p
JOIN Cita c ON p.ID_Paciente = c.ID_Paciente; */
    @Query("SELECT p.idPaciente, p.persona.nombres AS Nombre_Paciente, p.persona.apellidos AS Apellido_Paciente, " +
            "(SELECT CONCAT(m.nombres, ' ', m.apellidos) FROM Medico m WHERE m.id = c.medico.id) AS Nombre_Medico, " +
            "c.idCita, c.horaInicio, c.fecha, c.motivoConsulta, c.estadoCita " +
            "FROM Paciente p JOIN Cita c ON p.idPaciente = c.paciente.idPaciente")
    List<Object[]> obtenerTodosPacientesConCitasYMedicos();
}

