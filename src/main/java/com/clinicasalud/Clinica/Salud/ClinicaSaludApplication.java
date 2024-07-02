package com.clinicasalud.Clinica.Salud;

import com.clinicasalud.Clinica.Salud.model.cita.CitaService;
import com.clinicasalud.Clinica.Salud.model.medico.EspecialidadService;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoService;
import com.clinicasalud.Clinica.Salud.model.medico.MedicoRepository;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteRepository;
import com.clinicasalud.Clinica.Salud.model.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClinicaSaludApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ClinicaSaludApplication.class, args);
	}

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private EspecialidadService especialidadService;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private CitaService citaService;

	//Ejecucion en Consola
	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(pacienteRepository, pacienteService,especialidadService, medicoService, citaService);
		principal.menu();
	}
}