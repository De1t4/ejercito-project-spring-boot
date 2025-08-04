# Sistema de Gestion Militar

> Aplicación web que simula un entorno de gestión militar, permitiendo la administración basada en roles, la asignación de servicios.

## Tecnologías 

- Spring Boot – Framework para el desarrollo de aplicaciones backend en Java.
- Spring Security – Gestión de autenticación y autorización.
- Lombok – Reducción de código boilerplate en clases Java.
- MySQL Connector – Driver para conexión con bases de datos MySQL.
- JWT (JSON Web Tokens) – Autenticación basada en tokens.

## Como iniciar el proyecto

### Requisitos
- Java 17
- MySQL instalado y corriendo localmente
- IDE como IntelliJ o Eclipse (opcional, pero recomendado)


1. Clonar el repositorio

```bash
https://github.com/De1t4/ejercito-project-spring-boot.git
cd ejercito-project-spring-boot
```

2. Importar la base de datos
Asegurate de tener una instancia de MySQL activa. Importár el archivo SQL sist_gest_militar_test.sql ubicado en la raíz del proyecto.

```bash
sist_gest_militar_test.sql
```

3. Configurar el archivo application.properties

```bash
DB_PASSWORD=tu_contraseña;
DB_URL=jdbc:mysql://localhost:3306/sist_gest_militar;
DB_USERNAME=root;
JWT_SECRET=tuSalSecretaMuyLarga1234567890!@#$%
```
