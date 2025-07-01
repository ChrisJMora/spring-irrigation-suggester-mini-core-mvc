
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

### Credenciales de Acceso

- **Usuario**: admin1
- **Contraseña**: password1

## Endpoints de la API

### Administración de Cultivos

| Método HTTP | Endpoint               | Descripción                                   |
|-------------|------------------------|-----------------------------------------------|
| GET         | `/api/crop/all`        | Obtener todos los cultivos registrados        |
| POST        | `/api/crop/create`     | Crear un nuevo cultivo                        |
| PUT         | `/api/crop/update/{id}`| Actualizar un cultivo existente               |

### Pronósticos Meteorológicos

| Método HTTP | Endpoint                | Descripción                                       |
|-------------|--------------------------|---------------------------------------------------|
| GET         | `/api/forecast/all`      | Obtener todos los pronósticos registrados         |
| POST        | `/api/forecast/random`   | Actualizar aleatoriamente el pronóstico del día   |

### Cronogramas de Riego

| Método HTTP | Endpoint                         | Descripción                                         |
|-------------|----------------------------------|-----------------------------------------------------|
| GET         | `/api/schedule/all`              | Obtener todos los cronogramas registrados          |
| GET         | `/api/schedule/suggested/all`    | Obtener cronogramas de riego sugeridos             |
| POST        | `/api/schedule/suggested/accept` | Aceptar un cronograma sugerido                     |
| POST        | `/api/schedule/cancel`           | Cancelar un cronograma existente                   |
| POST        | `/api/schedule/create`           | Crear un nuevo cronograma                          |
| POST        | `/api/schedule/update`           | Actualizar un cronograma existente                 |

## Notas

Este proyecto incluye un módulo de seguridad que restringe el acceso a ciertos endpoints según roles de usuario como **ADMINISTRATOR** y **SUPERVISOR**. Asegúrate de configurar correctamente los roles en la base de datos para un funcionamiento adecuado.
