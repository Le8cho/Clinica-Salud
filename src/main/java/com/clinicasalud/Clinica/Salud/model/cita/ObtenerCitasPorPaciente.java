package com.clinicasalud.Clinica.Salud.model.cita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ObtenerCitasPorPaciente {
    @Autowired
    private CitaService citaService;
    private final Scanner input = new Scanner(System.in);
    public void run() {
        System.out.print("Ingrese el ID del paciente: ");
        Long idPaciente = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.println("Citas del paciente con ID " + idPaciente + ":");
        citaService.obtenerCitasPorPaciente(idPaciente).forEach(System.out::println);
    }
}
