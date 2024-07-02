package com.clinicasalud.Clinica.Salud.model.paciente;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class PacienteService {

    @Autowired
    private Validator validator;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente validarDatos(@Valid DatosCrearPaciente datosCrearPaciente){

        Set<ConstraintViolation<DatosCrearPaciente>> violations = validator.validate(datosCrearPaciente);

        if(!violations.isEmpty()){
            StringBuilder errorMessage = new StringBuilder("Datos no válidos:\n");
            for (ConstraintViolation<DatosCrearPaciente> violation : violations) {
                errorMessage.append("Campo: ").append(violation.getPropertyPath())
                        .append("\nError: ").append(violation.getMessage())
                        .append("\n");
            }
            System.out.println(errorMessage);
            return null;
        }
        return new Paciente(datosCrearPaciente);
    }

    public Long obtenerIdPacientePorDni(String dni) {
        return pacienteRepository.getIdPacienteByDni(dni);
    }

    public boolean pacienteExiste(String dni) {
        return pacienteRepository.existsByDni(dni);
    }
}
