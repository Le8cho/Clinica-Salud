use master
GO
DROP DATABASE IF EXISTS changoBase
GO
CREATE DATABASE changoBase
GO
use changoBase
GO
-- Eliminación de tablas si existen
DROP TABLE IF EXISTS Tratamiento, Diagnostico, Apoyo_Diagnostico, Consulta, Cita, Paciente, Medico, Horario_Medico, Dia_Atencion, Usuario;

-- Creación de la tabla Usuario
CREATE TABLE Usuario (
    ID_Usuario BIGINT NOT NULL PRIMARY KEY,
	[Username] VARCHAR(255) NOT NULL UNIQUE,
	[Password] VARCHAR(255) NOT NULL,
    Rol_Usuario VARCHAR(255) NOT NULL
);

-- Creación de la tabla Dia_Atencion
CREATE TABLE Dia_Atencion (
    ID_Dia BIGINT NOT NULL PRIMARY KEY,
    Dia VARCHAR(50) NOT NULL UNIQUE
);

-- Creación de la tabla Horario_Medico
CREATE TABLE Horario_Medico (
    ID_Horario_Medico BIGINT NOT NULL PRIMARY KEY,
    Hora_Inicio TIME NOT NULL,
    Hora_Fin TIME NOT NULL,
);

-- Creación de la tabla Horario_Dia
CREATE TABLE Horario_Dia (
    ID_Horario_Medico BIGINT NOT NULL,
	ID_Dia BIGINT NOT NULL,
	PRIMARY KEY(ID_Horario_Medico, ID_Dia),
	FOREIGN KEY(ID_Horario_Medico) REFERENCES Horario_Medico (ID_Horario_Medico),
	FOREIGN KEY(ID_Dia) REFERENCES Dia_Atencion (ID_Dia)
);

-- Creación de la tabla Medico
CREATE TABLE Medico (
    ID_Medico BIGINT NOT NULL PRIMARY KEY,
    ID_Horario_Medico BIGINT NOT NULL,
    Nombres VARCHAR(255) NOT NULL,
    Apellidos VARCHAR(255) NOT NULL,
    DNI CHAR(8) NOT NULL UNIQUE,
    Correo VARCHAR(255) UNIQUE,
    Telefono VARCHAR(15),
    Sexo CHAR(1),
    Estado TINYINT NOT NULL,
    Especialidad VARCHAR(255) NOT NULL,
    FOREIGN KEY (ID_Horario_Medico) REFERENCES Horario_Medico(ID_Horario_Medico)
);

-- Creación de la tabla Paciente
CREATE TABLE Paciente (
    ID_Paciente BIGINT NOT NULL PRIMARY KEY,
    Nombres VARCHAR(255) NOT NULL,
    Apellidos VARCHAR(255) NOT NULL,
    DNI CHAR(8) NOT NULL UNIQUE,
    Direccion VARCHAR(255),  
    Telefono CHAR(15),        
    Sexo CHAR(1),
    Fecha_Nacimiento DATE NOT NULL,
    Estado_Paciente TINYINT NOT NULL
);

-- Creación de la tabla Cita
CREATE TABLE Cita (
    ID_Cita BIGINT NOT NULL PRIMARY KEY,
    ID_Paciente BIGINT NOT NULL,
    ID_Medico BIGINT NOT NULL,
    Hora_Inicio TIME NOT NULL,
    Fecha DATE NOT NULL,
    Motivo_Consulta VARCHAR(255),
    Estado_Cita VARCHAR(50) NOT NULL,
    FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente),
    FOREIGN KEY (ID_Medico) REFERENCES Medico(ID_Medico)
);

-- Creación de la tabla Diagnostico
CREATE TABLE Diagnostico (
    ID_Diagnostico BIGINT NOT NULL PRIMARY KEY,
    ID_Cita BIGINT NOT NULL,
    Descripcion VARCHAR(255),
    Sospecha_Diagnostica TINYINT,
    FOREIGN KEY (ID_Cita) REFERENCES Cita(ID_Cita)
);

-- Creación de la tabla Tratamiento
CREATE TABLE Tratamiento (
    ID_Tratamiento BIGINT NOT NULL PRIMARY KEY,
    ID_Diagnostico BIGINT NOT NULL,
    Duracion INT,
    Prescripcion VARCHAR(255),
    Procedimiento VARCHAR(255),
    FOREIGN KEY (ID_Diagnostico) REFERENCES Diagnostico(ID_Diagnostico)
);

-- Creación de la tabla Apoyo_Diagnostico
CREATE TABLE Apoyo_Diagnostico (
    ID_Apoyo_Diagnostico BIGINT NOT NULL PRIMARY KEY,
    ID_Diagnostico BIGINT NOT NULL,
    Tipo_de_Apoyo VARCHAR(255) NOT NULL,
    FOREIGN KEY (ID_Diagnostico) REFERENCES Diagnostico(ID_Diagnostico)
);

