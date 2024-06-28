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

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hola soy un pollito perfeccionista que no disfruta equivocarse y tiene miedo de hacerlo mal");
	}
}
