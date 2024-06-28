package com.clinicasalud.Clinica.Salud;

import java.util.Scanner;

public class Principal {
    private Scanner input = new Scanner(System.in);

    public void menu() {
        System.out.println("Hola");
        int opcion = 0;
        do{
            menuOpciones();
            opcion = input.nextInt();
            input.skip("\n");
            evaluandoOpcion(opcion);
        } while(opcion != 0);
    }

    public void menuOpciones(){
        System.out.println("""
                Elija la opción a través de su número:
                1.- Registrar médicos en el sistema
                2.- Registrar horario de médico
                3.- Registrar Paciente
                4.- Registrar cita
                5.- Modificar cita
                6.- Obtener reporte de citas
                7. Obtener citas programadas
                8.- Obtener citas por paciente
                0.- Saliste del Sistema, lo lamento papu
                """);
        System.out.print("Opcion: ");
    }

    public void evaluandoOpcion(int opcion){

        switch(opcion){
            case 1 ->{
                nada();
            }
            case 2->{
                nada();
            }
            case 3->{
                nada();
            }
            case 4->{
                nada();
            }
            case 5 ->{
                nada();
            }
            case 6 ->{
                nada();
            }
            case 7 ->{
                nada();
            }
            case 8 ->{
                nada();
            }
            default ->{
                nada();
            }
        }

    }

    public void nada(){
        System.out.println("nada");
    }
}
