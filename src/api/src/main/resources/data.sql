-- Datos de ejemplo para la tabla "categoria"
 -- Permite insertar valores específicos en la columna de identidad
INSERT INTO CATEGORIA (categoria_id, nombre, descripcion) VALUES
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
 -- Desactiva la inserción de valores específicos en la columna de identidad



-- Datos de ejemplo para la tabla "dia"

INSERT INTO DIA (dia_id, nombre) VALUES
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes'),
(6, 'Sábado'),
(7, 'Domingo');




-- Datos de ejemplo para la tabla "empresa"

INSERT INTO EMPRESA (empresa_id, nombre, categoria_id) VALUES
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




-- Datos de ejemplo para la tabla "UBICACION"

INSERT INTO UBICACION (ubicacion_id, latitud, longitud, enlace_maps) VALUES
(1, 19.432608, -99.133209, 'https://maps.google.com/?q=19.432608,-99.133209'),
(2, 40.712776, -74.005974, 'https://maps.google.com/?q=40.712776,-74.005974'),
(3, 34.052235, -118.243683, 'https://maps.google.com/?q=34.052235,-118.243683'),
(4, 51.507351, -0.127758, 'https://maps.google.com/?q=51.507351,-0.127758'),
(5, 48.856613, 2.352222, 'https://maps.google.com/?q=48.856613,2.352222');




-- Datos de ejemplo para la tabla "sucursal"

INSERT INTO SUCURSAL (sucursal_id, nombre, empresa_id, ubicacion_id) VALUES
(1, 'La Casa del Sabor - Sucursal Centro', 1, 1),
(2, 'Café Aroma - Sucursal Norte', 2, 2),
(3, 'Bar El Rincón - Sucursal Sur', 3, 3),
(4, 'Fast Food Express - Sucursal Este', 4, 4),
(5, 'Helados del Sol - Sucursal Oeste', 5, 5);




-- Datos de ejemplo para la tabla "horario"

INSERT INTO HORARIO (horario_id, hora_apertura, hora_cierre, dia_id, sucursal_id) VALUES
(1, '08:00:00', '22:00:00', 1, 1),
(2, '08:00:00', '22:00:00', 2, 1),
(3, '08:00:00', '22:00:00', 3, 1),
(4, '08:00:00', '22:00:00', 4, 1),
(5, '08:00:00', '22:00:00', 5, 1),
(6, '10:00:00', '20:00:00', 6, 1),
(7, '10:00:00', '20:00:00', 7, 1);




-- Datos de ejemplo para la tabla "tipo_pago"

INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (1, 'Efectivo');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (2, 'Tarjeta de crédito');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (3, 'Tarjeta de débito');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (4, 'Transferencia bancaria');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (5, 'Pago móvil');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (6, 'Cheque');
INSERT INTO TIPO_PAGO (tipo_pago_id, nombre) VALUES (7, 'Vales de despensa');






