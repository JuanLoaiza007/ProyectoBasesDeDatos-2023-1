------------------------------------------------
-- BASES DE DATOS DE UNA BIBLIOTECA 

-- Archivo: biblioteca.sql
-- Version: 1.0.6
-- Fecha de última modificacion: 2023-05-31 10:33am

-- Registro de cambios
-- Las tablas de devuelve_usuario_ejemplar, multa y descarga_usuario_libro ya no tienen id propio si no una PK compuesta (v1.0.6)
-- El ejemplar ahora tiene los atributos de la ubicacion donde está (v1.0.5)
-- Tabla de ejemplar_ubicacion y ubicacion eliminada (v1.0.5)
-- Tabla de publica_editorial_libros eliminada por redundancia, libro ya tiene el codigo de la editorial (v1.0.4)
-- Tablas estudiante y profesor tienen como pk unicamente id_usuario, el id_estudiante e id_profesor fueron eliminadas (v1.0.4) 
-- El id_empleado se eliminó de la tabla libro, la implementacion en Java será diferente (v1.0.4)

-- Autores:
-- alejandro.cano@correounivalle.edu.co -- 2179652
-- juan.loaiza.santiago@correounivalle.edu.co -- 2177570
-- juan.munoz.rojas@correounivalle.edu.co -- 2177436
------------------------------------------------
DROP TABLE IF EXISTS area_conocimiento CASCADE;
CREATE TABLE area_conocimiento (
  codigo_area VARCHAR (15) NOT NULL PRIMARY KEY,
  codigo_area_padre VARCHAR (15),
  nombre  VARCHAR (50),
  descripcion VARCHAR (250)
);

ALTER TABLE area_conocimiento 
  ADD CONSTRAINT area_conocimiento_padre_fk FOREIGN KEY (codigo_area_padre) REFERENCES area_conocimiento(codigo_area);
------------------------------------------------
DROP TABLE IF EXISTS empleado CASCADE;
CREATE TABLE empleado (
  id_empleado VARCHAR (15) NOT NULL PRIMARY KEY,
  nombre VARCHAR (50),
  cargo VARCHAR (50)
);
------------------------------------------------
DROP TABLE IF EXISTS autor CASCADE;
CREATE TABLE autor (
  codigo_autor VARCHAR (15) NOT NULL PRIMARY KEY,
  primer_nombre VARCHAR (15),
  segundo_nombre VARCHAR (15),
  primer_apellido VARCHAR (15),
  segundo_apellido VARCHAR (15)
);
------------------------------------------------
DROP TABLE IF EXISTS editorial CASCADE;
CREATE TABLE editorial (
  codigo_editorial VARCHAR (15) NOT NULL PRIMARY KEY,
  nombre VARCHAR (50),
  pagina_web VARCHAR (50),
  pais_origen VARCHAR (50)
);
------------------------------------------------
DROP TABLE IF EXISTS libro CASCADE;
CREATE TABLE libro (
  isbn VARCHAR (15) NOT NULL PRIMARY KEY,
  codigo_area VARCHAR (15) NOT NULL,
  codigo_editorial VARCHAR (15) NOT NULL,
  titulo VARCHAR (50),
  anio_publicacion VARCHAR (4),
  nro_paginas INTEGER
);

ALTER TABLE libro
  ADD CONSTRAINT codigo_area_fk FOREIGN KEY (codigo_area) REFERENCES area_conocimiento(codigo_area);

ALTER TABLE libro
  ADD CONSTRAINT codigo_editorial_fk FOREIGN KEY (codigo_editorial) REFERENCES editorial(codigo_editorial);
------------------------------------------------
DROP TABLE IF EXISTS libro_digital CASCADE;
CREATE TABLE libro_digital (
  isbn VARCHAR (15) NOT NULL,
  direccion_url VARCHAR (50) NOT NULL UNIQUE,
  tamanio_bytes INTEGER,
  formato VARCHAR (10)
);

