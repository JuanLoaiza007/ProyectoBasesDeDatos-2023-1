------------------------------------------------
-- BASES DE DATOS DE UNA BIBLIOTECA 

-- Archivo: biblioteca.sql
-- Version: 1.0.2
-- Fecha de última modificacion: 2023-05-12 09:28pm

-- Autores:
-- alejandro.cano@correounivalle.edu.co -- 2179652
-- juan.loaiza.santiago@correounivalle.edu.co -- 2177570
-- juan.munoz.rojas@correounivalle.edu.co -- 2177436

-- Changelog:
-- Los atributos de profesor han cambiado de (carrera -> titulo) y (universidad -> dependencia) v1.0.2
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
DROP TABLE IF EXISTS ubicacion CASCADE;
CREATE TABLE ubicacion (
  id_ubicacion VARCHAR (15) NOT NULL PRIMARY KEY,
  nombre_sala VARCHAR (50),
  nro_pasillo INTEGER,
  estante INTEGER,
  nro_cajon INTEGER
);
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
  id_empleado VARCHAR (15),
  titulo VARCHAR (50),
  anio_publicacion VARCHAR (4),
  nro_paginas INTEGER
);

ALTER TABLE libro
  ADD CONSTRAINT codigo_area_fk FOREIGN KEY (codigo_area) REFERENCES area_conocimiento(codigo_area);

ALTER TABLE libro
  ADD CONSTRAINT codigo_editorial_fk FOREIGN KEY (codigo_editorial) REFERENCES editorial(codigo_editorial);

ALTER TABLE libro 
  ADD CONSTRAINT id_empleado FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado);
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
  nro_ejemplar VARCHAR (15) NOT NULL UNIQUE
);

ALTER TABLE ejemplar
  ADD CONSTRAINT libro_fk FOREIGN KEY (isbn) REFERENCES libro(isbn);

ALTER TABLE ejemplar 
  ADD CONSTRAINT libro_pk PRIMARY KEY (isbn, nro_ejemplar);
------------------------------------------------
DROP TABLE IF EXISTS ejemplar_ubicacion CASCADE;
CREATE TABLE ejemplar_ubicacion (
  isbn VARCHAR (15) NOT NULL,
  nro_ejemplar VARCHAR (15) NOT NULL,
  id_ubicacion VARCHAR (15) NOT NULL
);

ALTER TABLE ejemplar_ubicacion
  ADD CONSTRAINT ejemplar_fk FOREIGN KEY (isbn, nro_ejemplar) REFERENCES ejemplar(isbn, nro_ejemplar);

ALTER TABLE ejemplar_ubicacion
  ADD CONSTRAINT ubicacion_fk FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion);

ALTER TABLE ejemplar_ubicacion
  ADD CONSTRAINT ejemplar_ubicacion_pk PRIMARY KEY (isbn, nro_ejemplar, id_ubicacion);
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
DROP TABLE IF EXISTS publica_editorial_libro CASCADE;
CREATE TABLE publica_editorial_libro (
  codigo_editorial VARCHAR (15) NOT NULL,
  isbn VARCHAR (15) NOT NULL
);

ALTER TABLE publica_editorial_libro
  ADD CONSTRAINT editorial_fk FOREIGN KEY (codigo_editorial) REFERENCES editorial(codigo_editorial);

ALTER TABLE publica_editorial_libro 
  ADD CONSTRAINT libro_fk FOREIGN KEY (isbn) REFERENCES libro(isbn);

ALTER TABLE publica_editorial_libro
  ADD CONSTRAINT publica_editorial_libro_pk PRIMARY KEY (codigo_editorial, isbn);
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
  id_usuario VARCHAR (15) NOT NULL UNIQUE,
  id_estudiante VARCHAR (15) NOT NULL UNIQUE,
  carrera VARCHAR (50),
  universidad VARCHAR (50)
);

