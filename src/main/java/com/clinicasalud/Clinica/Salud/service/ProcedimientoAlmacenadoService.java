package com.clinicasalud.Clinica.Salud.service;

import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.diagnostico.Diagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class ProcedimientoAlmacenadoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cita> obtenerCitasPorFechaYMedico(Long medicoId, String fechaInicio, String fechaFin) {
        String sql = "EXEC ObtenerCitasPorFechaYMedico ?, ?, ?";
        return jdbcTemplate.query(sql, new Object[]{medicoId, fechaInicio, fechaFin},
                (ResultSet rs, int rowNum) -> {
                    Cita cita = new Cita();
                    cita.setIdCita(rs.getLong("ID_Cita"));
                    // Establecer otros campos de Cita desde ResultSet
                    return cita;
                });
    }

    public void actualizarEstadoCita(Long citaId, String nuevoEstado) {
        String sql = "EXEC ActualizarEstadoCita ?, ?";
        jdbcTemplate.update(sql, citaId, nuevoEstado);
    }

    public List<Diagnostico> obtenerDiagnosticosPorPaciente(Long pacienteId) {
        String sql = "EXEC ObtenerDiagnosticosPorPaciente ?";
        return jdbcTemplate.query(sql, new Object[]{pacienteId},
                (ResultSet rs, int rowNum) -> {
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setIdDiagnostico(rs.getLong("ID_Diagnostico"));
                    // Establecer otros campos de Diagnostico desde ResultSet
                    return diagnostico;
                });
    }
}