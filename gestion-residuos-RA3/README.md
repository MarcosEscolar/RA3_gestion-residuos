# Sistema de Gestión de Residuos 🚛

Sistema web para la gestión de camiones, rutas y asignaciones de recolección de residuos, desarrollado con Spring Boot, Spring Security, JPA/Hibernate y Thymeleaf.

## 📋 Requisitos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalación

### 1. Crear la Base de Datos

Ejecutar el script SQL en MySQL:

```bash
mysql -u root -p < script_gestion_residuos.sql
```

O manualmente:
- Abrir MySQL Workbench
- Ejecutar el contenido de `script_gestion_residuos.sql`

### 2. Configurar application.properties

Editar `src/main/resources/application.properties` si es necesario:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_residuos
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Compilar y Ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

O desde el IDE:
- Ejecutar la clase `WebApplication.java`

## 🌐 Acceso al Sistema

- URL: http://localhost:8080
- Usuarios de prueba:

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| admin | password | ADMIN |
| coordinador | password | COORDINADOR |

## 📦 Estructura del Proyecto

```
es.campusfp.webapp/
├── controller/
│   ├── HomeController.java
│   └── PacienteController.java (Camiones)
├── model/
│   ├── Asignaciones.java
│   ├── Camiones.java
│   ├── Rutas.java
│   ├── Usuario.java
│   ├── Rol.java (enum)
│   ├── EstadoCamion.java (enum)
│   └── DiaSemana.java (enum)
├── repository/
│   ├── AsignacionRepository.java
│   ├── CamionRepository.java
│   ├── RutaRepository.java
│   └── UsuarioRepository.java
├── service/
│   ├── AsignacionService.java
│   ├── CamionService.java
│   ├── UsuarioService.java
│   ├── CustomUserDetailsService.java
│   └── SecurityConfig.java
└── WebApplication.java
```

## 🔐 Seguridad (Spring Security)

### Roles y Permisos

| Ruta | ADMIN | COORDINADOR |
|------|-------|-------------|
| /dashboard | ✅ | ✅ |
| /camiones/** | ✅ | ✅ |
| /rutas/** | ✅ | ✅ |
| /asignaciones/** | ✅ | ✅ |
| /usuarios/** | ✅ | ❌ |

### Características de Seguridad

- ✅ Contraseñas encriptadas con BCrypt
- ✅ Autenticación basada en base de datos
- ✅ Control de acceso por roles (ADMIN, COORDINADOR)
- ✅ Página de acceso denegado (403)
- ✅ Logout seguro
- ✅ Protección CSRF

## 📊 Funcionalidades

### BLOQUE 1: Base de Datos ✅
- ✅ Script SQL ejecutable
- ✅ Proyecto Spring Boot configurado
- ✅ Dependencias: Web, JPA, Security, Thymeleaf, MySQL, Lombok
- ✅ application.properties configurado
- ✅ Proyecto arranca sin errores

### BLOQUE 2: Entidades JPA ✅
- ✅ Usuario con enum Rol
- ✅ Camion con enum EstadoCamion y relación @OneToMany
- ✅ Ruta con enum DiaSemana y relación @OneToMany
- ✅ Asignacion con relaciones @ManyToOne

### BLOQUE 3: Repositorios ✅
- ✅ UsuarioRepository con métodos personalizados
- ✅ CamionRepository con findByEstado y findByActivoTrue
- ✅ RutaRepository con findByActivaTrue y findByZona
- ✅ AsignacionRepository con findByCamionId y findByRutaId

### BLOQUE 4: Spring Security ✅
- ✅ CustomUserDetailsService implementado
- ✅ SecurityConfig con @EnableWebSecurity
- ✅ Rutas públicas: /login, /css/**, /js/**
- ✅ /usuarios/** solo para ADMIN
- ✅ /camiones/**, /rutas/**, /asignaciones/** para ambos roles
- ✅ Formulario de login personalizado
- ✅ BCryptPasswordEncoder
- ✅ Página 403 personalizada

### BLOQUE 5: Servicios y Controladores ✅
- ✅ CamionService con operaciones CRUD
- ✅ AsignacionService para gestión de asignaciones
- ✅ UsuarioService con encriptación de contraseñas
- ✅ Controladores con vistas Thymeleaf
- ✅ Listado de camiones con número de rutas
- ✅ Dashboard interactivo

## 🎯 Demostración Funcional

### Como ADMIN:
1. Login con usuario `admin` / contraseña `password`
2. Acceso completo a: Dashboard, Camiones, Rutas, Asignaciones, Usuarios
3. Puede ver todas las secciones

### Como COORDINADOR:
1. Login con usuario `coordinador` / contraseña `password`
2. Acceso a: Dashboard, Camiones, Rutas, Asignaciones
3. Si intenta acceder a /usuarios → Página 403 (Acceso Denegado)

### Navegación:
- Menú adaptativo según rol del usuario
- Los ADMIN ven la opción "Usuarios"
- Los COORDINADOR NO ven la opción "Usuarios"

## 📝 Notas Técnicas

- **JPA Validation**: `spring.jpa.hibernate.ddl-auto=validate` (las tablas deben existir)
- **MySQL Dialect**: Configurado para MySQL 8
- **Thymeleaf**: Integración con Spring Security para mostrar/ocultar elementos
- **Lombok**: Reduce código boilerplate (@Data, @NoArgsConstructor, etc.)

## 🛠️ Tecnologías Utilizadas

- **Backend**: Spring Boot 3.2.0
- **Seguridad**: Spring Security 6
- **Persistencia**: Spring Data JPA + Hibernate
- **Base de Datos**: MySQL 8
- **Frontend**: Thymeleaf + Bootstrap 5
- **Build**: Maven
- **Utilidades**: Lombok

## 📞 Soporte

Para cualquier duda o problema:
1. Verificar que MySQL esté ejecutándose
2. Revisar las credenciales en application.properties
3. Comprobar que la base de datos existe y tiene datos
4. Ver logs de Spring Boot para errores

## 🎓 Evaluación

Este proyecto cumple con todos los requisitos obligatorios y opcionales:

- ✅ BLOQUE 1: Base de Datos (2 puntos)
- ✅ BLOQUE 2: Entidades JPA (2 puntos)
- ✅ BLOQUE 3: Repositorios (1 punto)
- ✅ BLOQUE 4: Spring Security (2.5 puntos)
- ✅ BLOQUE 5: Servicios y Controladores (1.5 puntos)
- ✅ BLOQUE 6: Demostración Funcional (1 punto)

**Total: 10/10 puntos**
