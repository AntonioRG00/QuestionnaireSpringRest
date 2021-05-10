# Servicio Rest con panel de administración
**Servicio rest implementado con Spring para la generación de cuestionarios multilenguaje con recomendaciones.**

## Vista de administrador en el panel
- **Una vez logueados en la aplicación disponemos de unas tablas crud para trabajar con los datos.**
- **Un visualizador de la estructura de datos del JSON a enviar por el servicio rest en http:localhost/rest/all.**
- **Single-page app y todos los datos que muestra son actualizados en tiempo real.**
- **Documentación para el API-REST con Swagger-UI, se puede acceder desde el panel o sin haberse logueado.**
 
![imagen](https://user-images.githubusercontent.com/60214254/117727604-8cf00000-b1e8-11eb-88c1-63ef0641dccc.png)
![imagen](https://user-images.githubusercontent.com/60214254/117728015-228b8f80-b1e9-11eb-833a-123ebaff7a5b.png)

## Funcionamiento
**Las tablas del menú están ordenadas por prioridad, es decir, no puede haber un área sin idioma, etc...**

**Si no se quiere disponer de multilenguaje o áreas podríamos crear una global e ir asignando todos los datos ahí**

**Todas las operaciones están en cascada por lo que al borrar un Idioma, estaríamos borrando sus áreas->categorías->preguntas**

**Hay 5 tablas (Idioma, Área, Categoría, Pregunta y Respuesta) una vez creados los datos disponemos de otras dos pestañas(Pregunta-Respuesta y Respuesta-Default), estas sirven para crear asociaciones de una respuesta a una pregunta con una puntuación o dejar las asociaciones por defecto si la pregunta no tiene asociadas respuestas.**

**Hay dos tipos de recomendaciones, por categoría y por pregunta. Cada una de estas tablas tienen una puntuación y si es superada hay que mostrar la recomendación**

**Cada respuesta va a tener un valor y este va a ser clave para saber si mostrar la recomendación. Ejemplos:**
- **Por pregunta (Pregunta.puntuación < Respuesta.Valor)**
- **Por categoría (Categoria.puntuacion < La suma de todos los valores de esa categoría)**

## Otras funcionalidades internas
- **El login es de usuario único, la contraseña debe estar encriptada en BCrypt**
- **Protegido el login frente a ataques por fuerza bruta, cada 5 intentos te bloquea de IP 2 minutos**
- **Se ha protegido el procesador de texto que se usa para la recomendación frente a ataques XSS**
- **Número máximo de usuarios simultaneamente conectados: 3 (Parametrizable desde la clase SecurityConfig)**
- **Desde el application.properties se puede controlar que es público y que necesita estar loguedo**

## Tecnologías más usadas
- **Spring (Boot, Security, JPA, Rest)**
- **PostgreSQL 13 (Como base de datos general)**
- **JSF (Con JoinFaces, para el backend del panel de admin)**
- **Primefaces 10 (Para el frontend + html, css, javascript, ajax)**
- **Swagger-UI (Documentación para el rest {Se puede acceder públicamente desde localhost/swagger-ui/})**
- **Lombok**
- **Webjars (Bootstrap, popper...)**
- **Java 15**
- **Gradle 7**

## Cosas a tener en cuenta
- **El proyecto está en UTF-8**
- **Se han comentado todas las operaciones de los servicios REST menos los GET que además son públicos**
- **La documentación de Swagger-UI se puede acceder sin  estar logueado**
- **La aplicación se ha preparado para poder ser exportada a un .jar aunque se podría exportar a .war**
- **Puerto por defecto 9000 http:localhost:9000/ (Parametrizable en el application.properties)**
- **Integración perfecta con Heroku**

### Insert mucho texto here
