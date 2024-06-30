package com.clinicasalud.Clinica.Salud;
import com.clinicasalud.Clinica.Salud.model.cita.Cita;
import com.clinicasalud.Clinica.Salud.model.cita.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Principal implements CommandLineRunner {

    private final Scanner input = new Scanner(System.in);

    @Autowired
    private CitaService citaService;

    public void run(String... args) {
        menu();
    }

    public void menu() {
        System.out.println("Hola");
        int opcion;
        do {
            menuOpciones();
            opcion = input.nextInt();
            input.skip("\n");
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
                5.- Modificar/cancelar cita
                6.- Obtener reporte de citas
                7.- Obtener citas programadas
                8.- Obtener citas por paciente
                0.- Saliste del Sistema, lo lamento papu
                """);
        System.out.print("Opcion: ");
    }

    public void evaluandoOpcion(int opcion) {
        switch (opcion) {
            case 1, 2, 3, 4, 6, 7 -> nada(); // Funcionalidades aún no implementadas
            case 5 -> {
                System.out.println("1.- Modificar cita");
                System.out.println("2.- Cancelar cita");
                System.out.print("Seleccione una opción: ");
                int subOpcion = input.nextInt();
                input.skip("\n");

                switch (subOpcion) {
                    case 1 -> modificarCita();
                    case 2 -> cancelarCita();
                    default -> System.out.println("Opción inválida.");
                }
            }
            case 8 -> {
                System.out.print("Ingrese el ID del paciente: ");
                Long idPaciente = input.nextLong();
                input.nextLine(); // Consumir el salto de línea

                System.out.println("Citas del paciente con ID " + idPaciente + ":");
                citaService.obtenerCitasPorPaciente(idPaciente).forEach(System.out::println);
            }
            case 0 -> System.out.println("Saliste papu");
            default -> System.out.println("Opción no válida.");
        }
    }

    public void modificarCita() {
        System.out.print("Ingrese el ID de la cita a modificar: ");
        Long idCita = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese la nueva fecha (YYYY-MM-DD): ");
        String nuevaFecha = input.nextLine();

        System.out.print("Ingrese la nueva hora (HH:MM): ");
        String nuevaHora = input.nextLine();

        if (!validarFecha(nuevaFecha)) {
            System.out.println("Fecha inválida.");
            return;
        }

        if (!validarHora(nuevaHora)) {
            System.out.println("Hora inválida.");
            return;
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaIngresada = LocalDate.parse(nuevaFecha, DateTimeFormatter.ISO_LOCAL_DATE);

        if (fechaIngresada.isBefore(fechaActual)) {
            System.out.println("No se puede programar una cita en fechas anteriores a la actual.");
            return;
        }

        Cita cita = citaService.modificarCita(idCita, nuevaFecha, nuevaHora);
        if (cita != null) {
            System.out.println("Cita modificada exitosamente: " + cita);
        } else {
            System.out.println("No se encontró una cita con ID " + idCita);
        }
    }

    public void cancelarCita() {
        System.out.print("Ingrese el ID de la cita a cancelar: ");
        Long idCita = input.nextLong();
        input.nextLine(); // Consumir el salto de línea

        System.out.print("¿Está seguro de cancelar esta cita? (S/N): ");
        String confirmacion = input.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean cancelada = citaService.cancelarCita(idCita);
            if (cancelada) {
                System.out.println("Cita cancelada correctamente.");
            } else {
                System.out.println("No se encontró una cita con ID " + idCita);
            }
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }

    public void nada() {
        System.out.println("La opción no está habilitada");
    }

    public boolean validarFecha(String fecha) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

    public boolean validarHora(String hora) {
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(hora);
        return matcher.matches();
    }
}
