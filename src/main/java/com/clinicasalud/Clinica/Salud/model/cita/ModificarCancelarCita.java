package com.clinicasalud.Clinica.Salud.model.cita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class ModificarCancelarCita {
    @Autowired
    private CitaService citaService;

    private final Scanner input = new Scanner(System.in);
    public void run() {
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
