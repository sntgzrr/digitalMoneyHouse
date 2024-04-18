# Back Architecture

[![Roadmap-de-tecnolog-a.jpg](https://i.postimg.cc/m2xyBDFC/Roadmap-de-tecnolog-a.jpg)](https://postimg.cc/qgL3Gpwv)

# registrationUserService
El servicio 'registrationUserService' cumple con la funcionalidad de registrar a los distintos usuarios dentro de nuestro IAM (Keycloak) para que éstos se pueda autenticar y acceder a los distintios servicios de Digital Money House. Este servicio se comunica de manera sincrónica con userService para poder registrar al usuario dentro de la base de datos de igual forma, para que así, se pueda generar la CVU del usuario y pueda comenzar a hacer uso de la billetera virtual.