INSERT INTO PAIS (pais_id, nombre, nacionalidad, codigo, iso_2, iso_3)
VALUES
(1, 'Afganistán', 'Afgano/a', '93', 'AF', 'AFG'),
(2, 'Albania', NULL, '355', 'AL', 'ALB'),
(3, 'Alemania', 'Alemán/a', '49', 'DE', 'DEU'),
(4, 'Andorra', NULL, '376', 'AD', 'AND'),
(5, 'Angola', NULL, '244', 'AO', 'AGO'),
(6, 'Antigua y Barbuda', NULL, '1', 'AG', 'ATG'),
(7, 'Arabia Saudita', NULL, '966', 'SA', 'SAU'),
(8, 'Argelia', 'Argelino/a', '213', 'DZ', 'DZA'),
(9, 'Argentina', 'Argentino/a', '54', 'AR', 'ARG'),
(10, 'Armenia', NULL, '374', 'AM', 'ARM'),
(11, 'Australia', 'Australiano/a', '61', 'AU', 'AUS'),
(12, 'Austria', 'Austriaco/a', '43', 'AT', 'AUT'),
(13, 'Azerbaiyán', NULL, '994', 'AZ', 'AZE'),
(14, 'Bahamas', NULL, '1', 'BS', 'BHS'),
(15, 'Bangladés', NULL, '880', 'BD', 'BGD'),
(16, 'Barbados', NULL, '1', 'BB', 'BRB'),
(17, 'Baréin', NULL, '973', 'BH', 'BHR'),
(18, 'Bélgica', 'Belga', '32', 'BE', 'BEL'),
(19, 'Belice', NULL, '501', 'BZ', 'BLZ'),
(20, 'Benín', NULL, '229', 'BJ', 'BEN'),
(21, 'Bielorrusia', NULL, '375', 'BY', 'BLR'),
(22, 'Birmania', NULL, '95', 'MM', 'MMR'),
(23, 'Bolivia', 'Boliviano/a', '591', 'BO', 'BOL'),
(24, 'Bosnia y Herzegovina', NULL, '387', 'BA', 'BIH'),
(25, 'Botsuana', NULL, '267', 'BW', 'BWA'),
(26, 'Brasil', 'Brasileño/a', '55', 'BR', 'BRA'),
(27, 'Brunéi', NULL, '673', 'BN', 'BRN'),
(28, 'Bulgaria', NULL, '359', 'BG', 'BGR'),
(29, 'Burkina Faso', NULL, '226', 'BF', 'BFA'),
(30, 'Burundi', NULL, '257', 'BI', 'BDI'),
(31, 'Bután', NULL, '975', 'BT', 'BTN'),
(32, 'Cabo Verde', NULL, '238', 'CV', 'CPV'),
(33, 'Camboya', NULL, '855', 'KH', 'KHM'),
(34, 'Camerún', 'Camerunés/a', '237', 'CM', 'CMR'),
(35, 'Canadá', 'Canadiense', '1', 'CA', 'CAN'),
(36, 'Catar', NULL, '974', 'QA', 'QAT'),
(37, 'Chad', NULL, '235', 'TD', 'TCD'),
(38, 'Chile', NULL, '56', 'CL', 'CHL'),
(39, 'China', 'Chino/a', '86', 'CN', 'CHN'),
(40, 'Chipre', NULL, '357', 'CY', 'CYP'),
(41, 'Ciudad del Vaticano', NULL, '39', 'VA', 'VAT'),
(42, 'Colombia', 'Colombiano/a', '57', 'CO', 'COL'),
(43, 'Comoras', NULL, '269', 'KM', 'COM'),
(44, 'Corea del Norte', NULL, '850', 'KP', 'PRK'),
(45, 'Corea del Sur', 'Surcoreano/a', '82', 'KR', 'KOR'),
(46, 'Costa de Marfil', NULL, '225', 'CI', 'CIV'),
(47, 'Costa Rica', 'Costarricense', '506', 'CR', 'CRI'),
(48, 'Croacia', 'Croata', '385', 'HR', 'HRV'),
(49, 'Cuba', 'Cubano/a', '53', 'CU', 'CUB'),
(50, 'Dinamarca', 'Danés/a', '45', 'DK', 'DNK'),
(51, 'Dominica', NULL, '1', 'DM', 'DMA'),
(52, 'Ecuador', 'Ecuatoriano/a', '593', 'EC', 'ECU'),
(53, 'Egipto', 'Egipcio/a', '20', 'EG', 'EGY'),
(54, 'El Salvador', 'Salvadoreño/a', '503', 'SV', 'SLV'),
(55, 'Emiratos Árabes Unidos', NULL, '971', 'AE', 'ARE'),
(56, 'Eritrea', NULL, '291', 'ER', 'ERI'),
(57, 'Eslovaquia', 'Eslovaco/a', '421', 'SK', 'SVK'),
(58, 'Eslovenia', 'Esloveno/a', '386', 'SI', 'SVN'),
(59, 'España', 'Español/a', '34', 'ES', 'ESP'),
(60, 'Estados Unidos', 'Estadounidense', '1', 'US', 'USA'),
(61, 'Estonia', NULL, '372', 'EE', 'EST'),
(62, 'Etiopía', 'Etíope', '251', 'ET', 'ETH'),
(63, 'Filipinas', 'Filipino/a', '63', 'PH', 'PHL'),
(64, 'Finlandia', NULL, '358', 'FI', 'FIN'),
(65, 'Fiyi', NULL, '679', 'FJ', 'FJI'),
(66, 'Francia', 'Francés/a', '33', 'FR', 'FRA'),
(67, 'Gabón', NULL, '241', 'GA', 'GAB'),
(68, 'Gambia', NULL, '220', 'GM', 'GMB'),
(69, 'Georgia', NULL, '995', 'GE', 'GEO'),
(70, 'Ghana', NULL, '233', 'GH', 'GHA'),
(71, 'Granada', NULL, '1', 'GD', 'GRD'),
(72, 'Grecia', 'Griego/a', '30', 'GR', 'GRC'),
(73, 'Guatemala', NULL, '502', 'GT', 'GTM'),
(74, 'Guyana', 'Guyanés/a', '592', 'GY', 'GUY'),
(75, 'Guinea', NULL, '224', 'GN', 'GIN'),
(76, 'Guinea ecuatorial', NULL, '240', 'GQ', 'GNQ'),
(77, 'Guinea-Bisáu', NULL, '245', 'GW', 'GNB'),
(78, 'Haití', 'Haitiano/a', '509', 'HT', 'HTI'),
(79, 'Honduras', 'Hondureño/a', '504', 'HN', 'HND'),
(80, 'Hungría', 'Húngaro/a', '36', 'HU', 'HUN'),
(81, 'India', 'Indio/a', '91', 'IN', 'IND'),
(82, 'Indonesia', NULL, '62', 'ID', 'IDN'),
(83, 'Irak', 'Irakí', '964', 'IQ', 'IRQ'),
(84, 'Irán', 'Iraní', '98', 'IR', 'IRN'),
(85, 'Irlanda', 'Irlandés/a', '353', 'IE', 'IRL'),
(86, 'Islandia', 'Islandés/a', '354', 'IS', 'ISL'),
(87, 'Islas Marshall', NULL, '692', 'MH', 'MHL'),
(88, 'Islas Salomón', NULL, '677', 'SB', 'SLB'),
(89, 'Israel', 'Israelita', '972', 'IL', 'ISR'),
(90, 'Italia', 'Italiano/a', '39', 'IT', 'ITA'),
(91, 'Jamaica', 'Jamaicano/a', '1', 'JM', 'JAM'),
(92, 'Japón', 'Japonés/a', '81', 'JP', 'JPN'),
(93, 'Jordania', NULL, '962', 'JO', 'JOR'),
(94, 'Kazajistán', NULL, '7', 'KZ', 'KAZ'),
(95, 'Kenia', 'Keniata', '254', 'KE', 'KEN'),
(96, 'Kirguistán', NULL, '996', 'KG', 'KGZ'),
(97, 'Kiribati', NULL, '686', 'KI', 'KIR'),
(98, 'Kuwait', NULL, '965', 'KW', 'KWT'),
(99, 'Laos', NULL, '856', 'LA', 'LAO'),
(100, 'Lesoto', NULL, '266', 'LS', 'LSO'),
(101, 'Letonia', 'Letonio/a', '371', 'LV', 'LVA'),
(102, 'Líbano', 'Libanés/a', '961', 'LB', 'LBN'),
(103, 'Liberia', NULL, '231', 'LR', 'LBR'),
(104, 'Libia', NULL, '218', 'LY', 'LBY'),
(105, 'Liechtenstein', NULL, '423', 'LI', 'LIE'),
(106, 'Lituania', 'Lituano/a', '370', 'LT', 'LTU'),
(107, 'Luxemburgo', NULL, '352', 'LU', 'LUX'),
(108, 'Macedonia del Norte', NULL, '389', 'MK', 'MKD'),
(109, 'Madagascar', NULL, '261', 'MG', 'MDG'),
(110, 'Malasia', NULL, '60', 'MY', 'MYS'),
(111, 'Malaui', NULL, '265', 'MW', 'MWI'),
(112, 'Maldivas', NULL, '960', 'MV', 'MDV'),
(113, 'Malta', 'Maltés/a', '356', 'MT', 'MLT'),
(114, 'Marruecos', 'Marroquí', '212', 'MA', 'MAR'),
(115, 'Mauricio', NULL, '230', 'MU', 'MUS'),
(116, 'Mauritania', NULL, '222', 'MR', 'MRT'),
(117, 'México', 'Mexicano/a', '52', 'MX', 'MEX'),
(118, 'Micronesia', NULL, '691', 'FM', 'FSM'),
(119, 'Moldavia', 'Moldavo/a', '373', 'MD', 'MDA'),
(120, 'Mónaco', 'Mónaco/a, monaqués', '377', 'MC', 'MCO'),
(121, 'Mongolia', 'Mongol/a', '976', 'MN', 'MNG'),
(122, 'Montenegro', NULL, '382', 'ME', 'MNE'),
(123, 'Mozambique', NULL, '258', 'MZ', 'MOZ'),
(124, 'Namibia', NULL, '264', 'NA', 'NAM'),
(125, 'Nauru', NULL, '674', 'NR', 'NRU'),
(126, 'Nepal', 'Nepalí', '977', 'NP', 'NPL'),
(127, 'Nicaragua', 'Nicaraguense', '505', 'NI', 'NIC'),
(128, 'Níger', NULL, '227', 'NE', 'NER'),
(129, 'Nigeria', 'Nigeriano/a', '234', 'NG', 'NGA'),
(130, 'Noruega', 'Noruego/a', '47', 'NO', 'NOR'),
(131, 'Nueva Zelanda', NULL, '64', 'NZ', 'NZL'),
(132, 'Omán', NULL, '968', 'OM', 'OMN'),
(133, 'Países Bajos', 'Neerlandés/a', '31', 'NL', 'NLD'),
(134, 'Pakistán', NULL, '92', 'PK', 'PAK'),
(135, 'Palaos', NULL, '680', 'PW', 'PLW'),
(136, 'Panamá', 'Panameño/a', '507', 'PA', 'PAN'),
(137, 'Papúa Nueva Guinea', NULL, '675', 'PG', 'PNG'),
(138, 'Paraguay', 'Paraguayo/a', '595', 'PY', 'PRY'),
(139, 'Perú', 'Peruano/a', '51', 'PE', 'PER'),
(140, 'Polonia', NULL, '48', 'PL', 'POL'),
(141, 'Portugal', 'Portugués/a', '351', 'PT', 'PRT'),
(142, 'Reino Unido', 'Británico/a', '44', 'GB', 'GBR'),
(143, 'República Centroafricana', NULL, '236', 'CF', 'CAF'),
(144, 'República Checa', 'Checo/a', '420', 'CZ', 'CZE'),
(145, 'República del Congo', NULL, '242', 'CG', 'COG'),
(146, 'República Democrática del Congo', NULL, '243', 'CD', 'COD'),
(147, 'República Dominicana', NULL, '1', 'DO', 'DOM'),
(148, 'República Sudafricana', NULL, '27', 'ZA', 'ZAF'),
(149, 'Ruanda', NULL, '250', 'RW', 'RWA'),
(150, 'Rumanía', NULL, '40', 'RO', 'ROU'),
(151, 'Rusia', 'Ruso/a', '7', 'RU', 'RUS'),
(152, 'Samoa', NULL, '685', 'WS', 'WSM'),
(153, 'San Cristóbal y Nieves', NULL, '1', 'KN', 'KNA'),
(154, 'San Marino', NULL, '378', 'SM', 'SMR'),
(155, 'San Vicente y las Granadinas', NULL, '1', 'VC', 'VCT'),
(156, 'Santa Lucía', NULL, '1', 'LC', 'LCA'),
(157, 'Santo Tomé y Príncipe', NULL, '239', 'ST', 'STP'),
(158, 'Senegal', NULL, '221', 'SN', 'SEN'),
(159, 'Serbia', 'Serbio/a', '381', 'RS', 'SRB'),
(160, 'Seychelles', NULL, '248', 'SC', 'SYC'),
(161, 'Sierra Leona', NULL, '232', 'SL', 'SLE'),
(162, 'Singapur', 'Singapurense', '65', 'SG', 'SGP'),
(163, 'Siria', 'Sirio/a', '963', 'SY', 'SYR'),
(164, 'Somalia', NULL, '252', 'SO', 'SOM'),
(165, 'Sri Lanka', NULL, '94', 'LK', 'LKA'),
(166, 'Suazilandia', NULL, '268', 'SZ', 'SWZ'),
(167, 'Sudán', NULL, '249', 'SD', 'SDN'),
(168, 'Sudán del Sur', NULL, '211', 'SS', 'SSD'),
(169, 'Suecia', 'Sueco/a', '46', 'SE', 'SWE'),
(170, 'Suiza', 'Sueco/a', '41', 'CH', 'CHE'),
(171, 'Surinam', 'Surinamés/a', '597', 'SR', 'SUR'),
(172, 'Tailandia', 'Tailandés/a', '66', 'TH', 'THA'),
(173, 'Tanzania', NULL, '255', 'TZ', 'TZA'),
(174, 'Tayikistán', NULL, '992', 'TJ', 'TJK'),
(175, 'Timor Oriental', NULL, '670', 'TL', 'TLS'),
(176, 'Togo', NULL, '228', 'TG', 'TGO'),
(177, 'Tonga', NULL, '676', 'TO', 'TON'),
(178, 'Trinidad y Tobago', NULL, '1', 'TT', 'TTO'),
(179, 'Túnez', NULL, '216', 'TN', 'TUN'),
(180, 'Turkmenistán', NULL, '993', 'TM', 'TKM'),
(181, 'Turquía', 'Turco/a', '90', 'TR', 'TUR'),
(182, 'Tuvalu', NULL, '688', 'TV', 'TUV'),
(183, 'Ucrania', 'Ucraniano/a', '380', 'UA', 'UKR'),
(184, 'Uganda', NULL, '256', 'UG', 'UGA'),
(185, 'Uruguay', 'Uruguayo/a', '598', 'UY', 'URY'),
(186, 'Uzbekistán', NULL, '998', 'UZ', 'UZB'),
(187, 'Vanuatu', NULL, '678', 'VU', 'VUT'),
(188, 'Venezuela', 'Venezolano/a', '58', 'VE', 'VEN'),
(189, 'Vietnam', 'Vietnamita', '84', 'VN', 'VNM'),
(190, 'Yemen', 'Yemení', '967', 'YE', 'YEM'),
(191, 'Yibuti', NULL, '253', 'DJ', 'DJI'),
(192, 'Zambia', NULL, '260', 'ZM', 'ZMB'),
(193, 'Zimbabue', 'Zimbabuense/a', '263', 'ZW', 'ZWE');




-- Datos de usuarios de ejemplo

INSERT INTO USUARIO (usuario_id, username, password, nombre, apellido_paterno, apellido_materno, fecha_nacimiento) 
VALUES
(1, 'karen', '$2a$10$b1EGxVNXulrcy88j5tNkveGIPLIXeL.wi4eg.ws85vWykNP.AexjC', 'Karen', 'González', 'González', '1990-07-07'),
(2, 'bri', '$2a$10$b1EGxVNXulrcy88j5tNkveGIPLIXeL.wi4eg.ws85vWykNP.AexjC', 'Brigitte', 'Papanú', 'Ferrer', '1985-05-15'),
(3, 'fer', '$2a$10$b1EGxVNXulrcy88j5tNkveGIPLIXeL.wi4eg.ws85vWykNP.AexjC', 'El', 'Fer', 'Ricardo', '1992-09-30');
