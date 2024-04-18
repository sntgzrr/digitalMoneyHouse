# Back Architecture

[![Roadmap-de-tecnolog-a.jpg](https://i.postimg.cc/m2xyBDFC/Roadmap-de-tecnolog-a.jpg)](https://postimg.cc/qgL3Gpwv)

# registrationUserService
El servicio 'registrationUserService' cumple con la funcionalidad de registrar a los distintos usuarios dentro de nuestro IAM (Keycloak) para que así, se pueda tener un mayor manejo de autorización, autenticación, inicio y cierre de sesiones dentro de Digital Money House. Este servicio se comunica de manera sincrónica con userService para poder registrar al usuario dentro de la base de datos de igual forma, para que así, se pueda generar la CVU del usuario y pueda comenzar a hacer uso de la billetera virtual.

# userService
Este servicio el cual está conectado a PostgreSQL, genera las diferentes funcionalidades de Digital Money House a través de una API Rest.
