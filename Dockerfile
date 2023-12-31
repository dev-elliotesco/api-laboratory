# Utiliza una imagen base con Java 17 y Gradle
FROM gradle:8.5-jdk17 AS build

# Establece el directorio de trabajo en /home/gradle/src
WORKDIR /home/gradle/src

# Copia los archivos necesarios para aprovechar el caché de las dependencias
COPY build.gradle settings.gradle gradlew ./

# Copia el directorio gradle
COPY gradle ./gradle

# Copia el directorio de código fuente
COPY src ./src

# Ejecuta la construcción del proyecto con Gradle, excluyendo la ejecución de pruebas
RUN ./gradlew build -x test

# Inicia una nueva etapa para crear una imagen más pequeña
FROM openjdk:17-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR desde la etapa de construcción al directorio /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

# Expone el puerto 8080
EXPOSE 8080

# Comando por defecto para ejecutar la aplicación
CMD ["java", "-jar", "/app/com.laboratory-0.0.1-SNAPSHOT.jar"]
