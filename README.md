# Sistema de Biblioteca Multimedia

API REST completa para gestionar una biblioteca multimedia con libros, DVDs, autores, directores y categorías. Incluye autenticación JWT con roles de usuario.

---

##  Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 21 |
| Spring Boot | 4.0.5 |
| Spring Security | (incluido) |
| Spring Data JPA | (incluido) |
| PostgreSQL | 15+ |
| JWT (jjwt) | 0.12.6 |
| Maven | 3.9+ |
| Lombok | (incluido) |
| Swagger/OpenAPI | 2.7.0 |

---

##  Requisitos Previos

- **Java 21** o superior
- **Maven 3.9** o superior
- **PostgreSQL 15** o superior
- **IDE** (IntelliJ IDEA recomendado)

---

## ⚙ Configuración

### 1. Base de Datos

Crea una base de datos PostgreSQL:

```sql
CREATE DATABASE biblioteca_db;
```

### 2. Variables de Entorno (Opcional)

Puedes configurar la base de datos en `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Puerto (por defecto: 8080)
server.port=8080
```

---

##  Cómo Ejecutar

### Opción 1: Desde IntelliJ

1. Abre el proyecto en IntelliJ
2. Busca la clase principal `SistemaLibrosApplication.java`
3. Click derecho → Run

### Opción 2: Desde terminal

```bash
# Navega al directorio del proyecto
cd SistemaLibros

# Compila y ejecuta
mvn spring-boot:run
```

### Opción 3: Con Maven

```bash
mvn clean install
java -jar target/sistemalibros-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: **http://localhost:8080**

---

##  Documentación de la API

Una vez ejecutando, accede a:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **Documentación JSON**: http://localhost:8080/v3/api-docs

---

##  Autenticación

### Roles Disponibles

| Rol | Descripción |
|-----|-------------|
| `ADMIN` | Acceso total (CRUD completo, gestión de usuarios) |
| `USER` | Acceso limitado (lectura, creación propia) |

### Endpoints de Autenticación

#### Registro de Usuario

```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "nombre": "Juan",
  "email": "juan@ejemplo.com",
  "password": "miContrasena123"
}
```

#### Inicio de Sesión

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "juan@ejemplo.com",
  "password": "miContrasena123"
}
```

**Respuesta (Login):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "juan@ejemplo.com",
  "role": "USER"
}
```

### Cómo usar el Token JWT

Incluye el token en el header de Authorization para todas las peticiones protegidas:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

##  Endpoints de la API

### Autores

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/v1/autores` | Listar todos los autores | USER |
| `GET` | `/api/v1/autores/{id}` | Obtener autor por ID | USER |
| `POST` | `/api/v1/autores` | Crear nuevo autor | ADMIN |
| `PUT` | `/api/v1/autores/{id}` | Actualizar autor | ADMIN |
| `DELETE` | `/api/v1/autores/{id}` | Eliminar autor | ADMIN |

#### Crear Autor

```http
POST /api/v1/autores
Authorization: Bearer <token_admin>
Content-Type: application/json

{
  "nombre": "Gabriel",
  "apellido": "García Márquez",
  "nacionalidad": "Colombiano",
  "fechaNacimiento": "1927-03-06",
  "biografia": "Premio Nobel de Literatura..."
}
```

---

### Libros

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/v1/libros` | Listar todos los libros | USER |
| `GET` | `/api/v1/libros/paginated` | Listar con paginación | USER |
| `GET` | `/api/v1/libros/search` | Búsqueda avanzada | USER |
| `GET` | `/api/v1/libros/{id}` | Obtener libro por ID | USER |
| `POST` | `/api/v1/libros` | Crear nuevo libro | ADMIN |
| `PUT` | `/api/v1/libros/{id}` | Actualizar libro | ADMIN |
| `DELETE` | `/api/v1/libros/{id}` | Eliminar libro | ADMIN |

#### Búsqueda de Libros con Paginación

```http
GET /api/v1/libros/paginated?page=0&size=10
Authorization: Bearer <token>
```

#### Búsqueda Avanzada

```http
GET /api/v1/libros/search?titulo=cien&anio=1967
Authorization: Bearer <token>
```

#### Crear Libro

```http
POST /api/v1/libros
Authorization: Bearer <token_admin>
Content-Type: application/json

