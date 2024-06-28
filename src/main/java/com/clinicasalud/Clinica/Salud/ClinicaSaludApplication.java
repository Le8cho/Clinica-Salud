package com.clinicasalud.Clinica.Salud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ClinicaSaludApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaSaludApplication.class, args);
	}


	//Ejecucion en Consola
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.menu();
	}
}
