# Back Architecture

[![Roadmap-de-tecnolog-a.jpg](https://i.postimg.cc/m2xyBDFC/Roadmap-de-tecnolog-a.jpg)](https://postimg.cc/qgL3Gpwv)

**registrationUserService:** Cumple con la funcionalidad de registrar a los distintos usuarios dentro de nuestro IAM (Keycloak) para que así, se pueda tener un mayor manejo de autorización, autenticación, inicio y cierre de sesiones dentro de Digital Money House. Este servicio se comunica de manera sincrónica con userService para poder registrar al usuario dentro de la base de datos de igual forma, para que así, se pueda generar la CVU del usuario y pueda comenzar a hacer uso de la billetera virtual.

**Keycloak:** IAM (Identity and Access Management) el cual proporciona servicios de seguridad como autenticación, autorización y gestión de sesiones para Digital Money House. 

**Gateway:** Punto de entrada centralizado para todas las solicitudes que se realicen a los servicios de Digital Money House. Su función principal es gestionar, dirigir y controlar el tráfico entre los clientes y los servicios, cumpliendo con el patrón de diseño Edge Server.

**userService:** Este servicio el cual está conectado a PostgreSQL, genera las diferentes funcionalidades de Digital Money House a través de una API Rest.

**eurekaServer:** Cumple con el patrón de diseño Service Registry o Service Discovery. Funciona como un registro de servicios, permitiendo a los microservicios registrarse y descubrir otros servicios en el entorno de ejecución.

**configServer:** Cumple con el patrón de diseño Central Configuration. es un servicio que gestiona la configuración de las aplicaciones y servicios dentro de un sistema, proporcionando un repositorio centralizado para almacenar y distribuir la configuración de las aplicaciones.