ALTER TABLE libro_digital
  ADD CONSTRAINT libro_fk FOREIGN KEY (isbn) REFERENCES libro(isbn);

ALTER TABLE libro_digital 
  ADD CONSTRAINT libro_digital_pk PRIMARY KEY (isbn, direccion_url);
------------------------------------------------
DROP TABLE IF EXISTS ejemplar CASCADE;
CREATE TABLE ejemplar (
  isbn VARCHAR (15),
  nro_ejemplar VARCHAR (15) NOT NULL UNIQUE,
  sala VARCHAR (50),
  nro_pasillo INTEGER,
  estante INTEGER,
  nro_cajon INTEGER
);

ALTER TABLE ejemplar
  ADD CONSTRAINT libro_fk FOREIGN KEY (isbn) REFERENCES libro(isbn);

ALTER TABLE ejemplar 
  ADD CONSTRAINT libro_pk PRIMARY KEY (isbn, nro_ejemplar);
------------------------------------------------
DROP TABLE IF EXISTS libro_autor CASCADE; 
CREATE TABLE libro_autor (
  isbn VARCHAR (15) NOT NULL,
  codigo_autor VARCHAR (15) NOT NULL
);

ALTER TABLE libro_autor
  ADD CONSTRAINT libro_fk FOREIGN KEY (isbn) REFERENCES libro(isbn);

ALTER TABLE libro_autor
  ADD CONSTRAINT autor_fk FOREIGN KEY (codigo_autor) REFERENCES autor(codigo_autor);

ALTER TABLE libro_autor
  ADD CONSTRAINT libro_autor_pk PRIMARY KEY (isbn, codigo_autor);
------------------------------------------------
DROP TABLE IF EXISTS usuario CASCADE;
CREATE TABLE usuario (
  id_usuario VARCHAR (15) NOT NULL PRIMARY KEY,
  nombre VARCHAR (50),
  telefono VARCHAR (15),
  direccion VARCHAR (40),
  email VARCHAR (40)
);
------------------------------------------------
DROP TABLE IF EXISTS estudiante CASCADE;
CREATE TABLE estudiante (
  id_usuario VARCHAR (15) NOT NULL PRIMARY KEY,
  carrera VARCHAR (50),
  universidad VARCHAR (50)
);

ALTER TABLE estudiante
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
------------------------------------------------
DROP TABLE IF EXISTS profesor CASCADE;
CREATE TABLE profesor (
  id_usuario VARCHAR (15) NOT NULL PRIMARY KEY,
  titulo VARCHAR (50),
  dependencia VARChAR (50)
);

ALTER TABLE profesor
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
------------------------------------------------
DROP TABLE IF EXISTS profesor_area_conocimiento CASCADE;
CREATE TABLE profesor_area_conocimiento (
  id_usuario VARCHAR (15) NOT NULL,
  codigo_area VARCHAR (15) NOT NULL
);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT profesor_fk FOREIGN KEY (id_usuario) REFERENCES profesor(id_usuario);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT area_conocimiento_fk FOREIGN KEY (codigo_area) REFERENCES area_conocimiento(codigo_area);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT profesor_area_conocimiento_pk PRIMARY KEY (id_usuario, codigo_area);
------------------------------------------------
DROP TABLE IF EXISTS descarga_usuario_libro CASCADE;
CREATE TABLE descarga_usuario_libro (
  isbn VARCHAR (15) NOT NULL,
  direccion_url VARCHAR (50) NOT NULL,
  id_usuario VARCHAR (15) NOT NULL,  
  fecha TIMESTAMP,
  direccion_ip VARCHAR (15)  
);

ALTER TABLE descarga_usuario_libro
  ADD CONSTRAINT libro_digital_fk FOREIGN KEY (isbn, direccion_url) REFERENCES libro_digital(isbn, direccion_url);

ALTER TABLE descarga_usuario_libro
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE descarga_usuario_libro
  ADD CONSTRAINT descarga_usuario_libro_pk PRIMARY KEY (isbn, direccion_url, id_usuario, fecha);
