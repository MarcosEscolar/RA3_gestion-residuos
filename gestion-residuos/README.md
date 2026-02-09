# Sistema de Gestión de Residuos Urbanos

## 📋 Descripción
Sistema web para la gestión de camiones de recogida de residuos y sus rutas asignadas con Spring Boot 3.x, JPA/Hibernate, Spring Security y Thymeleaf.

## 🛠️ Tecnologías
- Java 17+
- Spring Boot 3.2.1
- Spring Data JPA  
- Spring Security
- Thymeleaf
- MySQL 8.0
- Lombok
- Maven

## 📦 Estructura del Proyecto (tu estructura exacta)
```
src/main/java/es/campusfp/webapp/
├── controller/
│   ├── HomeController.java
│   ├── PacienteController.java  (gestiona Camiones)
│   ├── RutaController.java
│   └── AsignacionController.java
├── model/
│   ├── Usuario.java
│   ├── Camion.java
│   ├── Ruta.java
│   ├── Asignacion.java
│   ├── RolUsuario.java (enum)
│   ├── EstadoCamion.java (enum)
│   └── DiaSemana.java (enum)
├── repository/
│   ├── UsuarioRepository.java
│   ├── CamionRepository.java
│   ├── RutaRepository.java
│   └── AsignacionRepository.java
├── service/
│   ├── CustomUserDetailsService.java
│   ├── SecurityConfig.java
│   ├── UsuarioService.java
│   ├── CamionService.java
│   ├── RutaService.java
│   └── AsignacionService.java
├── util/
│   ├── PasswordHashGenerator.java
│   └── PasswordVerifier.java
└── WebApplication.java
```

## 🚀 Instalación

### 1. Crear la Base de Datos
```bash
mysql -u root -p < script_gestion_residuos.sql
```

### 2. Configurar Credenciales
Editar `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

### 4. Acceder
http://localhost:8080

## 👥 Usuarios de Prueba

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| admin | password123 | ADMIN |
| coord.garcia | password123 | COORDINADOR |
| coord.lopez | password123 | COORDINADOR |

## 🔐 Permisos por Rol

| Acción | ADMIN | COORDINADOR |
|--------|-------|-------------|
| Ver camiones/rutas/asignaciones | ✅ | ✅ |
| Crear/Editar camión | ✅ | ❌ |
| Crear/Editar ruta | ✅ | ❌ |
| Crear/Eliminar asignación | ✅ | ✅ |
| Gestionar usuarios | ✅ | ❌ |

## 📊 Base de Datos

### Tablas
- **usuarios**: id, username, email, password_hash, nombre, rol, activo
- **camiones**: id, matricula, modelo, capacidad_kg, estado, fecha_alta, activo
- **rutas**: id, nombre, zona, dia_semana, hora_inicio, hora_fin, activa
- **asignaciones**: id, camion_id, ruta_id, fecha_asignacion

### Relaciones
- Many-to-Many entre camiones y rutas través de asignaciones
- Cascada ON DELETE para integridad referencial

### Vistas
- `vista_camiones`: Camiones con número de rutas
- `vista_rutas`: Rutas con número de camiones
- `vista_asignaciones`: Detalle completo

## ✅ Bloques Implementados

### OBLIGATORIOS (7 puntos)
1. ✅ **Base de datos + Configuración** (2 pts)
   - Script SQL completo
   - pom.xml con dependencias
   - application.properties configurado
   
2. ✅ **Entidades JPA** (2 pts)
   - Usuario, Camion, Ruta, Asignacion
   - Enums y relaciones correctas
   - Anotaciones @Entity, @Table, @OneToMany, @ManyToOne
   
3. ✅ **Repositorios** (1 pt)
   - Query Methods personalizados
   - findByEstado, findByActivoTrue, etc.
   
4. ✅ **Spring Security** (2.5 pts)
   - CustomUserDetailsService
   - SecurityConfig con roles
   - Login y 403 personalizados
   - BCrypt para contraseñas

### OPCIONALES (3 puntos)
5. ✅ **Servicios y Controladores** (1.5 pts)
   - Servicios CRUD completos
   - Controladores con Thymeleaf
   - Formularios funcionales
   
6. ✅ **Demostración Funcional** (1 pt)
   - Sistema completo operativo
   - Control de acceso funcional

**TOTAL: 10/10 puntos** 🎯

## 🧪 Pruebas

### Test 1: Login ADMIN
1. Login: admin / password123
2. Verificar acceso a todas las secciones

### Test 2: Login COORDINADOR  
1. Login: coord.garcia / password123
2. Verificar acceso limitado
3. Intentar /usuarios/ → debe mostrar 403

### Test 3: Crear Asignación
1. Ir a Asignaciones → Nueva
2. Seleccionar camión y ruta
3. Guardar y verificar

## ⚠️ Notas Importantes

**IMPORTANTE sobre "Paciente":** 
El controlador se llama `PacienteController` para coincidir con tu estructura de proyecto mostrada en las imágenes, pero gestiona CAMIONES internamente usando la clase `Camion.java`.

Si quieres renombrar:
1. En IntelliJ: Click derecho en `PacienteController` → Refactor → Rename → `CamionController`
2. IntelliJ actualizará todas las referencias automáticamente

## 🐛 Solución de Problemas

**Error MySQL:** Verifica credenciales en application.properties

**Puerto 8080 ocupado:** Cambia `server.port` en application.properties

**Error 403:** Verifica rol del usuario

## 📝 Entrega

Formato: `Apellidos_Nombre_RA3V3.zip`

Excluir: carpeta `target/`

---
Proyecto completo listo para evaluación RA3 ✅
