# ProyectoBasesDeDatos-2023-1

[![Vista-Login.png](https://i.postimg.cc/d0TmF5n3/Vista-Login.png)](https://postimg.cc/QBhWkk2Z)

## Integrantes (A - Z)
* Alejandro Guerrero Cano (Alejo101102)
* Juan David Loaiza Santiago (JuanLoaiza007)
* Juan Sebastian Muñoz Rojas (sebastianmr18)

## Tema
Proyecto final del curso de Bases de Datos enfocado en combinar PostgreSQL y Java para crear un sistema de administración de recursos de una biblioteca,
este proyecto busca evidenciar los resultados de aprendizaje de los integrantes en el curso partiendo de habilidades como el manejo del CRUD y la conexion
a una base de datos de PostgreSQL a partir de un lenguaje de programación que permita transformar consultas desde una interfaz gráfica hasta SQL.

## Instrucciones
Este proyecto fue creado usando Apache NetBeans IDE 17, JDK 17 y PostgreSQL JDBC Driver (42.2.16) por lo que bajo este entorno el proyecto funciona perfectamente, para
probarlo puede seguir los siguientes pasoss:
1. Importar el proyecto a su IDE usando git clone u otro metodo, (en caso de que el proyecto tenga conflictos de clic derecho sobre el y busque la opcion para
resolverlos automáticamente con el IDE)
2. En uno de los paquetes encontrará la base de datos en .sql paraver su estructura y crearla en postgreSQL, asegurese de ejecutar este archivo para que el programa
funcione correctamente.
[![BDsql.png](https://i.postimg.cc/02HrdD10/BDsql.png)](https://postimg.cc/xcM09kCX)
3. En el paquete de BaseDeDatos encontrará la clase BibliotecaManager.java, debe actualizar los datos de ingreso a postgreSQL para que el programa se pueda conectar correctamente
a la base de datos.
[![Biblioteca-Manager.png](https://i.postimg.cc/tgLVpP4z/Biblioteca-Manager.png)](https://postimg.cc/t1NgzsP1)
4. Construir y ejecutar el proyecto.

## Roles predefinidos
[![Vista-Administrador.png](https://i.postimg.cc/k5n78ghd/Vista-Administrador.png)](https://postimg.cc/3kcMQYTL)
El proyecto intenta hacer una diferenciación entre usuarios de tipo administrativos (administradores y empleados) y clientes (estudiantes y profesores). Para probar cada
rol puede usar los siguientes usuarios que estan predefinidos en el archivo .sql
### Administrador
**Correo:** admin  
**Contraseña:** admin
### Empleado  
**Correo:** 1  
**Contraseña:** Juanpe2012
### Estudiante/Profesor  
**Correo:** default@gmail.com  
**Contraseña:** default  
  
> Puede cambiar las contraseñas en el apartado "Avanzado" en el Dashboard

## Nota
Este proyecto esta realizado con fines meramente académicos y educativos, el repositorio se hizo público únicamente para que el profesor de curso pueda analizarlo y realizar su evaluación.  

En este proyecto hay recursos gráficos como los iconos del IDE y de otras herramientas que se usaron para su desarrollo, ninguno de estos fue creado por nosotros y no son usados con fines de lucro.  
Los iconos de la GUI fueron extraidos de flaticon: https://www.flaticon.com/uicons/interface-icons