ALTER TABLE estudiante
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE estudiante
  ADD CONSTRAINT estudiante_pk PRIMARY KEY (id_usuario, id_estudiante);
------------------------------------------------
DROP TABLE IF EXISTS profesor CASCADE;
CREATE TABLE profesor (
  id_usuario VARCHAR (15) NOT NULL UNIQUE,
  id_profesor VARCHAR (15) NOT NULL UNIQUE,
  titulo VARCHAR (50),
  dependencia VARChAR (50)
);

ALTER TABLE profesor
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE profesor
  ADD CONSTRAINT profesor_pk PRIMARY KEY (id_usuario, id_profesor);
------------------------------------------------
DROP TABLE IF EXISTS profesor_area_conocimiento CASCADE;
CREATE TABLE profesor_area_conocimiento (
  id_usuario VARCHAR (15) NOT NULL,
  id_profesor VARCHAR (15) NOT NULL,
  codigo_area VARCHAR (15) NOT NULL
);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT profesor_fk FOREIGN KEY (id_usuario, id_profesor) REFERENCES profesor(id_usuario, id_profesor);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT area_conocimiento_fk FOREIGN KEY (codigo_area) REFERENCES area_conocimiento(codigo_area);

ALTER TABLE profesor_area_conocimiento
  ADD CONSTRAINT profesor_area_conocimiento_pk PRIMARY KEY (id_usuario, id_profesor, codigo_area);
------------------------------------------------
DROP TABLE IF EXISTS descarga_usuario_libro CASCADE;
CREATE TABLE descarga_usuario_libro (
  id_descarga VARCHAR (15) NOT NULL PRIMARY KEY,
  isbn VARCHAR (15) NOT NULL,
  direccion_url VARCHAR (50) NOT NULL,
  id_usuario VARCHAR (15) NOT NULL,
  direccion VARCHAR (30),
  direccion_ip VARCHAR (15),
  fecha DATE,
  hora TIME
);

ALTER TABLE descarga_usuario_libro
  ADD CONSTRAINT libro_digital_fk FOREIGN KEY (isbn, direccion_url) REFERENCES libro_digital(isbn, direccion_url);

ALTER TABLE descarga_usuario_libro
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
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
  id_devolucion VARCHAR (15) NOT NULL PRIMARY KEY,
  id_usuario VARCHAR (15) NOT NULL,
  isbn VARCHAR (15) NOT NULL,
  nro_ejemplar VARCHAR (15) NOT NULL,
  fecha TIMESTAMP
);

ALTER TABLE devuelve_usuario_ejemplar
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);

ALTER TABLE devuelve_usuario_ejemplar
  ADD CONSTRAINT ejemplar_fk FOREIGN KEY (isbn, nro_ejemplar) REFERENCES ejemplar(isbn, nro_ejemplar);
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
  id_multa VARCHAR (15) NOT NULL PRIMARY KEY,
  id_usuario VARCHAR (15) NOT NULL,
  fecha TIMESTAMP,
  valor INTEGER,
  descripcion VARCHAR (250)
);

ALTER TABLE multa
  ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
------------------------------------------------
--- INSERCION DE REGISTROS ---
------------------------------------------------
--- AREAS DE CONOCIMIENTO
INSERT INTO area_conocimiento (codigo_area, codigo_area_padre, nombre, descripcion) 
VALUES ('AC001', NULL, 'Ciencias Naturales', 'Área de conocimiento que estudia los fenómenos naturales'),
       ('AC002', NULL, 'Ciencias Sociales', 'Área de conocimiento que estudia las relaciones sociales y culturales'),
       ('AC003', 'AC001', 'Física', 'Estudio de las propiedades de la materia y la energía'),
       ('AC004', 'AC001', 'Química', 'Estudio de las propiedades de la materia y su transformación'),
       ('AC005', 'AC001', 'Biología', 'Estudio de los seres vivos y su ambiente'),
       ('AC006', 'AC002', 'Economía', 'Estudio de la producción, distribución y consumo de bienes y servicios'),
       ('AC007', 'AC002', 'Sociología', 'Estudio de las relaciones sociales y culturales'),
       ('AC008', 'AC002', 'Antropología', 'Estudio de los seres humanos y su cultura'),
       ('AC009', 'AC005', 'Botánica', 'Estudio de las plantas'),
       ('AC010', 'AC005', 'Zoología', 'Estudio de los animales');
