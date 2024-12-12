# Argumentación Técnica del Proyecto, ChicCloset.

## Introducción Técnica.

El proyecto consiste en una tienda de ropa completamente online, diseñada para aplicar y perfeccionar habilidades adquiridas durante el curso, así como para explorar nuevas oportunidades en el desarrollo de software. Elegimos el mercado de la moda debido a su amplia audiencia y constante demanda, en especial con el auge de las compras online. Nuestro enfoque fue construir una solución funcional, escalable y técnicamente sólida, que sirviera tanto como experiencia de aprendizaje como base para proyectos futuros.

## Diseño Funcional.

El desarrollo de la aplicación comenzó con la identificación y priorización de funcionalidades clave. Estas se clasificaron en funciones esenciales y secundarias para garantizar que el proyecto pudiera cumplir con sus objetivos en el tiempo estipulado. Las funcionalidades básicas incluyeron:

## Usuarios finales:

- Visualización detallada de productos.
- Filtros avanzados por talla y color.
- Carrito de compras persistente por sesión.
- Proceso de registro y cambio de idioma.

## Administradores:

- Gestión de productos (creación, edición, eliminación).
- Gestión de usuarios y pedidos.
- Restricción de accesos a funciones críticas mediante roles.
- Se establecieron flujos de trabajo bien definidos para guiar el desarrollo y asegurar una implementación ordenada.

## Diseño Técnico y Tecnologías.

Para construir el proyecto, se emplearon herramientas modernas y prácticas que permitieron asegurar un desarrollo eficiente y organizado:

## Front-End:

- Tecnologías: HTML5, CSS, JavaScript, y Bootstrap.
- Integración con Back-End: Uso del motor de plantillas Thymeleaf, que permite la integración eficiente de datos en vistas dinámicas.

## Back-End:

- Lenguaje: Java, con soporte de Spring Boot para automatización de configuraciones y servicios robustos.
- Persistencia: MySQL gestionado con JPA (Java Persistence API) para realizar operaciones CRUD y mapear objetos Java a tablas de base de datos.
- Herramientas adicionales: MySQL Workbench para diseño de esquemas relacionales.

## Gestión del Código:

- Plataforma: GitHub/GitDesktop/Gitbash.
- Resolución de Conflictos: Visual Studio.
- Metodologías: Uso de herramientas como Jira y Miro para aplicar metodologías ágiles (SCRUM).

## Despliegue y Entorno:

- Docker: Creación de un entorno SQL para mantener consistencia entre diferentes configuraciones.
- Seguridad: Configuración de roles y permisos a través de Spring Security.
- Funcionalidades Técnicas Destacadas
- Filtros por Talla y Color: Los filtros dinámicos se implementaron con métodos de servicio que obtienen tallas y colores directamente de la base de datos. Estos filtros, combinados con paginación mediante PageRequest, aseguran una experiencia de usuario optimizada y escalable.

# Carrito de Compras:

Persistente a nivel de sesión, implementado mediante sessionToken.
Gestión avanzada de productos en el carrito: actualizaciones automáticas de cantidades o adiciones de nuevos productos según el estado actual del carrito.

## Seguridad:

- Autenticación obligatoria para procesos críticos como pagos.
- Restricción de acceso basada en roles, configurada a nivel de controlador.
- Proyecciones y Mejoras Futuras

## En versiones futuras, planeamos incluir:

Mercado circular: Una funcionalidad que permitirá a los usuarios revender productos previamente adquiridos, reutilizando los metadatos del producto para simplificar el proceso.
Optimización del código: Reorganización de archivos y consolidación de funciones similares para mejorar la eficiencia y mantenibilidad.
Nuevas funcionalidades: Incorporación de listas de favoritos y un historial de búsqueda para mejorar la experiencia del usuario.
Este enfoque técnico no solo permitió la culminación exitosa del proyecto, sino que también sentó las bases para futuras iteraciones y funcionalidades más avanzadas. La combinación de herramientas modernas, buenas prácticas de desarrollo y metodologías ágiles aseguró que nuestro equipo pudiera superar desafíos y entregar una solución práctica y bien estructurada.

Para proceder a la instalación y ejecución de la aplicación, debemos seguir los siguientes pasos:

 - Instalar Docker Desktop o Docker web.
 - Ejecutar el comando que hay en el archivo 'docker-run.txt' para crear el contenedor de la base de datos de el proyecto.

<img src="/img/desarrollo.png">

## Ejecución. 

- Arrancar el proyecto con el perfil "Desarrollo" activado.
- Entar a http://localhost:8080/ con un navegador.
