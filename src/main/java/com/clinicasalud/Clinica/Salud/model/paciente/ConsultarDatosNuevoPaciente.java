package com.clinicasalud.Clinica.Salud.model.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class ConsultarDatosNuevoPaciente {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PacienteRepository pacienteRepository;
    private final Scanner input = new Scanner(System.in);
    public void run() {
        System.out.println("Ingresar Nombres por favor:");
        var nombresPac = input.nextLine();
        System.out.println("Ingresar apellidos por favor:");
        var apellidosPac = input.nextLine();
        System.out.println("Ingresar Numero de DNI por favor:");
        var dniPac = input.nextLine();
        System.out.println("Ingresar Sexo (M/F) por favor:");
        var sexoPac = input.nextLine().toUpperCase();
        System.out.println("Ingresar Numero de Telefono por favor:");
        var tlfPac = input.nextLine();
        System.out.println("Ingresar direccion por favor:");
        var direccion = input.nextLine();
        System.out.println("Ingresar Fecha de Nacimiento (AAAA-MM-DD) por favor:");
        var fechaNacPac = LocalDate.parse(input.nextLine());

        //Validamos los campos ingresado
        DatosCrearPaciente datosCrearPaciente = new DatosCrearPaciente(
                nombresPac,
                apellidosPac,
                dniPac,
                sexoPac,
                tlfPac,
                direccion,
                fechaNacPac
        );

        Paciente paciente = pacienteService.validarDatos(datosCrearPaciente);

        pacienteRepository.save(paciente);
    }
}
