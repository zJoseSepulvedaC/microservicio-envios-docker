# 📦 Microservicio de Envíos Internacionales
# Autor José Ignacio Sepúlveda C.
# Ingeniería Desarrollo de Software

Este microservicio, desarrollado con Spring Boot, gestiona operaciones CRUD relacionadas con envíos internacionales. Permite registrar, consultar, actualizar y eliminar envíos, incluyendo su estado y ubicación actual.

## 🛠 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Oracle Cloud (Base de Datos)
- Docker
- Maven

## 📂 Endpoints disponibles

| Método | Endpoint                  | Descripción                              |
|--------|---------------------------|------------------------------------------|
| GET    | `/envios`                 | Lista todos los envíos                   |
| GET    | `/envios/{id}`            | Obtiene un envío por su ID               |
| GET    | `/envios/{id}/ubicacion`  | Consulta la ubicación actual del envío   |
| POST   | `/envios`                 | Registra un nuevo envío                  |
| PUT    | `/envios/{id}`            | Actualiza estado o ubicación del envío   |
| DELETE | `/envios/{id}`            | Elimina un envío                         |

## 🔗 Conexión a la Base de Datos

Este microservicio se conecta a una base de datos Oracle en Oracle Cloud. Asegúrate de que las credenciales y el wallet estén correctamente configurados en el contenedor Docker.

## 🚀 Despliegue en Docker

1. Empaquetar la app:
   ```bash
   mvn clean package

2.Construir la imagen Docker:

docker build -t microservicio-envios .

3. Ejecutar contenedor:

docker run -p 8081:8080 microservicio-envios
