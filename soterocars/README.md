# Proyecto de Gestión de Alquiler de Vehículos con API REST

## 1. Presentación del Proyecto

### 1.1 Introducción
Este proyecto tiene como objetivo desarrollar un sistema de gestión para una empresa de alquiler de vehículos, que incluye una API REST que permite interactuar con el sistema de forma eficiente. El sistema permite a los clientes consultar la disponibilidad de vehículos en distintas sedes, realizar reservas, y gestionar sus datos. Los administradores tienen acceso para gestionar vehículos, sedes, clientes y alquileres.

El sistema está basado en un enfoque web utilizando **Spring Boot** para el backend y **Swagger** para la documentación de la API REST. A través de esta API, otros servicios y aplicaciones pueden integrarse para interactuar con el sistema.

### 1.2 Tipos de Usuario
El sistema está diseñado para dos tipos de usuarios:

- **Administrador:**  
  Tiene acceso completo a todas las funcionalidades del sistema, tales como la gestión de vehículos, clientes, sedes y alquileres. Puede visualizar, modificar y eliminar registros.

- **Cliente:**  
  Tiene acceso para consultar la disponibilidad de vehículos en distintas sedes, realizar reservas y gestionar su propia información.

### 1.3 Épicas
Las épicas del sistema son las funcionalidades clave que abordan las necesidades de los usuarios:

- **Gestión de Vehículos:** El administrador puede registrar, editar y eliminar vehículos. Los clientes pueden consultar la disponibilidad de vehículos y realizar reservas.
- **Gestión de Sedes:** El administrador gestiona las sedes donde los vehículos serán recogidos o devueltos.
- **Gestión de Clientes:** El administrador gestiona los perfiles de los clientes. Los clientes pueden actualizar su propia información.
- **Gestión de Alquileres:** Los clientes pueden realizar alquileres en un rango de fechas determinado. El administrador gestiona el historial de alquileres y controla la disponibilidad de los vehículos.

### 1.4 Tecnologías Utilizadas
- **Spring Boot:** Framework utilizado para el backend.
- **Spring Data JPA:** Para la persistencia de datos en la base de datos.
- **Swagger:** Para la documentación y pruebas de la API REST.
- **MariaDB:** Base de datos relacional utilizada para almacenar la información del sistema de manera persistente.
- **HeidiSQL:** Herramienta de administración utilizada para gestionar y visualizar la base de datos MariaDB.
- **Lombok:** Para reducir el código repetitivo.

### 1.5 Planificación Temporal Inicial
El cronograma inicial del proyecto se dividió en tres fases principales:

- **Fase de Diseño (1 semana):** Planificación, diseño del sistema y modelado de datos.
- **Fase de Implementación (2 semanas):** Desarrollo de la API REST, integración de base de datos y funcionalidades principales.
- **Fase de Pruebas (1 semana):** Realización de pruebas unitarias e integración para asegurar el correcto funcionamiento del sistema.

## 2. Diseño del Sistema

### 2.1 Arquitectura del Sistema
La arquitectura sigue el patrón **Modelo-Vista-Controlador (MVC)**, con una capa de servicios que maneja la lógica de negocio y una API REST que expone los controladores para interactuar con los datos.

**Componentes principales:**

- **Modelo (Entities):** Contiene las clases que representan los objetos del negocio.
  - **Vehículo:** Representa un vehículo disponible para el alquiler.
  - **Sede:** Representa una sede para la recogida y devolución de vehículos.
  - **Cliente:** Representa a los clientes del sistema.
  - **Alquiler:** Representa un alquiler realizado por un cliente.

- **Controlador (Controllers):** Gestionan las solicitudes HTTP y coordinan la lógica de negocio con los servicios.
  - **VehiculoController, SedeController, ClienteController, AlquilerController.**

- **Servicios (Services):** Contienen la lógica de negocio, y son responsables de interactuar con los repositorios.
  - **VehiculoService, SedeService, ClienteService, AlquilerService.**

- **Repositorios (Repositories):** Manejan la persistencia de datos en la base de datos.
  - **VehiculoRepository, SedeRepository, ClienteRepository, AlquilerRepository.**

- **Vista (API REST):** A través de los controladores, se exponen los endpoints para interactuar con el sistema.

### 2.2 Implementación de la API REST
La API REST expone los endpoints necesarios para interactuar con las entidades del sistema. Los controladores están diseñados para manejar las solicitudes HTTP y devolver los datos correspondientes en formato JSON. La integración de **Swagger** permite documentar la API de manera accesible.
Para facilitar la interacción con el backend del sistema, puedes acceder a la documentación interactiva de la API RESTful utilizando **Swagger**.  

La documentación está disponible en la siguiente URL:  

🔗 **[Acceso a Swagger UI](http://localhost:8080/swagger-ui/index.html)**  

A través de esta interfaz, podrás realizar operaciones de la API de forma visual e interactiva, permitiendo:  
- Consultar los vehículos disponibles.  
- Realizar reservas.  
- Gestionar los datos de clientes y vehículos.  

Swagger facilita la prueba y exploración de los endpoints sin necesidad de utilizar herramientas externas, permitiendo una integración más eficiente.

**Controladores REST Implementados:**

- **VehiculoController:**  
  - `GET /api/vehiculos`: Listar todos los vehículos disponibles.
  - `POST /api/vehiculos`: Crear un nuevo vehículo.
  - `GET /api/vehiculos/{id}`: Obtener detalles de un vehículo.
  - `PUT /api/vehiculos/{id}`: Actualizar un vehículo.
  - `DELETE /api/vehiculos/{id}`: Eliminar un vehículo.

- **SedeController:**  
  - `GET /api/sedes`: Listar todas las sedes.
  - `POST /api/sedes`: Crear una nueva sede.
  - `GET /api/sedes/{id}`: Obtener detalles de una sede.
  - `PUT /api/sedes/{id}`: Actualizar una sede.
  - `DELETE /api/sedes/{id}`: Eliminar una sede.

- **ClienteController:**  
  - `GET /api/clientes`: Listar todos los clientes.
  - `POST /api/clientes`: Crear un nuevo cliente.
  - `GET /api/clientes/{id}`: Obtener detalles de un cliente.
  - `PUT /api/clientes/{id}`: Actualizar los datos de un cliente.
  - `DELETE /api/clientes/{id}`: Eliminar un cliente.

- **AlquilerController:**  
  - `GET /api/alquileres`: Listar todos los alquileres.
  - `POST /api/alquileres`: Crear un nuevo alquiler.
  - `GET /api/alquileres/{id}`: Obtener detalles de un alquiler.
  - `PUT /api/alquileres/{id}`: Actualizar un alquiler.
  - `DELETE /api/alquileres/{id}`: Eliminar un alquiler.


---

Este proyecto proporciona una solución completa para la gestión de alquileres de vehículos, permitiendo la administración eficiente de clientes, vehículos, sedes y alquileres. La API REST está documentada con Swagger, facilitando su uso e integración. Se espera que sirva como referencia tanto para el desarrollo como para futuras mejoras del sistema.