------------------------------------------------
DROP TABLE IF EXISTS prestamo CASCADE;
CREATE TABLE prestamo (
  nro_consecutivo_prestamo VARCHAR (15) NOT NULL PRIMARY KEY,
  id_usuario VARCHAR (15) NOT NULL,
  id_empleado VARCHAR (15) NOT NULL,
  fecha_realizacion TIMESTAMP
); 

ALTER TABLE prestamo 
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE prestamo
  ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado);
------------------------------------------------
DROP TABLE IF EXISTS prestamo_ejemplar CASCADE;
CREATE TABLE prestamo_ejemplar (
  nro_consecutivo_prestamo VARCHAR (15) NOT NULL,
  isbn VARCHAR (15) NOT NULL,
  nro_ejemplar VARCHAR (15) NOT NULL,
  fecha_devolucion TIMESTAMP
);

ALTER TABLE prestamo_ejemplar
  ADD CONSTRAINT prestamo_fk FOREIGN KEY (nro_consecutivo_prestamo) REFERENCES prestamo(nro_consecutivo_prestamo);

ALTER TABLE prestamo_ejemplar
  ADD CONSTRAINT ejemplar_fk FOREIGN KEY (isbn, nro_ejemplar) REFERENCES ejemplar(isbn, nro_ejemplar);

ALTER TABLE prestamo_ejemplar
  ADD CONSTRAINT prestamo_ejemplar_pk PRIMARY KEY (nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion);
------------------------------------------------
DROP TABLE IF EXISTS devuelve_usuario_ejemplar CASCADE;
CREATE TABLE devuelve_usuario_ejemplar (
  id_usuario VARCHAR (15) NOT NULL,
  isbn VARCHAR (15) NOT NULL,
  nro_ejemplar VARCHAR (15) NOT NULL,
  fecha TIMESTAMP
);

ALTER TABLE devuelve_usuario_ejemplar
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE devuelve_usuario_ejemplar
  ADD CONSTRAINT ejemplar_fk FOREIGN KEY (isbn, nro_ejemplar) REFERENCES ejemplar(isbn, nro_ejemplar);

ALTER TABLE devuelve_usuario_ejemplar
  ADD CONSTRAINT devuelve_usuario_ejemplar_pk PRIMARY KEY (id_usuario, isbn, nro_ejemplar, fecha);
-----------------------------------------------
DROP TABLE IF EXISTS solicitud CASCADE;
CREATE TABLE solicitud (
  nro_consecutivo_solicitud VARCHAR (15) NOT NULL PRIMARY KEY,
  id_usuario VARCHAR (15) NOT NULL,
  id_empleado VARCHAR (15) NOT NULL,
  isbn VARCHAR (15) NOT NULL,
  titulo VARCHAR (50) NOT NULL,
  descripcion VARCHAR (250),
  fecha TIMESTAMP
);

ALTER TABLE solicitud
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE solicitud
  ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado);
------------------------------------------------
DROP TABLE IF EXISTS multa CASCADE;
CREATE TABLE multa (
  id_usuario VARCHAR (15) NOT NULL,
  nro_consecutivo_prestamo VARCHAR (15),
  fecha TIMESTAMP,
  valor INTEGER,
  descripcion VARCHAR (250)
);

ALTER TABLE multa
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE multa
  ADD CONSTRAINT prestamo_fk FOREIGN KEY (nro_consecutivo_prestamo) REFERENCES prestamo(nro_consecutivo_prestamo);

ALTER TABLE multa 
  ADD CONSTRAINT multa_pk PRIMARY KEY (id_usuario, nro_consecutivo_prestamo, fecha);
