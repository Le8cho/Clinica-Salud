package com.clinicasalud.Clinica.Salud.model.medico;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    //Optional<Medico> findByDni(String dni);
    @Query("SELECT DISTINCT m.especialidad FROM Medico m WHERE m.estado = 1")
    List<String> findEspecialidadesDisponibles();

    @Query("SELECT m.apellidos,m.nombres FROM Medico m WHERE m.especialidad = :nombreEspecialidad AND m.estado = 1")
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
    List<String> findHorarioMedico(String nombres, String apellidos);
    @Query("SELECT m.id FROM Medico m WHERE m.dni = :dni")
    Long getIdMedicoByDni(@Param("dni") String dni);

    @Query("SELECT m.id FROM Medico m WHERE m.apellidos = :apellidos AND m.nombres = :nombres ")
    Long getIdMedicoByNombreAndApellido(@Param("apellidos") String apellido, @Param("nombres") String nombre);
    /*
    SELECT m.* FROM Medico m WHERE EXISTS (
       SELECT 1
       FROM Cita c
        WHERE c.ID_Medico = m.ID_Medico);
    */
    @Query("SELECT m FROM Medico m WHERE EXISTS (SELECT c FROM Cita c WHERE c.medico.id = m.id)")
    List<Medico> encontrarMedicosConCitas();

    /*SELECT m.ID_Medico, m.Nombres AS Nombre_Medico, m.Apellidos AS Apellido_Medico, m.Especialidad,
       CONCAT(hm.Hora_Inicio, ' - ', hm.Hora_Fin) AS Horario
    FROM Medico m
    INNER JOIN Horario_Medico hm ON m.ID_Horario_Medico = hm.ID_Horario_Medico;
    */
    @Query("SELECT m.id, m.nombres AS Nombre_Medico, m.apellidos AS Apellido_Medico, m.especialidad, " +
            "(SELECT CONCAT(hm.horaInicio, ' - ', hm.horaFin) FROM HorarioMedico hm WHERE hm.id = m.idHorarioMedico.id) AS Horario " +
            "FROM Medico m")
    List<Object[]> consultarTodosMedicosHorario();

}