------------------------------------------------
--- UBICACIONES DE SALAS
INSERT INTO ubicacion (id_ubicacion, nombre_sala, nro_pasillo, estante, nro_cajon) 
VALUES ('UB001', 'Sala de Lectura', 1, 2, 3),
       ('UB002', 'Sala de Estudio', 2, 1, 4),
       ('UB003', 'Sala de Referencia', 1, 3, 1),
       ('UB004', 'Sala Infantil', 3, 2, 2),
       ('UB005', 'Sala de Investigación', 2, 3, 5),
       ('UB006', 'Sala de Computadoras', 1, 1, 1),
       ('UB007', 'Sala de Audiovisuales', 3, 1, 2),
       ('UB008', 'Sala de Conferencias', 2, 2, 3),
       ('UB009', 'Sala de Cómics', 3, 3, 4),
       ('UB010', 'Sala de Arte', 1, 2, 5);
------------------------------------------------
--- EMPLEADOS
INSERT INTO empleado (id_empleado, nombre, cargo)
VALUES ('EMP001', 'Juan Pérez', 'Bibliotecario'),
      ('EMP002', 'María González', 'Asistente de Biblioteca'),
      ('EMP003', 'Pedro Martínez', 'Encargado de Adquisiciones'),
      ('EMP004', 'Luisa Rodríguez', 'Catalogadora'),
      ('EMP005', 'Carlos Gómez', 'Técnico de Mantenimiento'),
      ('EMP006', 'Ana Ortiz', 'Asistente de Investigación'),
      ('EMP007', 'Manuel Sánchez', 'Asistente de Préstamo'),
      ('EMP008', 'Carmen Torres', 'Encargada de Eventos'),
      ('EMP009', 'Diego Ruiz', 'Asistente de Sistemas'),
      ('EMP010', 'Sofía Hernández', 'Encargada de Archivo');
------------------------------------------------
--- AUTORES DE LIBROS
INSERT INTO autor (codigo_autor, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido) 
VALUES ('AU001', 'Gabriel', '', 'García', 'Márquez'),
       ('AU002', 'Jorge', '', 'Luis', 'Borges'),
       ('AU003', 'Mario', '', 'Vargas', 'Llosa'),
       ('AU004', 'Julio', '', 'Cortázar', ''),
       ('AU005', 'Pablo', '', 'Neruda', ''),
       ('AU006', 'Isabel', '', 'Allende', ''),
       ('AU007', 'Gustavo', '', 'Adolfo', 'Bécquer'),
       ('AU008', 'Miguel', 'de', 'Cervantes', 'Saavedra'),
       ('AU009', 'Federico', '', 'García', 'Lorca'),
       ('AU010', 'Juan', 'Rulfo', '', '');
------------------------------------------------
--- EDITORIALES DE LIBROS
INSERT INTO editorial (codigo_editorial, nombre, pagina_web, pais_origen) 
VALUES ('E1', 'Editorial Santillana', 'www.santillana.com', 'España'),
       ('E2', 'Editorial Norma', 'www.norma.com', 'Colombia'),
       ('E3', 'Editorial Anaya', 'www.anaya.es', 'España'),
       ('E4', 'Editorial Planeta', 'www.planetadelibros.com', 'Argentina'),
       ('E5', 'Editorial McGraw-Hill', 'www.mheducation.com', 'Estados Unidos'),
       ('E6', 'Editorial Trillas', 'www.trillas.mx', 'México'),
       ('E7', 'Editorial Pearson', 'www.pearson.com', 'Reino Unido'),
       ('E8', 'Editorial Océano', 'www.oceano.com.mx', 'México'),
       ('E9', 'Editorial Cambridge', 'www.cambridge.org', 'Reino Unido'),
       ('E10', 'Editorial Oxford', 'www.oup.com', 'Reino Unido');