--No Tocar
INSERT INTO Usuario (ID_Usuario, [Username] , [Password], Rol_Usuario) VALUES
(1,'admin','admin', 'ROLE_ADMIN'),
(2,'emp1','emp1', 'ROLE_EMPLOYEE'),
(3,'medic1','12345678', 'ROLE_MEDIC'),
(4,'medic2','23456789', 'ROLE_MEDIC'),
(5,'medic3','34567890', 'ROLE_MEDIC'),
(6,'medic4','45678901', 'ROLE_MEDIC'),
(7,'medic5','56789012', 'ROLE_MEDIC');

--No Tocar
INSERT INTO Dia_Atencion (ID_Dia, Dia) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes');

INSERT INTO Horario_Medico (ID_Horario_Medico, Hora_Inicio, Hora_Fin) VALUES
(1,'08:00', '12:00'),
(2,'08:00', '12:00'),
(3,'13:00', '17:00'),
(4,'13:00', '17:00'),
(5,'08:00', '12:00');

INSERT INTO Medico (ID_Medico, ID_Horario_Medico, Nombres, Apellidos, DNI, Correo, Telefono, Sexo, Estado, Especialidad) VALUES
(1, 1, 'Carlos', 'Méndez', '12345678', 'carlos.mendez@email.com', '999999999', 'M', 1, 'Cardiología'),
(2, 2, 'Ana', 'García', '23456789', 'ana.garcia@email.com', '888888888', 'F', 1, 'Dermatología'),
(3, 3, 'Julio', 'Torres', '34567890', 'julio.torres@email.com', '777777777', 'M', 1, 'Neurología'),
(4, 4, 'Lucia', 'Fernández', '45678901', 'lucia.fernandez@email.com', '666666666', 'F', 1, 'Pediatría'),
(5, 5, 'Marco', 'Ruiz', '56789012', 'marco.ruiz@email.com', '555555555', 'M', 1, 'Gastroenterología');

--Mas Pacientes
INSERT INTO Paciente (ID_Paciente, Nombres, Apellidos, DNI, Direccion, Telefono, Sexo, Fecha_Nacimiento, Estado_Paciente) VALUES
(1, 'Juan', 'Pérez', '87654321', 'Calle Falsa 123', '444444444', 'M', '1990-01-01', 1),
(2, 'Maria', 'López', '98765432', 'Avenida Siempre Viva 456', '333333333', 'F', '1982-02-02', 1),
(3, 'Pedro', 'Martínez', '09876543', 'Camino Largo 789', '222222222', 'M', '1974-03-03', 1),
(4, 'Carmen', 'Sanchez', '10987654', 'Ronda de Nelle 101', '111111111', 'F', '1966-04-04', 1),
(5, 'Luis', 'Gómez', '21098765', 'Plaza del Sol 567', '000000000', 'M', '1958-05-05', 1);

--Mas Citas
INSERT INTO Cita (ID_Cita, ID_Paciente, ID_Medico, Hora_Inicio, Fecha, Motivo_Consulta, Estado_Cita) VALUES
(1, 1, 1, '09:00', '2023-07-10', 'Revisión general', 'Programada'),
(2, 2, 2, '10:00', '2023-07-11', 'Consulta dermatológica', 'Programada'),
(3, 3, 3, '11:00', '2023-07-12', 'Consulta neurológica', 'Programada'),
(4, 4, 4, '12:00', '2023-07-13', 'Control pediátrico', 'Programada'),
(5, 5, 5, '08:00', '2023-07-14', 'Consulta de gastroenterología', 'Programada');

--Mas Diagnosticos
INSERT INTO Diagnostico (ID_Diagnostico, ID_Cita, Descripcion, Sospecha_Diagnostica) VALUES
(1, 1, 'No se observan problemas de salud.', 0),
(2, 2, 'Diagnóstico de eczema.', 1),
(3, 3, 'Sin signos de enfermedad neurológica.', 0),
(4, 4, 'Desarrollo normal para la edad.', 0),
(5, 5, 'Presencia de gastritis.', 1);

--Mas tratamientos Duración ¿0?
INSERT INTO Tratamiento (ID_Tratamiento, ID_Diagnostico, Duracion, Prescripcion, Procedimiento) VALUES
(1, 1, 0, 'Continuar con dieta saludable', 'Revisión anual'),
(2, 2, 30, 'Uso de crema tópica', 'Aplicar crema cada 8 horas'),
(3, 3, 0, 'No requiere tratamiento', 'Seguimiento en seis meses'),
(4, 4, 0, 'Vacunación al día', 'Programar próxima vacunación'),
(5, 5, 10, 'Medicación para gastritis', 'Tomar medicamento antes de las comidas');

--Más diagnosticos
INSERT INTO Apoyo_Diagnostico (ID_Apoyo_Diagnostico, ID_Diagnostico, Tipo_de_Apoyo) VALUES
(1, 2, 'Biopsia de piel'),
(2, 2, 'Análisis de sangre'),
(3, 5, 'Endoscopia'),
(4, 3, 'Resonancia Magnética'),
(5, 4, 'Pruebas de audición');
