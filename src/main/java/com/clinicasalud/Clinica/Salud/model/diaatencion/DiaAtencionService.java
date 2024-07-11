package com.clinicasalud.Clinica.Salud.model.diaatencion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaAtencionService {
    @Autowired
    DiaAtencionJpaRepository diaAtencionJpaRepository;
}
