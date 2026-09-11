------Instrucciones de instalación y ejecución del proyecto ------

1. Requisitos previos:
	-JDK 11 o superior.
	-Apache NetBeans IDE 12.0 o superior.
	-Apache Maven 3.6 (integrado en NetBeans).

2. Instalación y configuración:
	-Descargue el archivo.
	-Extraiga el contenido del ZIP en una carpeta de su elección.
	-Abrir NetBeans.
	-Desde el apartado "File", escoger la opción "Open Project".
	-Seleccionar la carpeta donde se encuentra el proyecto.
	-Una vez abierto, haga clic derecho en el panel del proyecto 
	 sobre y seleccione la opción "Clean and Build".

3. Instrucciones de ejecución:
	-Abra el proyecto.
	-Ubica la clase src/main/java/com/mycompany/proyecto/Main.java.
	-Para la ejecutar el proyecto presione "F6" o hacer click derecho
	 sobre la clase "Main.java" y seleccione "Run File"

4. Interacción con el programa:

-Al ejecutar el programa se le permitirá elegir correrlo por consola (1) o ventana (2).

-Para navegar en el Menú por consola solo deberá escribir el número de la opción mostrada en pantalla que desea seleccionar y apretar "Enter". En algunos casos se le solicitará ingresar la información.

-Para navegar en el Menú por ventana verá en la parte superior los apartados "Proyectos”, "Departamentos" y la función especial "Recomendador".

-Apartado Proyectos: Podrá ver desplegada en la ventana una tabla de los proyectos con sus datos. Además, en la parte inferior encontrará las opciones "Agregar", "Modificar", "Eliminar", "Buscar" y "Refrescar".
	
	-Agregar: Permite agregar un nuevo proyecto. Para ello deberá hacer clic en el botón “Agregar”, a continuación deberá escribir en  los espacios respectivos, el código, el nombre y la ubicación, y además deberá seleccionar el nivel de demanda. Para terminar deberá apretar el botón "Aceptar" para agregar el proyecto, o "Cancelar" para descartar la operación.
			 
	-Modificar: Permite modificar los datos de un proyecto (excepto su código). Deberá seleccionar un proyecto haciendo clic sobre él en la tabla mostrada en pantalla y luego apretar el botón de “Modificar”. Se abrirá una ventana donde 
podrá cambiar el nombre, la ubicación y su nivel de demanda. Para terminar deberá apretar el botón "Aceptar" para modificar el proyecto, o "Cancelar" para descartar la operación.

	-Eliminar: Permite eliminar un proyecto. Deberá seleccionar un proyecto haciendo clic sobre él en la tabla mostrada en pantalla y luego apretar el botón de “Eliminar”. Se abrirá una ventana consultado si desea eliminar el proyecto, si apreta el botón “Si” se eliminara, en el caso de apretar no “No” se descarta la operación.

	-Buscar: Permite buscar un proyecto. Deberá hacer clic en “Buscar”, luego aparecerá una ventana solicitando ingresar el código del proyecto que se desea buscar. Para terminar deberá apretar el botón "Aceptar" para buscar el proyecto, o "Cancelar" para descartar la operación.


	-Refrescar: Refresca los datos haciendo click en el botón del mismo nombre.
		
-Apartado Departamentos: Podrá ver en la parte superior botones que les permitirá seleccionar qué tabla de departamentos que se desplegarán en pantalla, podrá filtrar a que proyecto pertenecen, el estado en que se encuentran (DISPONIBLE, RESERVADO y VENDIDO) y el nivel de demanda (BAJA, MEDIA y ALTA). Además, en la parte inferior encontrará las opciones "Agregar", "Modificar", "Eliminar", "Buscar" y "Cambiar estado".
	-Agregar: Permite agregar un nuevo departamento. Para ello deberá hacer clic en el botón “Agregar”, a continuación deberá escribir en los campos respectivos, el código, el numero de departamento, la cantidad de metros cuadrados, el precio base, seleccionar la demanda y el estado del departamento. Adicionalmente se puede elegir entre tipo básico o premium y solo para la categoría premium se permite elegir entre 3 extras. Para terminar deberá apretar el botón "Aceptar" para agregar el Departamento, o "Cancelar" para descartar la operación.

	-Modificar:Permite modificar los datos de un Departamento (excepto su código). Deberá seleccionar un departamento haciendo clic sobre él en la tabla mostrada en pantalla y luego apretar el botón de “Modificar”. Se abrirá una ventana donde podrá cambiar los datos del mismo. Para terminar deberá apretar el botón "Aceptar" para modificar el proyecto, o "Cancelar" para descartar la operación.

	-Eliminar: Permite eliminar un proyecto. Deberá seleccionar un proyecto haciendo clic sobre él en la tabla mostrada en pantalla y luego apretar el botón de “Eliminar”. Se abrirá una ventana consultado si desea eliminar el proyecto, si apreta el botón “Si” se eliminara, en el caso de apretar no “No” se descarta la operación.

	-Buscar: Permite buscar un departamento. Deberá hacer clic sobre el departamento que desea eliminar en la tabla mostrada en pantalla, luego aparecerá una ventana de confirmación, pulse "Si" para realizar la eliminación o pulse "No" para calcelar la acción.

	-Cambiar estado: Permite cambiar el estado de un departamento de forma directa. Deberá seleccionar el departamento haciendo clic en él desde la tabla, luego seleccionar la opción “Cambiar estado”, aparecerá una ventana en la pantalla donde deberá elegir el estado del departamento. Para terminar deberá apretar el botón "Aceptar" para modificar el estado del departamento, o "Cancelar" para descartar la operación.


-Pestaña Recomendador: Permite buscar departamentos según un presupuesto. Podrá ver en la parte superior una espacio donde podrá escribir un presupuesto, luego tendrá que hacer click en el botón "Buscar opciones" para desplegar en pantalla una lista de departamentos que se ajustan al presupuesto.