------------------------------------------------
--- INSERCION DE REGISTROS ---
------------------------------------------------
--- AREAS DE CONOCIMIENTO
INSERT INTO area_conocimiento (codigo_area, codigo_area_padre, nombre, descripcion) 
VALUES ('1', NULL, 'Ciencias Naturales', 'Área de conocimiento que estudia los fenómenos naturales'),
       ('2', NULL, 'Ciencias Sociales', 'Área de conocimiento que estudia las relaciones sociales y culturales'),
       ('3', '1', 'Física', 'Estudio de las propiedades de la materia y la energía'),
       ('4', '1', 'Química', 'Estudio de las propiedades de la materia y su transformación'),
       ('5', '1', 'Biología', 'Estudio de los seres vivos y su ambiente'),
       ('6', '2', 'Economía', 'Estudio de la producción, distribución y consumo de bienes y servicios'),
       ('7', '2', 'Sociología', 'Estudio de las relaciones sociales y culturales'),
       ('8', '2', 'Antropología', 'Estudio de los seres humanos y su cultura'),
       ('9', '5', 'Botánica', 'Estudio de las plantas'),
       ('10','5', 'Zoología', 'Estudio de los animales');
--- EMPLEADOS
INSERT INTO empleado (id_empleado, nombre, cargo)
VALUES ('1', 'Juan Pérez', 'Bibliotecario'),
      ('2', 'María González', 'Asistente de Biblioteca'),
      ('3', 'Pedro Martínez', 'Encargado de Adquisiciones'),
      ('4', 'Luisa Rodríguez', 'Catalogadora'),
      ('5', 'Carlos Gómez', 'Técnico de Mantenimiento'),
      ('6', 'Ana Ortiz', 'Asistente de Investigación'),
      ('7', 'Manuel Sánchez', 'Asistente de Préstamo'),
      ('8', 'Carmen Torres', 'Encargada de Eventos'),
      ('9', 'Diego Ruiz', 'Asistente de Sistemas'),
      ('10', 'Sofía Hernández', 'Encargada de Archivo');
------------------------------------------------
--- AUTORES DE LIBROS
INSERT INTO autor (codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido) 
VALUES ('1', 'Gabriel', '', 'García', 'Márquez'),
       ('2', 'Jorge', '', 'Luis', 'Borges'),
       ('3', 'Mario', '', 'Vargas', 'Llosa'),
       ('4', 'Julio', '', 'Cortázar', ''),
       ('5', 'Pablo', '', 'Neruda', ''),
       ('6', 'Isabel', '', 'Allende', ''),
       ('7', 'Gustavo', '', 'Adolfo', 'Bécquer'),
       ('8', 'Miguel', 'de', 'Cervantes', 'Saavedra'),
       ('9', 'Federico', '', 'García', 'Lorca'),
       ('10', 'Juan', 'Rulfo', '', '');
------------------------------------------------
--- EDITORIALES DE LIBROS
INSERT INTO editorial (codigo_editorial, nombre, pagina_web, pais_origen) 
VALUES ('1', 'Editorial Santillana', 'www.santillana.com', 'España'),
       ('2', 'Editorial Norma', 'www.norma.com', 'Colombia'),
       ('3', 'Editorial Anaya', 'www.anaya.es', 'España'),
       ('4', 'Editorial Planeta', 'www.planetadelibros.com', 'Argentina'),
       ('5', 'Editorial McGraw-Hill', 'www.mheducation.com', 'Estados Unidos'),
       ('6', 'Editorial Trillas', 'www.trillas.mx', 'México'),
       ('7', 'Editorial Pearson', 'www.pearson.com', 'Reino Unido'),
       ('8', 'Editorial Océano', 'www.oceano.com.mx', 'México'),
       ('9', 'Editorial Cambridge', 'www.cambridge.org', 'Reino Unido'),
       ('10', 'Editorial Oxford', 'www.oup.com', 'Reino Unido');
