# 📖 Backend Plataforma de Relatos Web
### API REST desarrollada con Spring Boot para la gestión de relatos, usuarios, comentarios y categorías

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![JPA](https://img.shields.io/badge/Spring_Data_JPA-success?style=for-the-badge)
![REST API](https://img.shields.io/badge/REST-API-red?style=for-the-badge)

</div>

---

# 📚 Descripción

**Backend Plataforma de Relatos Web** es una API REST desarrollada con **Spring Boot** encargada de gestionar toda la lógica de negocio de una plataforma de lectura y publicación de relatos.

El sistema permite administrar usuarios, relatos, categorías, comentarios y favoritos, proporcionando una arquitectura escalable y segura para aplicaciones web y móviles.

---

# ✨ Características Principales

### 👤 Gestión de Usuarios

- Registro de usuarios.
- Inicio de sesión.
- Gestión de perfiles.
- Roles de usuario y administrador.
- Actualización de información personal.

### 📖 Gestión de Relatos

- Publicación de relatos.
- Edición de contenido.
- Eliminación de relatos.
- Consulta de relatos públicos.
- Búsqueda de historias.

### 🏷️ Categorías

- Creación de categorías.
- Asociación de relatos a categorías.
- Filtrado por temática.

### 💬 Comentarios

- Agregar comentarios.
- Consultar comentarios por relato.
- Eliminar comentarios.

### ❤️ Favoritos

- Guardar relatos favoritos.
- Consultar lista personalizada.
- Eliminar favoritos.

### 🔐 Seguridad

- Autenticación mediante JWT.
- Protección de rutas privadas.
- Control de acceso basado en roles.

---

# 🛠️ Tecnologías Utilizadas

| Tecnología | Descripción |
|------------|------------|
| Java 17 | Lenguaje principal |
| Spring Boot | Framework Backend |
| Spring Security | Seguridad y autenticación |
| JWT | Gestión de sesiones |
| Spring Data JPA | Persistencia de datos |
| Hibernate | ORM |
| MySQL | Base de datos |
| Maven | Gestión de dependencias |
| Lombok | Reducción de código repetitivo |

---

# 📂 Arquitectura del Proyecto

```bash
📦 BackendPlataformaRelatosPapel
├── 📂 controller
│   ├── UsuarioController.java
│   ├── RelatoController.java
│   ├── ComentarioController.java
│   └── CategoriaController.java
│
├── 📂 service
│   ├── UsuarioService.java
│   ├── RelatoService.java
│   ├── ComentarioService.java
│   └── CategoriaService.java
│
├── 📂 repository
│   ├── UsuarioRepository.java
│   ├── RelatoRepository.java
│   ├── ComentarioRepository.java
│   └── CategoriaRepository.java
│
├── 📂 model
│   ├── Usuario.java
│   ├── Relato.java
│   ├── Comentario.java
│   └── Categoria.java
│
├── 📂 security
│   ├── JwtFilter.java
│   ├── JwtService.java
│   └── SecurityConfig.java
│
└── RelatosApplication.java
```

---

# 🗄️ Base de Datos

### Tabla Usuarios

```sql
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(255),
    rol VARCHAR(50)
);
```

### Tabla Relatos

```sql
CREATE TABLE relatos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    contenido LONGTEXT,
    fecha_publicacion DATETIME,
    usuario_id BIGINT
);
```

### Tabla Categorías

```sql
CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);
```

### Tabla Comentarios

```sql
CREATE TABLE comentarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comentario TEXT,
    fecha DATETIME,
    usuario_id BIGINT,
    relato_id BIGINT
);
```

---

# 🔗 Endpoints Principales

## Usuarios

| Método | Endpoint |
|----------|----------|
| POST | /api/auth/register |
| POST | /api/auth/login |
| GET | /api/usuarios |
| GET | /api/usuarios/{id} |
| PUT | /api/usuarios/{id} |
| DELETE | /api/usuarios/{id} |

---

## Relatos

| Método | Endpoint |
|----------|----------|
| GET | /api/relatos |
| GET | /api/relatos/{id} |
| POST | /api/relatos |
| PUT | /api/relatos/{id} |
| DELETE | /api/relatos/{id} |

---

## Comentarios

| Método | Endpoint |
|----------|----------|
| GET | /api/comentarios |
| POST | /api/comentarios |
| DELETE | /api/comentarios/{id} |

---

## Categorías

| Método | Endpoint |
|----------|----------|
| GET | /api/categorias |
| POST | /api/categorias |
| PUT | /api/categorias/{id} |
| DELETE | /api/categorias/{id} |

---

# 🚀 Instalación

### Clonar repositorio

```bash
git clone https://github.com/isairey/BackendPlataformaRelatosPapel.git
```

### Entrar al proyecto

```bash
cd BackendPlataformaRelatosPapel
```

### Configurar Base de Datos

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/relatos
spring.datasource.username=root
spring.datasource.password=123456
```

### Ejecutar aplicación

```bash
mvn spring-boot:run
```

Servidor:

```bash
http://localhost:8080
```

---

# 🔐 Autenticación JWT

### Login

```http
POST /api/auth/login
```

```json
{
  "email": "usuario@gmail.com",
  "password": "123456"
}
```

### Respuesta

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Header para rutas protegidas

```http
Authorization: Bearer TOKEN
```

---

# 📊 Funcionalidades

✅ Registro de usuarios.

✅ Inicio de sesión.

✅ Gestión de relatos.

✅ Comentarios.

✅ Categorías.

✅ Favoritos.

✅ Seguridad JWT.

✅ API REST.

✅ Integración con aplicaciones web y móviles.

---

# 🎯 Objetivos del Proyecto

- Crear una plataforma moderna para compartir relatos.
- Centralizar la gestión de contenido literario.
- Implementar una API segura y escalable.
- Facilitar la integración con frontend web y aplicaciones móviles.
- Aplicar buenas prácticas de desarrollo backend.

---

# 👨‍💻 Desarrollador

<div align="center">

## ISAI REYES 

### Desarrollador Full Stack | Spring Boot | Java | Bases de Datos

💻 Tecnologías principales:

- Java
- Spring Boot
- MySQL
- Spring Security
- JWT
- Hibernate
- REST API
- Docker

🚀 Apasionado por el desarrollo de software, aplicaciones web y sistemas empresariales.

</div>

---

# 📄 Licencia

Este proyecto está bajo la licencia MIT.

Puedes utilizarlo, modificarlo y distribuirlo libremente.

---

<div align="center">

### ⭐ Si te gusta este proyecto, considera darle una estrella en GitHub ⭐

**Desarrollado con ❤️ por ISAI REYES**

</div>
