# Backend para Sugerencia de Cronogramas de Riego

Este proyecto es una API RESTful creada con **Spring Boot** para gestionar cronogramas de riego de cultivos. Utiliza sensores para medir el porcentaje de humedad y pronósticos de lluvia para sugerir cronogramas de riego personalizados. La base de datos utilizada es **PostgreSQL**, y el frontend puede desarrollarse con cualquier framework moderno para consumir los endpoints expuestos.

## Descripción

La API permite realizar las siguientes operaciones:

### Administración de Cultivos
- Obtener todos los cultivos registrados.
- Crear nuevos cultivos con sensores y aspersores asociados.
- Actualizar la información de un cultivo existente.

### Pronósticos Meteorológicos
- Obtener todos los pronósticos almacenados.
- Actualizar aleatoriamente el pronóstico del día actual.

### Cronogramas de Riego
- Obtener todos los cronogramas de riego registrados.
- Obtener cronogramas de riego sugeridos.
- Aceptar un cronograma sugerido.
- Cancelar un cronograma existente.
- Crear o actualizar cronogramas manualmente.

## Instalación

1. Clona este repositorio:
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd <NOMBRE_DEL_PROYECTO>
    ```

3. Configura la conexión a la base de datos PostgreSQL en el archivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/<NOMBRE_DB>
    spring.datasource.username=<TU_USUARIO>
    spring.datasource.password=<TU_CONTRASEÑA>
    ```

4. Construye el proyecto con Maven:
    ```bash
    mvn clean install
    ```

5. Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

## Dependencias

- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Web**
- **Microsoft SQL Server JDBC Driver** (runtime)
- **Lombok** (opcional)
- **Spring Boot Starter Test** (test)
- **PostgreSQL Driver** (versión 42.6.0)
- **Spring Boot Starter Security**
- **MapStruct** (versión 1.5.5.Final)
- **MapStruct Processor** (provided)
- **Java JWT API, Impl y Jackson** (versión 0.11.5, runtime)
- **Spring Boot Starter Validation**

## Enlaces de la API

El backend está desplegado en Render y está disponible en el siguiente enlace:

- **Backend**: [https://irrigation-suggester-mini-core-service.onrender.com](https://irrigation-suggester-mini-core-service.onrender.com)

## Endpoints de la API

### Administración de Cultivos

| Método HTTP | Endpoint         | Descripción                                   |
|--------------|------------------|-----------------------------------------------|
| GET          | `/api/crop/all`  | Obtener todos los cultivos registrados        |
| POST         | `/api/crop/create` | Crear un nuevo cultivo                        |
| PUT          | `/api/crop/update/{id}` | Actualizar un cultivo existente              |

### Pronósticos Meteorológicos

| Método HTTP | Endpoint          | Descripción                                   |
|--------------|-------------------|-----------------------------------------------|
| GET          | `/api/forecast/all` | Obtener todos los pronósticos registrados     |
| POST         | `/api/forecast/random` | Actualizar aleatoriamente el pronóstico del día |

### Cronogramas de Riego

| Método HTTP | Endpoint                     | Descripción                                         |
|--------------|------------------------------|---------------------------------------------------|
| GET          | `/api/schedule/all`          | Obtener todos los cronogramas registrados         |
| GET          | `/api/schedule/suggested/all`| Obtener cronogramas de riego sugeridos           |
| POST         | `/api/schedule/suggested/accept` | Aceptar un cronograma sugerido                   |
| POST         | `/api/schedule/cancel`       | Cancelar un cronograma existente                 |
| POST         | `/api/schedule/create`       | Crear un nuevo cronograma                        |
| POST         | `/api/schedule/update`       | Actualizar un cronograma existente               |

## Notas

Este proyecto incluye un módulo de seguridad que restringe el acceso a ciertos endpoints según roles de usuario como **ADMINISTRATOR** y **SUPERVISOR**. Asegúrate de configurar correctamente los roles en la base de datos para un funcionamiento adecuado.