------------------------------------------------
--- LIBROS
INSERT INTO libro (isbn, codigo_area, codigo_editorial, titulo, anio_publicacion, nro_paginas)
VALUES ('978-0307476463', '1', '1', 'Cien años de soledad', '1967', 417),
       ('978-8437620629', '1', '3', 'El amor en los tiempos del cólera', '1985', 368),
       ('978-9507317181', '1', '4', 'Rayuela', '1963', 620),
       ('978-9875805174', '1', '4', 'Bestiario', '1951', 177),
       ('978-8420471839', '1', '3', 'La ciudad y los perros', '1963', 432),
       ('978-8432248138', '2', '2', 'La casa de los espíritus', '1982', 496),
       ('978-8432212429', '2', '2', 'De amor y de sombra', '1984', 252),
       ('978-8420441146', '2', '3', 'La sombra del viento', '2001', 576),
       ('978-6071502919', '3', '8', 'Pedro Páramo', '1955', 124),
       ('978-8433920228', '3', '3', 'Aura', '1962', 80);
------------------------------------------------
--- LIBROS DIGITALES    
INSERT INTO libro_digital (isbn, direccion_url, tamanio_bytes, formato)
VALUES ('978-0307476463', 'http://falselibros.com/libro1', 500000, 'PDF'),
       ('978-8437620629', 'http://falselibros.com/libro2', 300000, 'EPUB'),
       ('978-9507317181', 'http://falselibros.com/libro3', 200000, 'PDF'),
       ('978-9875805174', 'http://falselibros.com/libro4', 400000, 'MOBI'),
       ('978-8420471839', 'http://falselibros.com/libro5', 150000, 'PDF'),
       ('978-8432248138', 'http://falselibros.com/libro6', 600000, 'EPUB'),
       ('978-8432212429', 'http://falselibros.com/libro7', 250000, 'PDF'),
       ('978-8420441146', 'http://falselibros.com/libro8', 450000, 'MOBI'),
       ('978-6071502919', 'http://falselibros.com/libro9', 350000, 'PDF'),
       ('978-8433920228', 'http://falselibros.com/libro10', 550000, 'EPUB');
------------------------------------------------
--- EJEMPLARES 
INSERT INTO ejemplar (isbn, nro_ejemplar, sala, nro_pasillo, estante, nro_cajon)
VALUES ('978-0307476463', '1', 'Sala de Estudio', 2, 1, 1),
       ('978-8437620629', '2', 'Sala de Referencia', 1, 3, 1),
       ('978-9507317181', '3', 'Sala de Lectura', 1, 2, 2),
       ('978-9875805174', '4', 'Sala de Lectura', 1, 2, 3),
       ('978-8420471839', '5', 'Sala de Estudio', 2, 1, 2),
       ('978-8432248138', '6', 'Sala de Referencia', 1, 3, 2),
       ('978-8432212429', '7', 'Sala de Investigación', 2, 3, 1),
       ('978-8420441146', '8', 'Sala de Investigación', 2, 3, 2),
       ('978-6071502919', '9', 'Sala de Referencia', 1, 3, 3),
       ('978-8433920228', '10', 'Sala de Referencia', 1, 3, 4);
------------------------------------------------
--- LIBROS AUTORES
INSERT INTO libro_autor (isbn, codigo_autor) 
VALUES ('978-0307476463', '1'),
       ('978-0307476463', '4'),
       ('978-8437620629', '1'),
       ('978-8437620629', '3'),
       ('978-9507317181', '4'),
       ('978-9875805174', '4'),
       ('978-8420471839', '3'),
       ('978-8432248138', '6'),
       ('978-8432212429', '6'),
       ('978-8420441146', '6');
