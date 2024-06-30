package com.clinicasalud.Clinica.Salud.model.horariomedico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioMedicoJpaService extends JpaRepository<HorarioMedico, Long> {
}
