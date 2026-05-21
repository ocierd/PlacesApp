-- Datos de ejemplo para la tabla "categoria"
SET IDENTITY_INSERT categoria ON; -- Permite insertar valores específicos en la columna de identidad
INSERT INTO categoria (categoria_id, nombre, descripcion) VALUES
(1, 'Restaurante', 'Establecimientos que ofrecen comida y bebida para consumo en el lugar.'),
(2, 'Cafetería', 'Lugares especializados en servir café y bebidas relacionadas, además de algunos alimentos ligeros.'),
(3, 'Bar', 'Establecimientos que se centran en la venta de bebidas alcohólicas y pueden ofrecer comida.'),
(4, 'Tienda de Comida Rápida', 'Locales que ofrecen alimentos preparados rápidamente para llevar o consumir en el lugar.'),
(5, 'Heladería', 'Negocios especializados en la venta de helados y postres fríos.'),
(6, 'Panadería', 'Establecimientos que producen y venden productos de panadería como pan, pasteles y galletas.'),
(7, 'Pizzería', 'Lugares que se especializan en la preparación y venta de pizzas.'),
(8, 'Comida Internacional', 'Restaurantes que ofrecen platos típicos de diferentes culturas y países.'),
(9, 'Comida Vegana/Vegetariana', 'Establecimientos que se enfocan en ofrecer opciones sin carne o productos animales.'),
(10, 'Food Truck', 'Vehículos móviles que venden comida preparada en la calle o en eventos especiales.'),
(11, 'Catering', 'Servicios que proporcionan comida para eventos y ocasiones especiales.'),
(12, 'Otros', 'Categoría general para establecimientos que no encajan en las categorías anteriores.'),
(13, 'Barbería', 'Establecimientos que ofrecen servicios de corte de cabello y cuidado personal.'),
(14, 'Salón de Belleza', 'Lugares que brindan servicios de belleza como peinados, maquillaje y tratamientos faciales.'),
(15, 'Gimnasio', 'Instalaciones dedicadas a la actividad física y el entrenamiento.'),
(16, 'Spa', 'Centros que ofrecen servicios de relajación y bienestar como masajes y tratamientos corporales.'),
(17, 'Tienda de Ropa', 'Negocios que venden prendas de vestir y accesorios.'),
(18, 'Librería', 'Establecimientos que venden libros y material de lectura.'),
(19, 'Cine', 'Salas de proyección para la exhibición de películas.'),
(20, 'Teatro', 'Espacios destinados a la representación de obras teatrales y eventos culturales.'),
(21, 'Museo', 'Instituciones que conservan y exhiben objetos de valor histórico, artístico o científico.'),
(22, 'Parque', 'Áreas verdes destinadas al esparcimiento y actividades recreativas.'),
(23, 'Gastronomía Local', 'Restaurantes que se especializan en platos típicos de la región o país.');
SET IDENTITY_INSERT categoria OFF; -- Desactiva la inserción de valores específicos en la columna de identidad



-- Datos de ejemplo para la tabla "dia"
SET IDENTITY_INSERT dia ON;
INSERT INTO dia (dia_id, nombre) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes'),
(6, 'Sábado'),
(7, 'Domingo');
SET IDENTITY_INSERT dia OFF;



-- Datos de ejemplo para la tabla "empresa"
SET IDENTITY_INSERT empresa ON;
INSERT INTO empresa (empresa_id, nombre, categoria_id) VALUES
(1, 'La Casa del Sabor', 1),
(2, 'Café Aroma', 2),
(3, 'Bar El Rincón', 3),
(4, 'Fast Food Express', 4),
(5, 'Helados del Sol', 5),
(6, 'Panadería La Espiga', 6),
(7, 'Pizzería La Rodaja', 7),
(8, 'Sabores del Mundo', 8),
(9, 'Veggie Delight', 9),
(10, 'Food Truck Sabroso', 10),
(11, 'Catering Eventos Especiales', 11),
(12, 'Otros Negocios', 12),
(13, 'Barbería Estilo', 13),
(14, 'Salón de Belleza Glamour', 14),
(15, 'Gimnasio FitLife', 15),
(16, 'Spa Relaxation', 16),
(17, 'Tienda de Ropa Fashion', 17),
(18, 'Librería El Saber', 18),
(19, 'Cine Estrella', 19),
(20, 'Teatro Central', 20),
(21, 'Museo de Arte Moderno', 21),
(22, 'Parque Natural Verde', 22),
(23, 'Gastronomía Local Tradicional', 23);
SET IDENTITY_INSERT empresa OFF;



-- Datos de ejemplo para la tabla "ubicacion"
SET IDENTITY_INSERT ubicacion ON;
INSERT INTO ubicacion (ubicacion_id, latitud, longitud, enlace_maps) VALUES
(1, 19.432608, -99.133209, 'https://maps.google.com/?q=19.432608,-99.133209'),
(2, 40.712776, -74.005974, 'https://maps.google.com/?q=40.712776,-74.005974'),
(3, 34.052235, -118.243683, 'https://maps.google.com/?q=34.052235,-118.243683'),
(4, 51.507351, -0.127758, 'https://maps.google.com/?q=51.507351,-0.127758'),
(5, 48.856613, 2.352222, 'https://maps.google.com/?q=48.856613,2.352222');
SET IDENTITY_INSERT ubicacion OFF;



