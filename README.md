# Chic Closet

Proyecto personal para Máster Programación Java EOI 2024.

# SOBRE EL PROYECTO

- La siguiente iniciativa ha sido creada por el Grupo 2, formado por 4 personas, como nuestro proyecto final para el bootcamp de Java. 
Durante estos meses, hemos aplicado todos los conocimientos adquiridos en la creación de una aplicación web de venta de ropa online, 
a la que hemos denominado Chic Closet. 
- Este proyecto integra una amplia variedad de tecnologías como Java, Programación Orientada a Objetos (POO), Maven, Hibernate, Spring Boot, JDBC (Java Database Connectivity), JPA, Thymeleaf, MySQL, HTML5, CSS y Bootstrap.


# OBJETIVO DEL PROYECTO 

- La idea del proyecto es crear una aplicación web para una tienda de ropa online que
permita a los usuarios explorar, buscar y comprar diversos productos de moda,
incluyendo funciones como: navegación por categorías, búsqueda de productos,
gestión de carrito de compras y realización de pagos.

- Como ya se ha mencionado, nuestro proyecto consiste en una tienda de ropa completamente online. 
La idea detrás de este proyecto fue crear una plataforma que nos permitiera no solo aplicar y perfeccionar las habilidades adquiridas durante el curso, 
sino también explorar nuevas oportunidades para el futuro. Elegimos el mercado de la ropa porque tiene un amplio público y una demanda constante, 
además de aprovechar el creciente interés en la compra de ropa online.

# PROCESO DE FLUJOS

- El siguiente paso en el desarrollo fue definir las distintas funcionalidades que la aplicación debería incluir, clasificándolas según su importancia. Se establecieron prioridades, desde las funcionalidades imprescindibles hasta aquellas que podrían implementarse si el tiempo lo permitía. Entre las funciones básicas se incluyen la visualización detallada de los productos, la capacidad de los usuarios para realizar compras, y la posibilidad de que los administradores puedan gestionar tanto los productos como los usuarios y sus pedidos.
Con las prioridades bien definidas, creamos flujos de trabajo ordenados que nos permitieran una visión clara durante el desarrollo de cada funcionalidad.

# DISEÑO TÉCNICO

- Para el desarrollo de la aplicación, hemos utilizado HTML5, CSS, JavaScript y Bootstrap en el front-end, mientras que para el back-end nos hemos centrado en Java y MySQL. La mayor parte del desarrollo se llevó a cabo en IntelliJ, utilizando Visual Studio principalmente para resolver conflictos que surgían al hacer merges en GitHub cuando integramos diferentes implementaciones.
GitHub fue una herramienta clave para mantener un desarrollo organizado y facilitar la comunicación entre los miembros del equipo, por eso mismo hicimos uso de la herramienta creando nuestro propio repositorio.
En cuanto al despliegue, utilizamos Docker para crear un entorno SQL, lo que nos permitió asegurar una configuración consistente de la base de datos. Esto facilitó la integración y el mantenimiento, reduciendo problemas de compatibilidad y configuración en diferentes entornos. Además, utilizamos MySQL Workbench para organizar las relaciones entre las entidades y obtener una visión general del esquema de la base de datos.

- Para la interacción con la base de datos, empleamos JPA (Java Persistence API) para mapear objetos Java a tablas de la base de datos, eliminando la necesidad de crear consultas SQL manuales. JPA también permite manejar las operaciones CRUD de manera eficiente mediante la creación de repositorios específicos.
  Spring Boot nos ha  ayudado a simplificar el proceso de desarrollo ya que automatiza muchas configuraciones. Además presenta funcionalidades listas para usar, como la seguridad.
  Por último, es importante mencionar el uso de Thymeleaf, un motor de plantillas para el desarrollo de páginas HTML. Thymeleaf facilita la integración de datos del modelo en las vistas HTML de manera sencilla y eficiente.

- Para la realización de los filtros se realizaron unos métodos en el servicio para obtener los colores y las tallas disponibles en la base de datos y que estos fueran las opciones que tienes para elegir, los datos de talla, color o ambos se usan para seleccionar los productos correctos. Para la paginacion se usó PageRequest, una implementación de la interfaz Pageable, que aplica los valores página y tamaño para regular la vista. El método obtenerProductosFiltradosPaginados se usa posteriormente en el controlador y los resultados se añaden al Model para que estén disponibles a la vista.

- En la implementación del carrito hay que destacar el uso de sessionToken, de forma que el carrito no está asociado a un usuario, sino a una sesión por lo que pueden añadirse cosas al carrito sin estar logueado. En el método del servicio addToexistingCarrito se tiene en cuenta la presencia de productos en el carrito mediante el id, de forma que si ya está presente simplemente se actualiza la cantidad, y sino se crea un producto nuevo. Con el controller se usa de nuevo para pasar los datos al model y que sea posible su visualización.

- Nuestra App se ha securizado, por ejemplo para hacer el pago necesitas estar logueado y el acceso está restringido a ciertas páginas para las que necesitas ser Administrador como para la administración de productos, para editarlos, eliminarlos, crearlos...

# UNA FUTURA VERSIÓN

- Con miras a futuras versiones, inicialmente consideramos la posibilidad de implementar un mercado circular dentro de la misma página. Esta sección permitiría a los usuarios vender productos que habían adquirido previamente en nuestra tienda y que ahora desearían revalorizar dándoles una segunda vida. Esta funcionalidad ofrecería una ventaja significativa para la reventa, ya que la información del producto se mantendría como metadatos, eliminando la necesidad de que los usuarios ingresen nuevamente los detalles del artículo.

- Por otro lado, durante el desarrollo del proyecto y la organización del código, nos dimos cuenta de que no siempre seguimos una estructura adecuada. Algunos archivos no están ubicados en los lugares correspondientes, y en algunos casos, podríamos haber consolidado o reutilizado archivos para optimizar recursos, ya que realizan funciones similares.

- Finalmente, por el tiempo y algunas dificultades con el código, solucionar errores que se iban produciendo en la aplicación y modificaciones para su correcto funcionamiento, decidimos en conjunto que lo mejor sería aprovechar el tiempo para terminar las funciones que ya habíamos implementado, para sacar el proyecto adelante y dejar en el camino algunas como un historial de búsqueda y una lista de favoritos para el propio usuario.

# CONCLUSIONES

- A lo largo de este curso, partiendo desde el nivel inicial, hemos logrado avanzar significativamente en el desarrollo de nuestro proyecto, tanto a nivel grupal como individual. Durante el proceso, hemos adquirido un profundo entendimiento de la importancia de los sistemas de control de versiones, como GitHub, y hemos aprendido a utilizar eficazmente herramientas de gestión como Jira y Confluence para mejorar la colaboración en equipo. Además, hemos apreciado la necesidad de mantener un desarrollo organizado y coherente.
- A pesar de enfrentar diversas dificultades que afectaron el funcionamiento del proyecto, hemos perseverado y logrado avanzar en nuestra idea grupal, aunque tuvimos que renunciar a algunas funcionalidades previstas.


# INSTALACIÓN 

Para proceder a la instalación y ejecución de la aplicación, debemos seguir los siguientes pasos:

 - Instalar Docker Desktop o Docker web.
 - Ejecutar el comando que hay en el archivo 'docker-run.txt' en la ventana de cmd para crear el contenedor de la base de datos de el proyecto.

<img src="src/main/resources/static/img/desarrollo.png">

# EJECUCIÓN 

- Arrancar el proyecto con el perfil "Desarrollo" activado.
- Entrar a http://localhost:8080/ con un navegador.