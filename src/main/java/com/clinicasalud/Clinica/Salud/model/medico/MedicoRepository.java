package com.clinicasalud.Clinica.Salud.model.medico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByDni(String dni);
}