-- Datos de ejemplo para la tabla "sucursal"
SET IDENTITY_INSERT sucursal ON;
INSERT INTO sucursal (sucursal_id, nombre, empresa_id, ubicacion_id) VALUES
(1, 'La Casa del Sabor - Sucursal Centro', 1, 1),
(2, 'Café Aroma - Sucursal Norte', 2, 2),
(3, 'Bar El Rincón - Sucursal Sur', 3, 3),
(4, 'Fast Food Express - Sucursal Este', 4, 4),
(5, 'Helados del Sol - Sucursal Oeste', 5, 5);
SET IDENTITY_INSERT sucursal OFF;



-- Datos de ejemplo para la tabla "horario"
SET IDENTITY_INSERT horario ON;
INSERT INTO horario (horario_id, hora_apertura, hora_cierre, dia_id, sucursal_id) VALUES
(1, '08:00:00', '22:00:00', 1, 1),
(2, '08:00:00', '22:00:00', 2, 1),
(3, '08:00:00', '22:00:00', 3, 1),
(4, '08:00:00', '22:00:00', 4, 1),
(5, '08:00:00', '22:00:00', 5, 1),
(6, '10:00:00', '20:00:00', 6, 1),
(7, '10:00:00', '20:00:00', 7, 1);
SET IDENTITY_INSERT horario OFF;



-- Datos de ejemplo para la tabla "tipo_pago"
SET IDENTITY_INSERT tipo_pago ON;
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (1, 'Efectivo');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (2, 'Tarjeta de crédito');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (3, 'Tarjeta de débito');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (4, 'Transferencia bancaria');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (5, 'Pago móvil');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (6, 'Cheque');
INSERT INTO tipo_pago (tipo_pago_id, nombre) VALUES (7, 'Vales de despensa');
SET IDENTITY_INSERT tipo_pago OFF;


-- Datos de catálogo para la tabla "modulo"
SET IDENTITY_INSERT modulo ON;
INSERT INTO modulo (modulo_id, nombre, descripcion, ruta) VALUES
(1, 'Principal', 'Módulo principal del sistema', '/main'),
(2, 'Administrador', 'Gestión de administración', '/admin'),
(3, 'Usuario administrador', 'Gestión de usuarios administradores', '/user-admin');
SET IDENTITY_INSERT modulo OFF;



-- Datos de catálogo para la tabla menu
SET IDENTITY_INSERT menu ON;
INSERT INTO menu (menu_id, nombre, ruta, icono, padre_menu_id, modulo_id) VALUES
(1,'Inicio', '/home', 'home', NULL, 1),
(2,'Overview','/overview','workspaces',1, NULL),
(3,'Dashboard','/dashboard','dashboard',1, NULL),
(4,'Visitas','/visitas','visibility',NULL, 1),
(5,'Pendientes','/lista-visitas','pending_actions',4, NULL),
(6,'Completadas','/completadas','check_circle',4, NULL),

(7,'Gestión de Usuarios','/gestion-usuarios','manage_accounts',NULL, 2),
(8,'Usuarios','/usuarios','demography',7, NULL),
(9,'Editar usuario','/editar-usuario','user_attributes',7, NULL),

(10,'Gestión de módulos','/gestion-modulos','bookmark_stacks',NULL, 2),
(11,'Gestión de menús','/gestion-menus','menu',NULL, 2),
(12,'Roles y Permisos','/roles-permisos','security',NULL, 2),
(13,'Configuración del Sistema','/configuracion-sistema','settings',NULL, 2);
SET IDENTITY_INSERT menu OFF;


-- Datos de catálogo para la tabla rol
SET IDENTITY_INSERT rol ON;
INSERT INTO rol (rol_id, nombre) VALUES
(1,'Administrador'),
(2,'Usuario Administrativo'),
(3,'Usuario Regular'),
(4,'Invitado');
SET IDENTITY_INSERT rol OFF;


-- Datos de catálogo para la tabla rol_menu
SET IDENTITY_INSERT rol_menu ON;
INSERT INTO rol_menu (rol_menu_id, rol_id, menu_id) VALUES
(1, 1, 1), -- Administrador tiene acceso a Inicio
(2, 1, 2), -- Administrador tiene acceso a Overview
(3, 1, 3), -- Administrador tiene acceso a Dashboard
(4, 1, 4), -- Administrador tiene acceso a Visitas
(5, 1, 5), -- Administrador tiene acceso a Pendientes
(6, 1, 6), -- Administrador tiene acceso a Completadas
(7, 2, 1), -- Usuario Administrativo tiene acceso a Inicio
(8, 2, 2), -- Usuario Administrativo tiene acceso a Overview
(9, 2, 3), -- Usuario Administrativo tiene acceso a Dashboard
(10, 2, 4), -- Usuario Administrativo tiene acceso a Visitas
(11, 2, 5), -- Usuario Administrativo tiene acceso a Pendientes
(12, 3, 1), -- Usuario Regular tiene acceso a Inicio
(13, 3, 4), -- Usuario Regular tiene acceso a Visitas
(14, 3, 5), -- Usuario Regular tiene acceso a Pendientes
(15, 3, 6), -- Usuario Regular tiene acceso a Completadas
(16, 4, 1), -- Invitado tiene acceso a Inicio

(17, 1, 7), -- Administrador tiene acceso a Gestión de Usuarios
(18, 1, 8), -- Administrador tiene acceso a Usuarios
(19, 1, 9); -- Administrador tiene acceso a Editar usuario
SET IDENTITY_INSERT rol_menu OFF;

