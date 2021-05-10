# Servicio Rest con panel de administración para crear datos
**Servicio rest implementado con Spring para el proyecto de generación de cuestionarios multilenguaje con recomendaciones para los institutos con formación en oferta dual.**

## Vista de administrador en el panel
- **Una vez logueados en la aplicación disponemos de unas tablas crud para trabajar con la estructura de datos de la aplicación.**
- **Disponemos también de un visualizador de la estructura de datos del JSON a enviar por el servicio rest en http:localhost/rest/all.**
- **Todo está implementado en una single-page app y todos los datos que muestra son actualizados en tiempo real.**
- **Documentación y testeo para el API-REST con Swagger-UI, se puede acceder desde el panel o sin haberse logueado.**
 
![imagen](https://user-images.githubusercontent.com/60214254/117727604-8cf00000-b1e8-11eb-88c1-63ef0641dccc.png)
![imagen](https://user-images.githubusercontent.com/60214254/117728015-228b8f80-b1e9-11eb-833a-123ebaff7a5b.png)

## Funcionamiento


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
- **La aplicación se ha preparado para poder ser exportada a un .jar aunque se podría exportar a .war**
- **Puerto por defecto 9000 por lo que la url sería  http:localhost:9000/ (Se puede cambiar en el application.properties)**