------------------------------------------------
--- LIBROS
INSERT INTO libro (isbn, codigo_area, codigo_editorial, id_empleado, titulo, anio_publicacion, nro_paginas)
VALUES ('978-0307476463', 'AC001', 'E1', 'EMP003', 'Cien años de soledad', '1967', 417),
       ('978-8437620629', 'AC001', 'E3', 'EMP003', 'El amor en los tiempos del cólera', '1985', 368),
       ('978-9507317181', 'AC001', 'E4', 'EMP003', 'Rayuela', '1963', 620),
       ('978-9875805174', 'AC001', 'E4', 'EMP003', 'Bestiario', '1951', 177),
       ('978-8420471839', 'AC001', 'E3', 'EMP003', 'La ciudad y los perros', '1963', 432),
       ('978-8432248138', 'AC002', 'E2', 'EMP003', 'La casa de los espíritus', '1982', 496),
       ('978-8432212429', 'AC002', 'E2', 'EMP003', 'De amor y de sombra', '1984', 252),
       ('978-8420441146', 'AC002', 'E3', 'EMP003', 'La sombra del viento', '2001', 576),
       ('978-6071502919', 'AC003', 'E8', 'EMP003', 'Pedro Páramo', '1955', 124),
       ('978-8433920228', 'AC003', 'E3', 'EMP003', 'Aura', '1962', 80);
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
INSERT INTO ejemplar (isbn, nro_ejemplar)
VALUES ('978-0307476463', 'EJ00001'),
       ('978-8437620629', 'EJ00002'),
       ('978-9507317181', 'EJ00003'),
       ('978-9875805174', 'EJ00004'),
       ('978-8420471839', 'EJ00005'),
       ('978-8432248138', 'EJ00006'),
       ('978-8432212429', 'EJ00007'),
       ('978-8420441146', 'EJ00008'),
       ('978-6071502919', 'EJ00009'),
       ('978-8433920228', 'EJ00010');
------------------------------------------------
--- EJEMPLARES UBICACION
INSERT INTO ejemplar_ubicacion (isbn, nro_ejemplar, id_ubicacion)
VALUES ('978-0307476463', 'EJ00001', 'UB001'),
       ('978-8437620629', 'EJ00002', 'UB002'),
       ('978-9507317181', 'EJ00003', 'UB003'),
       ('978-9875805174', 'EJ00004', 'UB004'),
       ('978-8420471839', 'EJ00005', 'UB005'),
       ('978-8432248138', 'EJ00006', 'UB006'),
       ('978-8432212429', 'EJ00007', 'UB007'),
       ('978-8420441146', 'EJ00008', 'UB008'),
       ('978-6071502919', 'EJ00009', 'UB009'),
       ('978-8433920228', 'EJ00010', 'UB010');
------------------------------------------------
--- LIBROS AUTORES
INSERT INTO libro_autor (isbn, codigo_autor) 
VALUES ('978-0307476463', 'AU001'),
       ('978-0307476463', 'AU004'),
       ('978-8437620629', 'AU001'),
       ('978-8437620629', 'AU003'),
       ('978-9507317181', 'AU004'),
       ('978-9875805174', 'AU004'),
       ('978-8420471839', 'AU003'),
       ('978-8432248138', 'AU006'),
       ('978-8432212429', 'AU006'),
       ('978-8420441146', 'AU006');