------------------------------------------------
--- USUARIOS       
INSERT INTO usuario (id_usuario, nombre, telefono, direccion, email)
VALUES ('1', 'Juan Perez', '555-1234', 'Av. 123, Lima', 'juan.perez@gmail.com'),
      ('2', 'Maria Rodriguez', '555-5678', 'Jr. 456, Lima', 'maria.rodriguez@yahoo.com'),
      ('3', 'Pedro Gomez', '555-9876', 'Calle 789, Lima', 'pedro.gomez@hotmail.com'),
      ('4', 'Ana Garcia', '555-4321', 'Av. 234, Lima', 'ana.garcia@gmail.com'),
      ('5', 'Luisa Ramirez', '555-8765', 'Jr. 567, Lima', 'luisa.ramirez@outlook.com'),
      ('6', 'Mario Chavez', '555-3456', 'Calle 890, Lima', 'mario.chavez@gmail.com'),
      ('7', 'Sofia Fernandez', '555-6543', 'Av. 901, Lima', 'sofia.fernandez@yahoo.com'),
      ('8', 'Jorge Torres', '555-7890', 'Jr. 234, Lima', 'jorge.torres@hotmail.com'),
      ('9', 'Carla Diaz', '555-2109', 'Calle 567, Lima', 'carla.diaz@gmail.com'),
      ('10', 'Fernando Perez', '555-9012', 'Av. 890, Lima', 'fernando.perez@outlook.com'),
      ('11', 'Silvia Rodriguez', '555-1092', 'Jr. 123, Lima', 'silvia.rodriguez@gmail.com'),
      ('12', 'Jose Gomez', '555-9201', 'Calle 456, Lima', 'jose.gomez@yahoo.com'),
      ('13', 'Laura Garcia', '555-5643', 'Av. 789, Lima', 'laura.garcia@hotmail.com'),
      ('14', 'Carlos Ramirez', '555-9810', 'Jr. 567, Lima', 'carlos.ramirez@gmail.com'),
      ('15', 'Paola Chavez', '555-3465', 'Calle 890, Lima', 'paola.chavez@yahoo.com'),
      ('16', 'Ricardo Fernandez', '555-5467', 'Av. 901, Lima', 'ricardo.fernandez@hotmail.com'),
      ('17', 'Julia Torres', '555-8765', 'Jr. 234, Lima', 'julia.torres@gmail.com'),
      ('18', 'Daniel Diaz', '555-0987', 'Calle 567, Lima', 'daniel.diaz@outlook.com'),
      ('19', 'Camila Perez', '555-8901', 'Av. 890, Lima', 'camila.perez@gmail.com'),
      ('20', 'Gabriel Rodriguez', '555-2345', 'Jr. 123, Lima', 'gabriel.rodriguez@yahoo.com');    
------------------------------------------------
--- ESTUDIANTES 
INSERT INTO estudiante (id_usuario, carrera, universidad)
VALUES ('11', 'Ingeniería Civil', 'Universidad Nacional de Colombia'),
      ('12', 'Medicina Veterinaria', 'Universidad de Antioquia'),
      ('13', 'Derecho', 'Universidad del Rosario'),
      ('14', 'Psicología', 'Universidad de los Andes'),
      ('15', 'Administración de Empresas', 'Universidad de la Costa'),
      ('16', 'Ingeniería de Sistemas', 'Universidad del Valle'),
      ('17', 'Comunicación Social', 'Universidad de la Sabana'),
      ('18', 'Arquitectura', 'Universidad Sergio Arboleda'),
      ('19', 'Ciencias Políticas', 'Universidad del Norte'),
      ('20', 'Economía', 'Pontificia Universidad Javeriana');  
------------------------------------------------
--- PROFESORES 
INSERT INTO profesor (id_usuario, titulo, dependencia)
VALUES ('1', 'Maestro', 'Departamento de Matematicas'),
      ('2', 'Licenciado', 'Departamento de Fisica'),
      ('3', 'Maestro', 'Departamento de Biologia'),
      ('4', 'Maestro', 'Departamento de Quimica'),
      ('5', 'Licenciado', 'Departamento de Informatica'),
      ('6', 'Doctor', 'Departamento de Biologia'),
      ('7', 'Mestro', 'Departamento de Quimica'),
      ('8', 'Licenciado', 'Departamento de Matematica'),
      ('9', 'Maestro', 'Departamento de Fisica'),
      ('10', 'Maestro', 'Departamento de Algebra');