{
  "titulo": "Cien años de soledad",
  "anioPublicacion": 1967,
  "isbn": "978-0-06-088328-7",
  "autorId": 1,
  "categoriaIds": [1, 2],
  "categoriaTypes": ["NOVELA", "DRAMA"]
}
```

---

### Directores

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/v1/directores` | Listar todos los directores | USER |
| `GET` | `/api/v1/directores/{id}` | Obtener director por ID | USER |
| `POST` | `/api/v1/directores` | Crear nuevo director | ADMIN |
| `PUT` | `/api/v1/directores/{id}` | Actualizar director | ADMIN |
| `DELETE` | `/api/v1/directores/{id}` | Eliminar director | ADMIN |

---

### DVDs

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/v1/dvds` | Listar todos los DVDs | USER |
| `GET` | `/api/v1/dvds/{id}` | Obtener DVD por ID | USER |
| `POST` | `/api/v1/dvds` | Crear nuevo DVD | ADMIN |
| `PUT` | `/api/v1/dvds/{id}` | Actualizar DVD | ADMIN |
| `DELETE` | `/api/v1/dvds/{id}` | Eliminar DVD | ADMIN |

---

### Categorías

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/v1/categorias` | Listar todas las categorías | USER |
| `GET` | `/api/v1/categorias/{id}` | Obtener categoría por ID | USER |
| `POST` | `/api/v1/categorias` | Crear nueva categoría | ADMIN |
| `PUT` | `/api/v1/categorias/{id}` | Actualizar categoría | ADMIN |
| `DELETE` | `/api/v1/categorias/{id}` | Eliminar categoría | ADMIN |

---

## 🏗 Estructura del Proyecto

```
src/main/java/com/codewithjona/sistemalibros/
├── SistemaLibrosApplication.java      # Clase principal
├── config/
│   ├── SecurityConfig.java            # Configuración de seguridad
│   └── OpenApiConfig.java             # Configuración de Swagger
├── controller/
│   ├── AuthorController.java
│   ├── BookController.java
│   ├── DirectorController.java
│   ├── DvdController.java
│   ├── CategoryController.java
│   └── AuthController.java
├── service/
│   └── Impl/
│       ├── AuthorServiceImpl.java
│       ├── BookServiceImpl.java
│       ├── DirectorServiceImpl.java
│       ├── DvdServiceImpl.java
│       ├── CategoryServiceImpl.java
│       └── AuthServiceImpl.java
├── repository/
│   ├── AuthorRepository.java
│   ├── BookRepository.java
│   ├── DirectorRepository.java
│   ├── DvdRepository.java
│   ├── CategoryRepository.java
│   └── UserRepository.java
├── entity/
│   ├── Person.java                    # Clase base (MappedSuperclass)
│   ├── Author.java                    # extiende Person
│   ├── Director.java                  # extiende Person
│   ├── Book.java                      # relación con Author y Category
│   ├── Dvd.java                       # relación con Director y Category
│   ├── Category.java                  # relación ManyToMany con Book y Dvd
│   └── User.java                      # para autenticación
├── dto/
│   ├── AuthorDTO.java
│   ├── BookDTO.java
│   └── ...
├── enums/
│   ├── Genres.java
│   ├── Roles.java
│   ├── LiteraryGenre.java
│   └── CategoryType.java
├── user/
│   ├── AuthController.java
│   ├── JwtService.java
│   ├── JwtAuthenticationFilter.java
│   └── ...
└── exception/
    ├── GlobalExceptionHandler.java
    ├── ErrorResponse.java
    └── RecursoNoEncontradoException.java
```

---

##  Testing

Ejecutar los tests:

```bash
mvn test
```

---

## 📝 Notas Adicionales

- Todos los endpoints de autenticación (`/api/v1/auth/**`) son públicos
- Swagger UI está disponible sin autenticación
- Los endpoints `/api/v1/admin/**` requieren rol `ADMIN`
- Todos los demás endpoints requieren autenticación con JWT

---

## 📧 Autor

**Jonathan Flores** - [notjona19@gmail.com](mailto:tu-email@ejemplo.com)

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT.
