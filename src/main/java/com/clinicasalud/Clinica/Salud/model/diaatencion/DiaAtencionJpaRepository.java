package com.clinicasalud.Clinica.Salud.model.diaatencion;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaAtencionJpaRepository extends JpaRepository<DiaAtencion,Long> {


}
