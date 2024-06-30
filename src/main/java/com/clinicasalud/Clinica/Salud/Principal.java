package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.service.CitaService;
import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.cita.EstadoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private CitaService citaService;

    private final Scanner input = new Scanner(System.in);

    public void menu() {
        System.out.println("Bienvenido al sistema de gestión de la Clínica de Salud");
        int opcion;
        do {
            menuOpciones();
            while (!input.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                input.next(); // Limpiar entrada inválida
            }
            opcion = input.nextInt();
            input.nextLine(); // Consumir el salto de línea
            evaluandoOpcion(opcion);
        } while (opcion != 0);
    }

    public void menuOpciones() {
        System.out.println("""
                Elija la opción a través de su número:
                1.- Registrar médicos en el sistema
                2.- Registrar horario de médico
                3.- Registrar Paciente
                4.- Registrar cita
                5.- Modificar cita
                6.- Obtener reporte de citas
                7.- Obtener citas programadas
                8.- Obtener citas por paciente
                0.- Saliste del Sistema lo lamento papu
                """);
        System.out.print("Opción: ");
    }

    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarMedico();
            case 2 -> registrarHorarioMedico();
            case 3 -> registrarPaciente();
            case 4 -> registrarCita();
            case 5 -> modificarCita();
            case 6 -> obtenerReporteCitas();
            case 7 -> obtenerCitasProgramadas();
            case 8 -> obtenerCitasPorPaciente();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    public void registrarMedico() {
        System.out.println("Funcionalidad de registrar médico no implementada.");
    }

    public void registrarHorarioMedico() {
        System.out.println("Funcionalidad de registrar horario de médico no implementada.");
    }

    public void registrarPaciente() {
        System.out.println("Funcionalidad de registrar paciente no implementada.");
    }

    public void registrarCita() {
        System.out.println("Funcionalidad de registrar cita no implementada.");
    }

    public void modificarCita() {
        System.out.println("Funcionalidad de modificar cita no implementada.");
    }
    public void obtenerCitasProgramadas() {
        System.out.println("Funcionalidad de obtener citas programadas no implementada.");
    }

    public void obtenerCitasPorPaciente() {
        System.out.println("Funcionalidad de obtener citas por paciente no implementada.");
    }

    public void obtenerReporteCitas() {
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
                LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
                LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);

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
                System.out.printf("Cita ID: %d, Paciente: %s %s, Médico: %s %s, Fecha: %s, Estado: %s%n",
                        cita.getIdCita(),
                        cita.getPaciente().getPersona().getNombres(),
                        cita.getPaciente().getPersona().getApellidos(),
                        cita.getMedico().getNombres(),
                        cita.getMedico().getApellidos(),
                        cita.getFecha().toString(),
                        cita.getEstadoCita().toString());
            }
        }
    }
}