------------------------------------------------
--- EDITORIALES PUBLICAN LIBROS
INSERT INTO publica_editorial_libro (codigo_editorial, isbn) 
VALUES ('E1', '978-0307476463'),
       ('E3', '978-8437620629'),
       ('E4', '978-9507317181'),
       ('E4', '978-9875805174'),
       ('E3', '978-8420471839'),
       ('E2', '978-8432248138'),
       ('E2', '978-8432212429'),
       ('E3', '978-8420441146'),
       ('E8', '978-6071502919'),
       ('E3', '978-8433920228');
------------------------------------------------
--- USUARIOS       
INSERT INTO usuario (id_usuario, nombre, telefono, direccion, email)
VALUES ('USR001', 'Juan Perez', '555-1234', 'Av. 123, Lima', 'juan.perez@gmail.com'),
      ('USR002', 'Maria Rodriguez', '555-5678', 'Jr. 456, Lima', 'maria.rodriguez@yahoo.com'),
      ('USR003', 'Pedro Gomez', '555-9876', 'Calle 789, Lima', 'pedro.gomez@hotmail.com'),
      ('USR004', 'Ana Garcia', '555-4321', 'Av. 234, Lima', 'ana.garcia@gmail.com'),
      ('USR005', 'Luisa Ramirez', '555-8765', 'Jr. 567, Lima', 'luisa.ramirez@outlook.com'),
      ('USR006', 'Mario Chavez', '555-3456', 'Calle 890, Lima', 'mario.chavez@gmail.com'),
      ('USR007', 'Sofia Fernandez', '555-6543', 'Av. 901, Lima', 'sofia.fernandez@yahoo.com'),
      ('USR008', 'Jorge Torres', '555-7890', 'Jr. 234, Lima', 'jorge.torres@hotmail.com'),
      ('USR009', 'Carla Diaz', '555-2109', 'Calle 567, Lima', 'carla.diaz@gmail.com'),
      ('USR010', 'Fernando Perez', '555-9012', 'Av. 890, Lima', 'fernando.perez@outlook.com'),
      ('USR011', 'Silvia Rodriguez', '555-1092', 'Jr. 123, Lima', 'silvia.rodriguez@gmail.com'),
      ('USR012', 'Jose Gomez', '555-9201', 'Calle 456, Lima', 'jose.gomez@yahoo.com'),
      ('USR013', 'Laura Garcia', '555-5643', 'Av. 789, Lima', 'laura.garcia@hotmail.com'),
      ('USR014', 'Carlos Ramirez', '555-9810', 'Jr. 567, Lima', 'carlos.ramirez@gmail.com'),
      ('USR015', 'Paola Chavez', '555-3465', 'Calle 890, Lima', 'paola.chavez@yahoo.com'),
      ('USR016', 'Ricardo Fernandez', '555-5467', 'Av. 901, Lima', 'ricardo.fernandez@hotmail.com'),
      ('USR017', 'Julia Torres', '555-8765', 'Jr. 234, Lima', 'julia.torres@gmail.com'),
      ('USR018', 'Daniel Diaz', '555-0987', 'Calle 567, Lima', 'daniel.diaz@outlook.com'),
      ('USR019', 'Camila Perez', '555-8901', 'Av. 890, Lima', 'camila.perez@gmail.com'),
      ('USR020', 'Gabriel Rodriguez', '555-2345', 'Jr. 123, Lima', 'gabriel.rodriguez@yahoo.com');    
