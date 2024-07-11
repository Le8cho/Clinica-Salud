package com.clinicasalud.Clinica.Salud.model.cita;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class ObtenerReporteCitas {
    @Autowired
    private CitaService citaService;
    private final Scanner input = new Scanner(System.in);

    public void run() {
        System.out.println("Generar reporte de citas:");
        System.out.println("Seleccione el criterio de filtrado:");
        System.out.println("1. Rango de fechas");
        System.out.println("2. Profesional de salud");
        System.out.println("3. Estado de la cita");

        int criterio = input.nextInt();
        input.nextLine(); // Consumir el salto de línea

        switch (criterio) {
            case 1 -> {
                System.out.println("Ingrese la fecha de inicio (yyyy-MM-dd HH:mm): ");
                String startDateStr = input.nextLine();
                System.out.println("Ingrese la fecha de fin (yyyy-MM-dd HH:mm): ");
                String endDateStr = input.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, formatter);

                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                List<Cita> citas = citaService.obtenerCitasPorRangoDeFechas(startDate, endDate);
                imprimirReporte(citas);
            }
            case 2 -> {
                System.out.println("Ingrese el ID del profesional de salud: ");
                Long medicoId = input.nextLong();
                input.nextLine(); // Consumir el salto de línea

                List<Cita> citas = citaService.obtenerCitasPorMedico(medicoId);
                imprimirReporte(citas);
            }
            case 3 -> {
                System.out.println("Ingrese el estado de la cita (Programada, Atendida, Cancelada): ");
                String estadoStr = input.nextLine();
                System.out.println("Estado ingresado: " + estadoStr); // Añadir esta línea
                EstadoCita estado = EstadoCita.valueOf(estadoStr);
                List<Cita> citas = citaService.obtenerCitasPorEstado(estado);
                imprimirReporte(citas);
            }
            default -> System.out.println("Criterio no válido.");
        }
    }
    private void imprimirReporte(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas con los criterios seleccionados.");
        } else {
            for (Cita cita : citas) {
                Hibernate.initialize(cita.getPaciente()); // Inicializar Paciente
                Hibernate.initialize(cita.getMedico()); // Inicializar Medico
                System.out.printf("Cita ID: %d, Paciente: %s %s, Médico: %s %s, Fecha: %s,Hora: %s, Estado: %s%n",
                        cita.getIdCita(),
                        cita.getPaciente().getPersona().getNombres(),
                        cita.getPaciente().getPersona().getApellidos(),
                        cita.getMedico().getNombres(),
                        cita.getMedico().getApellidos(),
                        cita.getFecha().toString(),
                        cita.getHoraInicio().toString(),
                        cita.getEstadoCita().toString());
            }
        }
    }

}
