# Talent ED - Plataforma de gestión de ofertas educativas

Este proyecto es una aplicación web inteligente para la gestión de ofertas laborales en el sector educativo privado, con generación dinámica de formularios de selección mediante IA.

El backend está desarrollado en **Java Spring Boot** y el frontend en **Angular 17 con PrimeNG**.

---

## Requisitos previos

Antes de levantar la aplicación en local necesitas:

- **Java 17** 
- **Maven 3.8+**
- **Node.js 18+**
- **Angular CLI 17**
- **MySQL** 
- **Git** para clonar el repositorio

Opcional:

- **DBeaver** 

---

## 1. Clonar el repositorio
git clone https://github.com/mariangelesmt/talent-ed.git


## 2. Configurar la base de datos 
1. Crea una base de datos local llamada talentdb
2. Configura las variables de conexión en application-local.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/talentdb
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

Se ha añadido un dump para crear datos iniciales y sea posible visualizar elementos inicialmente.

## 3. Backend (Spring Boot)
1. Navega a la carpeta del backend: cd backend
2. Levanta la aplicación: ./mvnw spring-boot:run -Dspring-boot.run.profiles=local

La aplicación correrá en http://localhost:8080 por defecto.
Hibernate creará automáticamente las tablas definidas en las entidades.

## 4. Frontend (Angular)
1. Instala dependencias: npm i
2. Levanta la aplicación: ng serve --open

El frontend correrá en http://localhost:4200.
Se conecta automáticamente al backend en http://localhost:8080.