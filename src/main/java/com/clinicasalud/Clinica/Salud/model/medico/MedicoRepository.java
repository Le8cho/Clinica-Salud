package com.clinicasalud.Clinica.Salud.model.medico;
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

    @Query("SELECT da.diaSemana, hm.horaInicio, hm.horaFin " +
            "FROM Medico m " +
            "JOIN m.idHorarioMedico hm " +
            "JOIN hm.horarioDiaList hd " +
            "JOIN hd.horarioDiaKey.diaAtencion da " +
            "WHERE m.nombres = :nombres AND m.apellidos = :apellidos " +
            "ORDER BY da.idDia, hm.horaInicio")
    List<String> findHorarioMedico(String nombres, String apellidos);

    @Query("SELECT m.id FROM Medico m WHERE m.apellidos = :apellidos AND m.nombres = :nombres ")
    Long getIdMedicoByNombreAndApellido(@Param("apellidos") String apellido, @Param("nombres") String nombre);

}
