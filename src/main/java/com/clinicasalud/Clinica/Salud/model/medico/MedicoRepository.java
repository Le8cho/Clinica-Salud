package com.clinicasalud.Clinica.Salud.model.medico;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Consultas existentes
    @Query("SELECT DISTINCT m.especialidad FROM Medico m WHERE m.estado = 1")
    List<String> findEspecialidadesDisponibles();

    @Query("SELECT m.apellidos, m.nombres FROM Medico m WHERE m.especialidad = :nombreEspecialidad AND m.estado = 1")
    List<String> findByNombreEspecialidad(@Param("nombreEspecialidad") Especialidad especialidad);

    @Query("SELECT COUNT(m) > 0 FROM Medico m WHERE m.dni = :dni")
    boolean existsByDni(@Param("dni") String dni);

    @Query("SELECT da.diaSemana, hm.horaInicio, hm.horaFin " +
            "FROM Medico m " +
            "JOIN m.idHorarioMedico hm " +
            "JOIN hm.horarioDiaList hd " +
            "JOIN hd.horarioDiaKey.diaAtencion da " +
            "WHERE m.nombres = :nombres AND m.apellidos = :apellidos " +
            "ORDER BY da.idDia, hm.horaInicio")
    List<String> findHorarioMedico(@Param("nombres") String nombres, @Param("apellidos") String apellidos);

    @Query("SELECT m.id FROM Medico m WHERE m.dni = :dni")
    Long getIdMedicoByDni(@Param("dni") String dni);

    @Query("SELECT m.id FROM Medico m WHERE m.apellidos = :apellidos AND m.nombres = :nombres ")
    Long getIdMedicoByNombreAndApellido(@Param("apellidos") String apellido, @Param("nombres") String nombre);

    // Nuevas consultas
    @Query("SELECT c, p, m FROM Cita c " +
            "JOIN c.paciente p " +
            "JOIN c.medico m")
    List<Object[]> obtenerTodasLasCitas();

    @Query("SELECT c, p, m, d, t FROM Cita c " +
            "JOIN c.paciente p " +
            "JOIN c.medico m " +
            "JOIN c.diagnosticos d " +
            "JOIN d.tratamientos t")
    List<Object[]> obtenerDiagnosticosYTratamientos();

    @Query("SELECT d, a FROM Diagnostico d " +
            "JOIN d.apoyoDiagnosticos a")
    List<Object[]> obtenerApoyosDiagnosticos();

    @Query("SELECT m.nombres, m.apellidos, m.especialidad, COUNT(c) FROM Medico m " +
            "LEFT JOIN m.citaList c " +
            "GROUP BY m.nombres, m.apellidos, m.especialidad")
    List<Object[]> obtenerCantidadCitasPorMedico();
}
