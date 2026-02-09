-- Script de Gestión de Residuos
-- Crear base de datos
DROP DATABASE IF EXISTS gestion_residuos;
CREATE DATABASE gestion_residuos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gestion_residuos;

-- Tabla usuarios
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_username (username),
    INDEX idx_rol (rol)
) ENGINE=InnoDB;

-- Tabla camiones
CREATE TABLE camiones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    capacidad INT,
    estado VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_matricula (matricula),
    INDEX idx_estado (estado)
) ENGINE=InnoDB;

-- Tabla rutas
CREATE TABLE rutas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    zona VARCHAR(100),
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME,
    hora_fin TIME,
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_zona (zona),
    INDEX idx_dia_semana (dia_semana)
) ENGINE=InnoDB;

-- Tabla asignaciones
CREATE TABLE asignaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    camion_id BIGINT NOT NULL,
    ruta_id BIGINT NOT NULL,
    fecha_asignacion DATE NOT NULL,
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (camion_id) REFERENCES camiones(id) ON DELETE CASCADE,
    FOREIGN KEY (ruta_id) REFERENCES rutas(id) ON DELETE CASCADE,
    INDEX idx_camion (camion_id),
    INDEX idx_ruta (ruta_id),
    INDEX idx_fecha (fecha_asignacion)
) ENGINE=InnoDB;

-- Insertar datos de ejemplo

-- Usuarios (password: "password" encriptado con BCrypt)
INSERT INTO usuarios (username, password, rol, activo) VALUES
('admin', '$2a$10$N.hLnW5U9dPBFvxGmPCQSOaJTFAE6DWQhpZHQ9VYfxZGEOXBHyNKK', 'ADMIN', TRUE),
('coordinador', '$2a$10$N.hLnW5U9dPBFvxGmPCQSOaJTFAE6DWQhpZHQ9VYfxZGEOXBHyNKK', 'COORDINADOR', TRUE),
('coord2', '$2a$10$N.hLnW5U9dPBFvxGmPCQSOaJTFAE6DWQhpZHQ9VYfxZGEOXBHyNKK', 'COORDINADOR', TRUE);

-- Camiones
INSERT INTO camiones (matricula, marca, modelo, capacidad, estado, activo) VALUES
('ABC-1234', 'Volvo', 'FH16', 12000, 'DISPONIBLE', TRUE),
('DEF-5678', 'Mercedes-Benz', 'Actros', 15000, 'EN_RUTA', TRUE),
('GHI-9012', 'Scania', 'R450', 13000, 'DISPONIBLE', TRUE),
('JKL-3456', 'MAN', 'TGX', 14000, 'MANTENIMIENTO', TRUE),
('MNO-7890', 'Iveco', 'Stralis', 11000, 'DISPONIBLE', TRUE);

-- Rutas
INSERT INTO rutas (nombre, zona, dia_semana, hora_inicio, hora_fin, activa) VALUES
('Ruta Centro', 'Centro Histórico', 'LUNES', '06:00:00', '12:00:00', TRUE),
('Ruta Norte', 'Zona Norte', 'MARTES', '07:00:00', '13:00:00', TRUE),
('Ruta Sur', 'Zona Sur', 'MIERCOLES', '06:30:00', '12:30:00', TRUE),
('Ruta Este', 'Zona Este', 'JUEVES', '06:00:00', '11:30:00', TRUE),
('Ruta Oeste', 'Zona Oeste', 'VIERNES', '07:00:00', '13:00:00', TRUE),
('Ruta Industrial', 'Polígono Industrial', 'LUNES', '14:00:00', '18:00:00', TRUE);

-- Asignaciones
INSERT INTO asignaciones (camion_id, ruta_id, fecha_asignacion, activa) VALUES
(1, 1, '2024-02-05', TRUE),
(2, 2, '2024-02-06', TRUE),
(3, 3, '2024-02-07', TRUE),
(5, 4, '2024-02-08', TRUE),
(1, 6, '2024-02-05', TRUE);

-- Verificar datos
SELECT 'Usuarios creados:' AS info;
SELECT * FROM usuarios;

SELECT 'Camiones creados:' AS info;
SELECT * FROM camiones;

SELECT 'Rutas creadas:' AS info;
SELECT * FROM rutas;

SELECT 'Asignaciones creadas:' AS info;
SELECT * FROM asignaciones;
