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