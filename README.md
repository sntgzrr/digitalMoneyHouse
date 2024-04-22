# Back Architecture

[![Roadmap-de-tecnolog-a.jpg](https://i.postimg.cc/Wp5wZFtF/Roadmap-de-tecnolog-a.jpg)](https://postimg.cc/G9sDwpFd)

**registrationUserService:** Cumple con la funcionalidad de registrar a los distintos usuarios dentro de nuestro IAM (Keycloak) para que así, se pueda tener un mayor manejo de autorización, autenticación, inicio y cierre de sesiones dentro de Digital Money House. Este servicio se comunica de manera sincrónica con userService para poder registrar al usuario dentro de la base de datos de igual forma, para que así, se pueda generar la CVU del usuario y pueda comenzar a hacer uso de la billetera virtual.

**Keycloak:** IAM (Identity and Access Management) el cual proporciona servicios de seguridad como autenticación, autorización y gestión de sesiones para Digital Money House. 

**Gateway:** Punto de entrada centralizado para todas las solicitudes que se realicen a los servicios de Digital Money House. Su función principal es gestionar, dirigir y controlar el tráfico entre los clientes y los servicios, cumpliendo con el patrón de diseño Edge Server.

**userService:** Este servicio el cual está conectado a PostgreSQL, genera las diferentes funcionalidades de Digital Money House a través de una API Rest.

**eurekaServer:** Cumple con el patrón de diseño Service Registry o Service Discovery. Funciona como un registro de servicios, permitiendo a los microservicios registrarse y descubrir otros servicios en el entorno de ejecución.

**configServer:** Cumple con el patrón de diseño Central Configuration. es un servicio que gestiona la configuración de las aplicaciones y servicios dentro de un sistema, proporcionando un repositorio centralizado para almacenar y distribuir la configuración de las aplicaciones.

# Sprint I.

Como se describió anteriormente, el servicio 'registrationUserService' cumple con la tarea de registrar a los usuarios dentro de nuestro IAM, para así, poder manejar de manera efectiva el inicio y cierre de sesión, como también aprovechar las diferentes funciones que Keycloak nos ofrece.

Para realizar el registro de un usuario, es necesario apuntar al endpoint de nuestro servicio, que en este caso es 'http://localhost:9090/registration'. Para realizar este registro, no se requiere ninguna autenticación o autorización. En este caso, realizaré una prueba de registro por medio de Postman:

[![Reg.png](https://i.postimg.cc/c1RCXtQM/Reg.png)](https://postimg.cc/6TQBQ3by)
(POST para realizar registro de usuario)

Realizamos el respectivo POST con los datos que son necesarios para realizar el registro (Primer nombre, apellido, email, contraseña, dni/identificación y teléfono). Una vez registrado el usuario, es retornado un status 200 junto con el usuario creado sin su ID ni contraseña. Los campos 'cvu' y 'alias' son asiganos de manera aleatoria, como se describía y mencionaba dentro de los requerimientos.

Acá podemos ver a los usuarios registrados dentro de nuestro Keycloak, como también dentro de nuestra base de datos, ya que como se describió dentro de la arquitectura, el servicio 'registrationUserService' se comunca de manera sincrónica con el servicio 'userService', el cual se encarga también de una vez registrado el usuario, crear la cuenta con el 'cvu' asignado para que el usuario pueda hacer uso de su billetera virtual.

[![Key-User-Reg.png](https://i.postimg.cc/154x4L5n/Key-User-Reg.png)](https://postimg.cc/HVGvhvMd)
(Usuario registrado en Keycloak)

[![Postgres-User-Reg.png](https://i.postimg.cc/nzwkWw8g/Postgres-User-Reg.png)](https://postimg.cc/34mm41xF)
(Usuario registrado en nuestra base de datos)

[![Postgres-User-Reg-Acc.png](https://i.postimg.cc/GpL6wNNm/Postgres-User-Reg-Acc.png)](https://postimg.cc/qNF12m4P)
(Cuenta con 'cvu' creada una vez registrado el usuario)
