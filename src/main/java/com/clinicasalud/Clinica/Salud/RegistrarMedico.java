package com.clinicasalud.Clinica.Salud;
import com.clinicasalud.Clinica.Salud.model.horariomedico.HorarioMedicoService;
import com.clinicasalud.Clinica.Salud.model.medico.Especialidad;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegistrarMedico {
    @Autowired
    private HorarioMedicoService horarioMedicoService;
    @Autowired
    private MedicoService medicoService;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private final Scanner input = new Scanner(System.in);

    public void run(){
        System.out.println("Primero Se creara el horario del Médico");
        //int id_horariomedico=registrarHorarioMedico();
        boolean novalido=false;
        char sexo='M';
        int estado;
        String dni,telefono,correo;
        System.out.println("Ingrese los nombres (Juan Jose)");
        String nombres = input.nextLine();
        System.out.println("Ingrese los apellidos (Perez Galvez)");
        String apellidos = input.nextLine();
        do {
            System.out.println("Ingrese el sexo (M Masculino | F Femenino)");
            String stringsexo=input.nextLine();
            if (stringsexo.length() == 1) {
                sexo = stringsexo.charAt(0);
                novalido=false;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese solo un carácter (M o F).");
                novalido=true;
            }
        }while(novalido);
        do {
            System.out.println("Ingrese el estado (0 Activo | 1 Inactivo)");
            estado=input.nextInt();
            input.nextLine();
            if (estado== 1 || estado==0) {
                novalido=false;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese (0 o 1).");
                input.next();
                novalido=true;
            }
        }while(novalido);
        do {
            System.out.println("Ingrese el DNI (12345678)");
            dni = input.nextLine();
            if(8!=dni.length()){
                novalido=true;
                System.out.println("Ingrese un numero de dni válido");
            }else{
                novalido=false;
            }
        }while(novalido);
        do {
            System.out.println("Ingrese el telefono (987654321)");
            telefono = input.nextLine();
            if(9!=telefono.length()){
                novalido=true;
                System.out.println("Ingrese un numero válido");
            }else{
                novalido=false;
            }
        }while(novalido);
        do{
            System.out.println("Ingrese el correo (ejemplo@gmail.com)");
            correo = input.nextLine();
            if(isValidEmail(correo)){
                novalido=false;
            }else{
                System.out.println("Ingrese un correo válido");
                novalido=true;
            }
        }while(novalido);
        Especialidad especialidad1 = null;
        do {
            System.out.println("Ingrese la especialidad (Pediatria):");
            String especial = input.nextLine();
            especialidad1 = Especialidad.fromString(especial);
            if (especialidad1 == null) {
                novalido = true;
                System.out.println("Coloque una especialidad que exista.");
            } else {
                novalido = false;
            }
        } while (novalido);
        do{
            System.out.println("Ingrese una opción");
            System.out.println("0:Cancelar");
            System.out.println("1:Ingresar");
            if (input.hasNextInt()) {
                int opcion = input.nextInt();
                input.nextLine();
                if (opcion == 0) {
                    return;
                } else if (opcion == 1) {
                    medicoService.crearMedico(sexo, nombres, apellidos, dni, telefono, correo, estado, especialidad1);
                    System.out.println("Médico registrado exitosamente.");
                    novalido = false;
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese (0 o 1).");
                    novalido = true;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese (0 o 1).");
                input.next();
                novalido = true;
            }
        }while(novalido);
        input.close();
    }
    public void registrarHorarioMedico(){

        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el horario del médico:");
            LocalTime horadeInicio = leerHora("Hora de inicio (HH:MM): ");
            LocalTime horaFin = leerHora("Hora de fin (HH:MM): ");

            if (horadeInicio != null && horaFin != null) {
                if (horadeInicio.isBefore(horaFin)) {
                    horarioMedicoService.registrarHorarioMedico(horadeInicio,horaFin);
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
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