------------------------------------------------
--- ESTUDIANTES 
INSERT INTO estudiante (id_usuario, id_estudiante, carrera, universidad)
VALUES ('USR011', 'EST001', 'Ingeniería Civil', 'Universidad Nacional de Colombia'),
      ('USR012', 'EST002', 'Medicina Veterinaria', 'Universidad de Antioquia'),
      ('USR013', 'EST003', 'Derecho', 'Universidad del Rosario'),
      ('USR014', 'EST004', 'Psicología', 'Universidad de los Andes'),
      ('USR015', 'EST005', 'Administración de Empresas', 'Universidad de la Costa'),
      ('USR016', 'EST006', 'Ingeniería de Sistemas', 'Universidad del Valle'),
      ('USR017', 'EST007', 'Comunicación Social', 'Universidad de la Sabana'),
      ('USR018', 'EST008', 'Arquitectura', 'Universidad Sergio Arboleda'),
      ('USR019', 'EST009', 'Ciencias Políticas', 'Universidad del Norte'),
      ('USR020', 'EST010', 'Economía', 'Pontificia Universidad Javeriana');  
------------------------------------------------
--- PROFESORES 
INSERT INTO profesor (id_usuario, id_profesor, titulo, dependencia)
VALUES ('USR001', 'PRF001', 'Ingeniería Industrial', 'Universidad Nacional de Colombia'),
      ('USR002', 'PRF002', 'Derecho', 'Universidad de Antioquia'),
      ('USR003', 'PRF003', 'Medicina', 'Universidad del Rosario'),
      ('USR004', 'PRF004', 'Administración de Empresas', 'Universidad de los Andes'),
      ('USR005', 'PRF005', 'Ingeniería de Sistemas', 'Universidad de la Costa'),
      ('USR006', 'PRF006', 'Economía', 'Universidad del Valle'),
      ('USR007', 'PRF007', 'Arquitectura', 'Universidad de la Sabana'),
      ('USR008', 'PRF008', 'Ciencias Políticas', 'Universidad Sergio Arboleda'),
      ('USR009', 'PRF009', 'Psicología', 'Universidad del Norte'),
      ('USR010', 'PRF010', 'Comunicación Social', 'Pontificia Universidad Javeriana');
------------------------------------------------
--- PROFESORES Y AREAS DE INTERES
INSERT INTO profesor_area_conocimiento (id_usuario, id_profesor, codigo_area)
VALUES ('USR001', 'PRF001', 'AC001'),
      ('USR002', 'PRF002', 'AC007'),
      ('USR003', 'PRF003', 'AC005'),
      ('USR004', 'PRF004', 'AC006'),
      ('USR005', 'PRF005', 'AC003'),
      ('USR006', 'PRF006', 'AC006'),
      ('USR007', 'PRF007', 'AC008'),
      ('USR008', 'PRF008', 'AC007'),
      ('USR009', 'PRF009', 'AC005'),
      ('USR010', 'PRF010', 'AC002');
------------------------------------------------
--- LIBROS Y DESCARGAS DE USUARIOS
INSERT INTO descarga_usuario_libro (id_descarga, isbn, direccion_url, id_usuario, direccion, direccion_ip, fecha, hora)
VALUES 
  ('DL001', '978-0307476463', 'http://falselibros.com/libro1', 'USR001', 'Av. 123, Lima', '192.168.0.1', '2023-05-14', '10:00:00'),
  ('DL002', '978-8437620629', 'http://falselibros.com/libro2', 'USR002', 'Jr. 456, Lima', '192.168.0.2', '2023-05-14', '11:00:00'),
  ('DL003', '978-9507317181', 'http://falselibros.com/libro3', 'USR003', 'Calle 789, Lima', '192.168.0.3', '2023-05-14', '12:00:00'),
  ('DL004', '978-9875805174', 'http://falselibros.com/libro4', 'USR004', 'Av. 234, Lima', '192.168.0.4', '2023-05-14', '13:00:00'),
  ('DL005', '978-8420471839', 'http://falselibros.com/libro5', 'USR005', 'Jr. 567, Lima', '192.168.0.5', '2023-05-14', '14:00:00'),
  ('DL006', '978-8432248138', 'http://falselibros.com/libro6', 'USR006', 'Calle 890, Lima', '192.168.0.6', '2023-05-14', '15:00:00'),
  ('DL007', '978-8432212429', 'http://falselibros.com/libro7', 'USR007', 'Av. 901, Lima', '192.168.0.7', '2023-05-14', '16:00:00'),
  ('DL008', '978-8420441146', 'http://falselibros.com/libro8', 'USR008', 'Jr. 234, Lima', '192.168.0.8', '2023-05-14', '17:00:00'),
  ('DL009', '978-6071502919', 'http://falselibros.com/libro9', 'USR009', 'Calle 567, Lima', '192.168.0.9', '2023-05-14', '18:00:00'),
  ('DL010', '978-8433920228', 'http://falselibros.com/libro10', 'USR010', 'Av. 890, Lima', '192.168.0.10', '2023-05-14', '19:00:00');
