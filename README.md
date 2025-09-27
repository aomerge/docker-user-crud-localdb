# Docker User CRUD - Spring Boot Application

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.14-blue.svg)
![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-green.svg)

Aplicación CRUD de usuarios desarrollada con **Spring Boot 3.3.0**, **PostgreSQL** y **Docker**. Incluye configuración completa para despliegue en **Kubernetes** con **Helm Charts**.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Prerrequisitos](#prerrequisitos)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [API Endpoints](#api-endpoints)
- [Configuración](#configuración)
- [Docker](#docker)
- [Kubernetes](#kubernetes)
- [Documentación API](#documentación-api)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Contribuir](#contribuir)

## ✨ Características

- 🚀 **API RESTful** completa para gestión de usuarios
- 🔐 **Spring Security** para autenticación y autorización
- 🐘 **PostgreSQL** como base de datos
- 🐳 **Docker & Docker Compose** para containerización
- ☸️ **Kubernetes** manifests y **Helm Charts**
- 📊 **Flyway** para migraciones de base de datos
- 📝 **Swagger/OpenAPI** para documentación automática
- 🔍 **Logging estructurado** con Logback
- 📈 **Trazabilidad** con trace IDs
- ⚡ **Lombok** para reducir boilerplate code

## 🛠 Tecnologías

- **Java 17+**
- **Spring Boot 3.3.0**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL 15.14**
- **Flyway** para migraciones
- **Docker & Docker Compose**
- **Kubernetes**
- **Helm**
- **Maven**
- **Lombok**
- **Swagger/OpenAPI 3**

## 📋 Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- Docker y Docker Compose
- Git

## 🚀 Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd docker-user-crud-localdb-incomplete
```

### 2. Ejecutar con Docker Compose (Recomendado)

```bash
# Construir y ejecutar todos los servicios
docker-compose up --build

# Ejecutar en segundo plano
docker-compose up -d --build
```

La aplicación estará disponible en: `http://localhost:8082`

### 3. Ejecutar Localmente (Desarrollo)

```bash
# 1. Iniciar solo PostgreSQL con Docker
docker-compose up postgres -d

# 2. Configurar variables de entorno (opcional)
export SPRING_PROFILES_ACTIVE=local

# 3. Ejecutar la aplicación
mvn spring-boot:run
```

### 4. Detener los Servicios

```bash
docker-compose down

# Para eliminar también los volúmenes
docker-compose down -v
```

## 🔗 API Endpoints

### Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/users` | Obtener todos los usuarios |
| `GET` | `/api/users/{id}` | Obtener usuario por ID |
| `POST` | `/api/users` | Crear nuevo usuario |
| `PUT` | `/api/users/{id}` | Actualizar usuario |
| `DELETE` | `/api/users/{id}` | Eliminar usuario |

### Ejemplo de Request Body (POST/PUT)

```json
{
    "username": "john_doe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```

### Ejemplo de Response

```json
{
    "id": 1,
    "username": "john_doe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "createdAt": "2024-09-27T10:30:00",
    "updatedAt": "2024-09-27T10:30:00"
}
```

## ⚙️ Configuración

### Variables de Entorno

Crear un archivo `.env` en la raíz del proyecto:

```env
# Base de datos
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/userdb
SPRING_DATASOURCE_USERNAME=userdb
SPRING_DATASOURCE_PASSWORD=secret

# Puerto de la aplicación
SERVER_PORT=8082

# Perfil de Spring
SPRING_PROFILES_ACTIVE=docker
```

### Configuración de Base de Datos

- **Host**: `localhost` (desarrollo) / `postgres` (Docker)
- **Puerto**: `5433` (mapeado desde Docker)
- **Base de datos**: `userdb`
- **Usuario**: `userdb`
- **Contraseña**: `secret`

## 🐳 Docker

### Construcción Manual de la Imagen

```bash
# Construir la imagen
docker build -t docker-user-crud:latest .

# Ejecutar el contenedor
docker run -p 8082:8082 \
  --env-file example.env \
  docker-user-crud:latest
```

### Docker Compose - Servicios

- **app**: Aplicación Spring Boot (puerto 8082)
- **postgres**: Base de datos PostgreSQL (puerto 5433)

## ☸️ Kubernetes

### Despliegue con Manifests

```bash
# Aplicar manifests de Kubernetes
kubectl apply -f k8s/

# Verificar el despliegue
kubectl get pods
kubectl get services
```

### Despliegue con Helm

```bash
# Instalar con Helm
helm install user-crud-app ./k8s-helm

# Actualizar release
helm upgrade user-crud-app ./k8s-helm

# Desinstalar
helm uninstall user-crud-app
```

### Servicios de Kubernetes

- **PostgreSQL**: Base de datos con almacenamiento persistente
- **Spring App**: Aplicación con balanceador de carga
- **Secrets**: Configuración segura de credenciales

## 📚 Documentación API

La documentación interactiva de la API está disponible a través de Swagger UI:

- **Swagger UI**: `http://localhost:8082/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8082/v3/api-docs`

## 📁 Estructura del Proyecto

```
├── src/
│   └── main/
│       ├── java/com/example/dockerusercrud/
│       │   ├── DockerUserCrudApplication.java
│       │   ├── config/
│       │   │   └── SecurityConfig.java
│       │   ├── controller/
│       │   │   └── UserController.java
│       │   ├── entity/
│       │   │   └── User.java
│       │   ├── repository/
│       │   │   └── UserRepository.java
│       │   └── service/
│       │       ├── UserService.java
│       │       └── UserServiceImpl.java
│       └── resources/
│           ├── application.yml
│           ├── application-docker.properties
│           ├── logback-spring.xml
│           └── db/migration/
│               └── V1_init.sql
├── k8s/                          # Manifests de Kubernetes
├── k8s-helm/                     # Helm Chart
├── k8s-test/                     # Helm Chart para testing
├── docker-compose.yml
├── docker-compose-swarm.yml
├── Dockerfile
└── pom.xml
```

## 🧪 Testing

```bash
# Ejecutar tests unitarios
mvn test

# Ejecutar tests de integración
mvn integration-test

# Generar reporte de cobertura
mvn jacoco:report
```

## 📊 Monitoreo y Logs

La aplicación incluye:

- **Logging estructurado** con Logback
- **Trace IDs** para seguimiento de requests
- **Health checks** de Spring Boot Actuator
- **Métricas** disponibles en `/actuator/metrics`

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👤 Autor

- **Tu Nombre** - *Desarrollador Principal*

## 🔗 Enlaces Útiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Helm Documentation](https://helm.sh/docs/)

---

⭐ ¡No olvides darle una estrella al proyecto si te fue útil!