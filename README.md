# 🎓 Talent ED - Plataforma de Gestión de Ofertas Educativas

*Talent ED* es una aplicación web inteligente diseñada para optimizar la **gestión de ofertas laborales en el sector educativo privado**.  
Incorpora **Inteligencia Artificial Generativa** para crear **formularios dinámicos** en procesos de selección, adaptados en tiempo real a las respuestas de los candidatos.  

- **Backend:** Java Spring Boot  
- **Frontend:** Angular 17 con PrimeNG  
- **Base de Datos:** MySQL  

---

## 📦 Requisitos Previos

Antes de levantar la aplicación en local necesitas tener instalado:

- ☕ **Java 17** o superior  
- 📦 **Maven 3.8+**  
- 🟢 **Node.js 18+**  
- 🅰️ **Angular CLI 17**  
- 🐬 **MySQL**  
- 🐙 **Git** para clonar el repositorio  

🔹 Opcional:  
- 🖥️ **DBeaver** para gestionar visualmente la base de datos  

---

## 🔗 1. Clonar el Repositorio

```bash
git clone https://github.com/mariangelesmt/talent-ed.git
cd talent_ed
```

## 🗄️ 2. Configurar la base de datos 
1. Crea una base de datos local llamada talentdb
2. Configura las variables de conexión en application-local.properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/talentdb
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## 📂 3. Backend (Spring Boot)
1. Navega a la carpeta del backend: 
```bash
cd backend
```
2. Levanta la aplicación: 
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

La aplicación correrá en http://localhost:8080 por defecto.
Hibernate creará automáticamente las tablas definidas en las entidades.

## 🎨 4. Frontend (Angular)
1. Instala dependencias: 
```bash
npm i
```
2. Levanta la aplicación: 
```bash
ng serve --open
```
El frontend correrá en http://localhost:4200.
Se conecta automáticamente al backend en http://localhost:8080.

## 🔐 5. Variables de entorno y perfiles
Local: usa application-local.properties para DB y valores de desarrollo.

## ✉️ Contacto / Soporte

Para dudas sobre configuración local, despliegue o acceso al repositorio, contactar con: María de los Ángeles Muñoz Torres (autora del proyecto).