------------------------------------------------
--- PRESTAMOS
INSERT INTO prestamo (nro_consecutivo_prestamo, id_usuario, id_empleado, fecha_realizacion)
VALUES ('PR001', 'USR001', 'EMP001', '2023-05-14 10:30:00'),
      ('PR002', 'USR002', 'EMP002', '2023-05-14 11:00:00'),
      ('PR003', 'USR003', 'EMP001', '2023-05-14 11:30:00'),
      ('PR004', 'USR004', 'EMP001', '2023-05-14 12:00:00'),
      ('PR005', 'USR005', 'EMP002', '2023-05-14 13:00:00'),
      ('PR006', 'USR006', 'EMP001', '2023-05-14 14:00:00'),
      ('PR007', 'USR007', 'EMP001', '2023-05-14 15:00:00'),
      ('PR008', 'USR008', 'EMP002', '2023-05-14 16:00:00'),
      ('PR009', 'USR009', 'EMP001', '2023-05-14 17:00:00'),
      ('PR010', 'USR010', 'EMP001', '2023-05-14 18:00:00');
------------------------------------------------
--- PRESTAMOS EJEMPLARES      
INSERT INTO prestamo_ejemplar (nro_consecutivo_prestamo, isbn, nro_ejemplar, fecha_devolucion)
VALUES ('PR001', '978-0307476463', 'EJ00001', '2023-05-16 10:30:00'),
      ('PR002', '978-8437620629', 'EJ00002', '2023-05-16 10:30:00'),
      ('PR003', '978-9507317181', 'EJ00003', '2023-05-17 11:00:00'),
      ('PR004', '978-9875805174', 'EJ00004', '2023-05-18 11:30:00'),
      ('PR005', '978-8420471839', 'EJ00005', '2023-05-18 11:30:00'),
      ('PR006', '978-8432248138', 'EJ00006', '2023-05-19 12:00:00'),
      ('PR007', '978-8432212429', 'EJ00007', '2023-05-20 13:00:00'),
      ('PR008', '978-8420441146', 'EJ00008', '2023-05-21 14:00:00'),
      ('PR009', '978-6071502919', 'EJ00009', '2023-05-22 15:00:00'),
      ('PR010', '978-8433920228', 'EJ00010', '2023-05-23 16:00:00');
------------------------------------------------
--- DEVOLUCIONES EJEMPLARES
INSERT INTO devuelve_usuario_ejemplar (id_devolucion, id_usuario, isbn, nro_ejemplar, fecha)
VALUES 
      ('DEV001', 'USR001', '978-0307476463', 'EJ00001', '2023-05-16 10:35:00'),
      ('DEV002', 'USR002', '978-8437620629', 'EJ00002', '2023-05-16 10:40:00'),
      ('DEV003', 'USR003', '978-9507317181', 'EJ00003', '2023-05-17 11:05:00'),
      ('DEV004', 'USR004', '978-9875805174', 'EJ00004', '2023-05-18 11:35:00'),
      ('DEV005', 'USR005', '978-8420471839', 'EJ00005', '2023-05-18 11:35:00'),
      ('DEV006', 'USR006', '978-8432248138', 'EJ00006', '2023-05-19 12:15:00'),
      ('DEV007', 'USR007', '978-8432212429', 'EJ00007', '2023-05-20 13:10:00'),
      ('DEV008', 'USR008', '978-8420441146', 'EJ00008', '2023-05-21 14:20:00'),
      ('DEV009', 'USR009', '978-6071502919', 'EJ00009', '2023-05-22 15:30:00'),
      ('DEV010', 'USR010', '978-8433920228', 'EJ00010', '2023-05-23 16:40:00');  
