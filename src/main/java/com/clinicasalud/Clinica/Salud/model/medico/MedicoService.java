package com.clinicasalud.Clinica.Salud.model.medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<String> obtenerHorarioMedico(String apellido, String nombre) {
        return medicoRepository.findHorarioMedico(apellido, nombre);
    }
    public long obtenerIdMedicoPorDni(String dni) {
        return medicoRepository.getIdMedicoByDni(dni);
    }
    public boolean medicoExiste(String dni) {
        return medicoRepository.existsByDni(dni);
    }
    public long obtenerIdMedicoPorNombreYApellido(String nombre, String apellido) {
        return medicoRepository.getIdMedicoByNombreAndApellido(nombre, apellido);
    }
    public void crearMedico(char sexo, String nombres,String apellidos, String dni, String telefono, String correo,int estado, Especialidad especialidad){
        Medico medico = new Medico();
        medico.setNombres(nombres);
        medico.setApellidos(apellidos);
        medico.setDni(dni);
        medico.setTelefono(telefono);
        medico.setCorreo(correo);
        medico.setSexo(sexo);
        medico.setEstado(estado);
        medico.setEspecialidad(especialidad);
        medicoRepository.save(medico);
    }
}
