# API laboratory

Esta aplicación es un sistema de administración para un laboratorio clínico, con implementación de contenedores con 
Docker, pruebas unitarias y análisis de código estático con SonarCloud para el despliegue de la aplicación en DockerHub 
mediante el uso de un pipeline de CI en GithubActions.

## Pre-Requisitos

* Docker Engine.
* Docker Compose.

O instalar Docker Desktop que ofrece ambas herramientas.

[Link a Docker Desktop](https://www.docker.com/products/docker-desktop/)



Ejecutar el siguiente comando en la ruta del proyecto, este levantara la aplicación Sprinboot y la base de datos Mysql.

```
$ docker compose up