------------------------------------------------
--- PROFESORES Y AREAS DE INTERES
INSERT INTO profesor_area_conocimiento (id_usuario, codigo_area)
VALUES ('1', '1'),
      ('2', '7'),
      ('3', '5'),
      ('4', '6'),
      ('5', '3'),
      ('6', '6'),
      ('7', '8'),
      ('8', '7'),
      ('9', '5'),
      ('10', '2');
------------------------------------------------
--- LIBROS Y DESCARGAS DE USUARIOS
INSERT INTO descarga_usuario_libro (isbn, direccion_url, id_usuario, fecha, direccion_ip)
VALUES 
  ('978-0307476463', 'http://falselibros.com/libro1', '1', '2023-05-14 10:00:00', '192.168.0.1' ),
  ('978-8437620629', 'http://falselibros.com/libro2', '2', '2023-05-14 11:00:00', '192.168.0.2' ),
  ('978-9507317181', 'http://falselibros.com/libro3', '3', '2023-05-14 12:00:00', '192.168.0.3' ),
  ('978-9875805174', 'http://falselibros.com/libro4', '4', '2023-05-14 13:00:00', '192.168.0.4' ),
  ('978-8420471839', 'http://falselibros.com/libro5', '5', '2023-05-14 14:00:00', '192.168.0.5' ),
  ('978-8432248138', 'http://falselibros.com/libro6', '6', '2023-05-14 15:00:00', '192.168.0.6' ),
  ('978-8432212429', 'http://falselibros.com/libro7', '7', '2023-05-14 16:00:00', '192.168.0.7' ),
  ('978-8420441146', 'http://falselibros.com/libro8', '8', '2023-05-14 17:00:00', '192.168.0.8' ),
  ('978-6071502919', 'http://falselibros.com/libro9', '9', '2023-05-14 18:00:00', '192.168.0.9' ),
  ('978-8433920228', 'http://falselibros.com/libro10', '10', '2023-05-14 19:00:00', '192.168.0.10');
------------------------------------------------
--- PRESTAMOS
INSERT INTO prestamo (nro_consecutivo_prestamo, id_usuario, id_empleado, fecha_realizacion)
VALUES ('1', '1', '1', '2023-05-14 10:30:00'),
      ('2', '2', '2', '2023-05-14 11:00:00'),
      ('3', '3', '1', '2023-05-14 11:30:00'),
      ('4', '4', '1', '2023-05-14 12:00:00'),
      ('5', '5', '2', '2023-05-14 13:00:00'),
      ('6', '6', '1', '2023-05-14 14:00:00'),
      ('7', '7', '1', '2023-05-14 15:00:00'),
      ('8', '8', '2', '2023-05-14 16:00:00'),
      ('9', '9', '1', '2023-05-14 17:00:00'),
      ('10', '10', '1', '2023-05-14 18:00:00');
------------------------------------------------
--- PRESTAMOS EJEMPLARES      
INSERT INTO prestamo_ejemplar (nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion)
VALUES ('1', '978-0307476463', '1', '2023-05-16 10:30:00'),
      ('2', '978-8437620629', '2', '2023-05-16 10:30:00'),
      ('3', '978-9507317181', '3', '2023-05-17 11:00:00'),
      ('4', '978-9875805174', '4', '2023-05-18 11:30:00'),
      ('5', '978-8420471839', '5', '2023-05-18 11:30:00'),
      ('6', '978-8432248138', '6', '2023-05-19 12:00:00'),
      ('7', '978-8432212429', '7', '2023-05-20 13:00:00'),
      ('8', '978-8420441146', '8', '2023-05-21 14:00:00'),
      ('9', '978-6071502919', '9', '2023-05-22 15:00:00'),
      ('10', '978-8433920228', '10', '2023-05-23 16:00:00');
