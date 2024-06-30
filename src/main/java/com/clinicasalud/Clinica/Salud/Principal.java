package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedico;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedicoJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private HorarioMedicoJpaService horarioMedicoJpaService;

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
                0.- Saliste del Sistema, lo lamento papu
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
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el horario del médico:");
            LocalTime horadeInicio = leerHora("Hora de inicio (HH:MM): ");
            LocalTime horaFin = leerHora("Hora de fin (HH:MM): ");

            if (horadeInicio != null && horaFin != null) {
                if (horadeInicio.isBefore(horaFin)) {
                    HorarioMedico horarioMedico = new HorarioMedico();
                    horarioMedico.setHoraInicio(Time.valueOf(horadeInicio));
                    horarioMedico.setHoraFin(Time.valueOf(horaFin));

                    horarioMedicoJpaService.save(horarioMedico);
                    System.out.println("Horario del médico registrado exitosamente.");
                } else {
                    System.out.println("La hora de inicio debe ser anterior a la hora de fin.");
                }
            }

            System.out.print("¿Desea registrar otro horario? (S/N): ");
            String respuesta = input.nextLine().trim().toUpperCase();
            continuar = respuesta.equals("S");
        }
    }

    private LocalTime leerHora(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String horaStr = input.nextLine();
            try {
                return LocalTime.parse(horaStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Use el formato HH:MM.");
            }
        }
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

    public void obtenerReporteCitas() {
        System.out.println("Funcionalidad de obtener reporte de citas no implementada.");
    }

    public void obtenerCitasProgramadas() {
        System.out.println("Funcionalidad de obtener citas programadas no implementada.");
    }

    public void obtenerCitasPorPaciente() {
        System.out.println("Funcionalidad de obtener citas por paciente no implementada.");
    }
}
//gampi