------------------------------------------------
--- SOLICITUDES  
INSERT INTO solicitud (nro_consecutivo_solicitud, id_usuario, id_empleado, isbn, titulo, descripcion, fecha)
VALUES
('1', 'USR001', 'EMP003', '978-3161484100', 'El Gran Gatsby', 'Solicitud de préstamo para el libro El Gran Gatsby', '2023-05-14 14:30:00'),
('2', 'USR002', 'EMP003', '978-8466331844', 'Harry Potter y la piedra filosofal', 'Solicitud de préstamo para el libro Harry Potter y la piedra filosofal', '2023-05-14 15:30:00'),
('3', 'USR003', 'EMP003', '978-0544272996', 'El hobbit', 'Solicitud de préstamo para el libro El hobbit', '2023-05-15 10:00:00'),
('4', 'USR004', 'EMP003', '978-6075277254', 'La insoportable levedad del ser', 'Solicitud de préstamo para el libro La insoportable levedad del ser', '2023-05-15 11:00:00'),
('5', 'USR005', 'EMP003', '978-0307278466', '1984', 'Solicitud de préstamo para el libro 1984', '2023-05-16 13:00:00'),
('6', 'USR006', 'EMP003', '978-0307476463', 'Cien años de soledad', 'Solicitud de préstamo para el libro Cien años de soledad', '2023-05-16 14:00:00'),
('7', 'USR007', 'EMP003', '978-8437604947', 'La ciudad y los perros', 'Solicitud de préstamo para el libro La ciudad y los perros', '2023-05-17 10:30:00'),
('8', 'USR008', 'EMP003', '978-8408163172', 'Origen', 'Solicitud de préstamo para el libro Origen', '2023-05-17 11:30:00'),
('9', 'USR009', 'EMP003', '978-8420407437', 'Crimen y castigo', 'Solicitud de préstamo para el libro Crimen y castigo', '2023-05-18 12:00:00'),
('10', 'USR010', 'EMP003', '978-8498383620', 'Los hombres que no amaban a las mujeres', 'Solicitud de préstamo para el libro Los hombres que no amaban a las mujeres', '2023-05-18 13:00:00');
------------------------------------------------
--- MULTAS
INSERT INTO multa (id_multa, id_usuario, fecha, valor, descripcion)
VALUES 
      ('M00001', 'USR001', '2023-05-16 10:35:00', 40000, 'Retraso en la devolución'),
      ('M00002', 'USR002', '2023-05-16 10:40:00', 40000, 'Retraso en la devolución'),
      ('M00003', 'USR003', '2023-05-17 11:05:00', 40000, 'Retraso en la devolución'),
      ('M00004', 'USR004', '2023-05-18 11:35:00', 40000, 'Retraso en la devolución'),
      ('M00005', 'USR005', '2023-05-18 11:35:00', 40000, 'Retraso en la devolución'),
      ('M00006', 'USR006', '2023-05-19 12:15:00', 40000, 'Retraso en la devolución'),
      ('M00007', 'USR007', '2023-05-20 13:10:00', 40000, 'Retraso en la devolución'),
      ('M00008', 'USR008', '2023-05-21 14:20:00', 40000, 'Retraso en la devolución'),
      ('M00009', 'USR009', '2023-05-22 15:30:00', 40000, 'Retraso en la devolución'),
      ('M00010', 'USR010', '2023-05-23 16:40:00', 40000, 'Retraso en la devolución');
