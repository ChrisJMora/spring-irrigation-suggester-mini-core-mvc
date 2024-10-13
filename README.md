# CRUD API RESTful con Spring Boot y MSSQL

Este proyecto es una API RESTful creada con **Spring Boot** para manejar operaciones CRUD sobre clientes. La base de datos utilizada es **Microsoft SQL Server (MSSQL)**, y el frontend está desarrollado en **Vue.js**. El proyecto utiliza **Maven** como gestor de dependencias.

## Descripción

La API permite realizar las siguientes operaciones sobre clientes:
- Crear un nuevo cliente
- Obtener todos los clientes
- Autenticar un cliente mediante credenciales
- Buscar cliente por nombre de usuario o correo electrónico
- Actualizar un cliente existente
- Eliminar un cliente por nombre de usuario o correo electrónico

El frontend, desarrollado en **Vue.js**, interactúa con esta API para visualizar y gestionar la información de los clientes.

## Instalación

1. Clona este repositorio:
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd <NOMBRE_DEL_PROYECTO>
    ```

3. Configura la conexión a la base de datos MSSQL en el archivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=<NOMBRE_DB>
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

- **Spring Boot** (Web, JPA, DevTools)
- **MSSQL Driver** para la base de datos
- **Vue.js** para el frontend
- **Maven** como gestor de dependencias

## Endpoints de la API

| Método HTTP | Endpoint                  | Descripción                                                       |
|-------------|---------------------------|-------------------------------------------------------------------|
| POST        | `/api/clients/create`      | Crear un nuevo cliente                                            |
| GET         | `/api/clients/all`         | Obtener todos los clientes                                        |
| POST        | `/api/clients/auth`        | Autenticar cliente con credenciales                               |
| GET         | `/api/clients`             | Buscar cliente por nombre de usuario o correo electrónico         |
| PUT         | `/api/clients/update`      | Actualizar cliente por nombre de usuario o correo electrónico      |
| DELETE      | `/api/clients/delete`      | Eliminar cliente por nombre de usuario o correo electrónico        |