------------------------------------------------
--- DEVOLUCIONES EJEMPLARES
INSERT INTO devuelve_usuario_ejemplar (id_usuario, isbn, nro_ejemplar, fecha)
VALUES 
      ('1', '978-0307476463', '1', '2023-05-16 10:35:00'),
      ('2', '978-8437620629', '2', '2023-05-16 10:40:00'),
      ('3', '978-9507317181', '3', '2023-05-17 11:05:00'),
      ('4', '978-9875805174', '4', '2023-05-18 11:35:00'),
      ('5', '978-8420471839', '5', '2023-05-18 11:35:00'),
      ('6', '978-8432248138', '6', '2023-05-19 12:15:00'),
      ('7', '978-8432212429', '7', '2023-05-20 13:10:00'),
      ('8', '978-8420441146', '8', '2023-05-21 14:20:00'),
      ('9', '978-6071502919', '9', '2023-05-22 15:30:00'),
      ('10', '978-8433920228', '10', '2023-05-23 16:40:00');  
------------------------------------------------
--- SOLICITUDES  
INSERT INTO solicitud (nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha)
VALUES
('1', '1', '3', '978-3161484100', 'El Gran Gatsby', 'Solicitud de préstamo para el libro El Gran Gatsby', '2023-05-14 14:30:00'),
('2', '2', '3', '978-8466331844', 'Harry Potter y la piedra filosofal', 'Solicitud de préstamo para el libro Harry Potter y la piedra filosofal', '2023-05-14 15:30:00'),
('3', '3', '3', '978-0544272996', 'El hobbit', 'Solicitud de préstamo para el libro El hobbit', '2023-05-15 10:00:00'),
('4', '4', '3', '978-6075277254', 'La insoportable levedad del ser', 'Solicitud de préstamo para el libro La insoportable levedad del ser', '2023-05-15 11:00:00'),
('5', '5', '3', '978-0307278466', '1984', 'Solicitud de préstamo para el libro 1984', '2023-05-16 13:00:00'),
('6', '6', '3', '978-0307476463', 'Cien años de soledad', 'Solicitud de préstamo para el libro Cien años de soledad', '2023-05-16 14:00:00'),
('7', '7', '3', '978-8437604947', 'La ciudad y los perros', 'Solicitud de préstamo para el libro La ciudad y los perros', '2023-05-17 10:30:00'),
('8', '8', '3', '978-8408163172', 'Origen', 'Solicitud de préstamo para el libro Origen', '2023-05-17 11:30:00'),
('9', '9', '3', '978-8420407437', 'Crimen y castigo', 'Solicitud de préstamo para el libro Crimen y castigo', '2023-05-18 12:00:00'),
('10', '10', '3', '978-8498383620', 'Los hombres que no amaban a las mujeres', 'Solicitud de préstamo para el libro Los hombres que no amaban a las mujeres', '2023-05-18 13:00:00');
------------------------------------------------
--- MULTAS
INSERT INTO multa (id_usuario, nro_consecutivo_prestamo, fecha, valor, descripcion)
VALUES 
      ('1', '1', '2023-05-16 10:35:00', 40000, 'Retraso en devolución'),
      ('2', '2', '2023-05-16 10:40:00', 40000, 'Retraso en devolución'),
      ('3', '3', '2023-05-17 11:05:00', 40000, 'Retraso en devolución'),
      ('4', '4', '2023-05-18 11:35:00', 40000, 'Retraso en devolución'),
      ('5', '5', '2023-05-18 11:35:00', 40000, 'Retraso en devolución'),
      ('6', '6', '2023-05-19 12:15:00', 40000, 'Retraso en devolución'),
      ('7', '7', '2023-05-20 13:10:00', 40000, 'Retraso en devolución'),
      ('8', '8', '2023-05-21 14:20:00', 40000, 'Retraso en devolución'),
      ('9', '9', '2023-05-22 15:30:00', 40000, 'Retraso en devolución'),
      ('10', '10', '2023-05-23 16:40:00', 40000, 'Retraso en devolución');