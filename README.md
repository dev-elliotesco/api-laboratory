# API laboratory
Esta aplicación es un sistema de administración para un laboratorio clínico hecho con Java 17 y Springboot, implementando pruebas unitarias, contenedor en Docker, análisis de código estático con SonarCloud y despliegue de la imagen en el registry de DockerHub mediante el uso de un pipeline CI en Github Actions.

## Requerimientos
* Java JDK 17. [Link](https://jdk.java.net/java-se-ri/17)
* My SQL Database. [Link](https://dev.mysql.com/downloads/mysql/)
* Docker Engine o Docker Desktop (Si se desea correr el contenedor).
[Link](https://www.docker.com/products/docker-desktop/)

## Ejecución 
### Configuración de base de datos: 
Se debe modificar la URL, el username y el password de la base de datos en el archivo de *application.properties* en la ruta *src/main/resources*:
```
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

### Ejecución con Gradle:
Ejecutar el siguiente comando en la ruta del proyecto para levantar la aplicación:
```
./gradlew run
```

 ### Ejecución con Docker:
Ejecutar el siguiente comando en la ruta del proyecto para el build de la imagen de docker:

```
$ docker build -t api-laboratory .
```

Luego ejecutar el siguiente comando para correr el contenedor, este levantara la aplicación Springboot en el puerto 8080:

```
$ docker run -p 8080:8080 api-laboratory
```



