package com.clinicasalud.Clinica.Salud.model.paciente;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class PacienteService {

    @Autowired
    private Validator validator;

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
}
