# Back Architecture

[![Roadmap-de-tecnolog-a.jpg](https://i.postimg.cc/Wp5wZFtF/Roadmap-de-tecnolog-a.jpg)](https://postimg.cc/G9sDwpFd)

**registrationUserService:** Cumple con la funcionalidad de registrar a los distintos usuarios dentro de nuestro IAM (Keycloak) para que así, se pueda tener un mayor manejo de autorización, autenticación, inicio y cierre de sesiones dentro de Digital Money House. Este servicio se comunica de manera sincrónica con userService para poder registrar al usuario dentro de la base de datos de igual forma, para que así, se pueda generar la CVU del usuario y pueda comenzar a hacer uso de la billetera virtual.

**Keycloak:** IAM (Identity and Access Management) el cual proporciona servicios de seguridad como autenticación, autorización y gestión de sesiones para Digital Money House. 

**Gateway:** Punto de entrada centralizado para todas las solicitudes que se realicen a los servicios de Digital Money House. Su función principal es gestionar, dirigir y controlar el tráfico entre los clientes y los servicios, cumpliendo con el patrón de diseño Edge Server.

**userService:** Este servicio el cual está conectado a PostgreSQL, genera las diferentes funcionalidades de Digital Money House a través de una API Rest.

**eurekaServer:** Cumple con el patrón de diseño Service Registry o Service Discovery. Funciona como un registro de servicios, permitiendo a los microservicios registrarse y descubrir otros servicios en el entorno de ejecución.

**configServer:** Cumple con el patrón de diseño Central Configuration. es un servicio que gestiona la configuración de las aplicaciones y servicios dentro de un sistema, proporcionando un repositorio centralizado para almacenar y distribuir la configuración de las aplicaciones.

# Sprint I

'Como usuario quiero registrarme a Digital Money House para acceder y usar los servicios que ofrece.'

Como se describió anteriormente, el servicio 'registrationUserService' cumple con la tarea de registrar a los usuarios dentro de nuestro IAM, para así, poder manejar de manera efectiva el inicio y cierre de sesión, como también aprovechar las diferentes funciones que Keycloak nos ofrece.

Para realizar el registro de un usuario, es necesario apuntar al endpoint de nuestro servicio, que en este caso lo haremos por medio del gateway 'http://localhost:9090/registration'. Para realizar este registro, no se requiere ninguna autenticación o autorización. En este caso, realizaré una prueba de registro por medio de Postman:

[![Reg.png](https://i.postimg.cc/c1RCXtQM/Reg.png)](https://postimg.cc/6TQBQ3by)

Realizamos el respectivo POST con los datos que son necesarios para realizar el registro (Primer nombre, apellido, email, contraseña, dni/identificación y teléfono). Una vez registrado el usuario, es retornado un status 200 junto con el usuario creado sin su ID ni contraseña. Los campos 'cvu' y 'alias' son asiganos de manera aleatoria, como se describía y mencionaba dentro de los requerimientos.

Acá podemos ver a los usuarios registrados dentro de nuestro Keycloak, como también dentro de nuestra base de datos, ya que como se describió dentro de la arquitectura, el servicio 'registrationUserService' se comunca de manera sincrónica con el servicio 'userService', el cual se encarga también de una vez registrado el usuario, crear la cuenta con el 'cvu' asignado para que el usuario pueda hacer uso de su billetera virtual.

[![Key-User-Reg.png](https://i.postimg.cc/154x4L5n/Key-User-Reg.png)](https://postimg.cc/HVGvhvMd)

[![Postgres-User-Reg.png](https://i.postimg.cc/nzwkWw8g/Postgres-User-Reg.png)](https://postimg.cc/34mm41xF)

[![Postgres-User-Reg-Acc.png](https://i.postimg.cc/YCbbgSnt/Postgres-User-Reg-Acc.png)](https://postimg.cc/fVScQMQP)

De esta manera, se puede controlar y cumplir con los requerimientos dados dentro del primer Sprint. Recalco que dentro de la base de datos no se decidió guardar la contraseña de los usuarios por decisiones de seguridad, ya que estas credenciales serán controladas dentro del IAM (Keycloak). Además, decidí utilizar como 'username' el correo del usuario dentro de keycloak, ya que para poder iniciar sesión dentro de nuestra billetera se realiza con email y contraseña, así logrando pedir un token de esta manera:

[![LogUser.png](https://i.postimg.cc/Y0rv1tyw/LogUser.png)](https://postimg.cc/YGP27BHX)

[![SessUser.png](https://i.postimg.cc/x1z1NkTz/SessUser.png)](https://postimg.cc/DS7TtwGv)

'Como usuario quiero acceder a Digital Money House para poder realizar transferencias de fondos.'
'Como usuario necesito poder cerrar sesión en la billetera Digital Money House.'

Si las credenciales son correctas, Keycloak iniciará una sesión y nos ofrecerá un Token para poder hacer uso de los servicios en nuestra billetera. Así mismo, si queremos realizar logout lo podemos realizar apuntando a la URI 'http://localhost:8080/realms/digital-money-house/protocol/openid-connect/logout'.

# Sprint II

'Como usuario necesito ver, en el inicio, la cantidad de dinero disponible y los últimos 5 movimientos realizados con la billetera Digital Money House.'

Para demostrar el funcionamiento de estos servicios, hice uso de Swagger dentro de nuestro 'userService' (http://localhost:8082/swagger-ui/index.html#/). Para poder hacer uso de estas funcionalidades, es necesario estar autorizado, por lo que necesitamos pedir un Token con nuestras credenciales (email y contraseña) y colocarlo dentro de nuestro Swagger, de esta manera:

[![Swagger-Auth.png](https://i.postimg.cc/MG4Tny1V/Swagger-Auth.png)](https://postimg.cc/VSq1T0Gk)

[![Swagger-Bearer-Token.png](https://i.postimg.cc/D0QypHHP/Swagger-Bearer-Token.png)](https://postimg.cc/YL9Hhnpv)

Una vez autorizados para realizar solicitudes a nuestros servicios, podemos probamos la API con los endpoints requeridos para este Sprint.

[![SR1.png](https://i.postimg.cc/hGVwmnds/SR1.png)](https://postimg.cc/kVXfrkwR)

[![SR1R.png](https://i.postimg.cc/Qtby6qSW/SR1R.png)](https://postimg.cc/vg4hTfJQ)

Requerimiento el cual devuelve la información de la cuenta con el dinero disponible por la ID del usuario.

[![SR2.png](https://i.postimg.cc/ZKp4cGp9/SR2.png)](https://postimg.cc/2VksjXHm)

[![SR2R.png](https://i.postimg.cc/bwR8X16p/SR2R.png)](https://postimg.cc/sM1b7GdN)

Requerimiento el cual devuelve las transacciones realizadas por ID del usuario.

'Como usuario quiero ver mi perfil para consultar los datos de mi Cuenta Virtual Uniforme (CVU) y alias provistos por Digital Money House.'

[![SR3.png](https://i.postimg.cc/T1qGRmRV/SR3.png)](https://postimg.cc/tnT8ksnJ)

[![SR3R.png](https://i.postimg.cc/44zXbCzY/SR3R.png)](https://postimg.cc/c6Lq0bkW)

Requerimiento el cual devuelve toda la información del usuario por su ID.

'Como usuario me gustaría ver una lista de las tarjetas de crédito y débito que tengo disponibles para utilizar.'

[![CRUD.png](https://i.postimg.cc/x1qrZBby/CRUD.png)](https://postimg.cc/nX8wjR2C)

Requerimiento CRUD de cartas.

[![CardsReq.png](https://i.postimg.cc/Wz2pqSYW/CardsReq.png)](https://postimg.cc/S2vbwLhC)

Requerimiento el cual devuelve la lista de cartas por el ID del usuario y ver una carta en específico por su ID.
