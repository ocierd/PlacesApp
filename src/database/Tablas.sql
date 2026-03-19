CREATE TABLE categoria (
    categoria_id tinyint IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(1000) NULL
);

CREATE TABLE usuario (
    usuario_id bigint IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
--    email VARCHAR(255) UNIQUE NOT NULL,
--    email_verificado bit DEFAULT 0,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) NULL,
    fecha_nacimiento date NOT NULL DEFAULT GETDATE(),
--    telefono VARCHAR(15) NULL,
--    telefono_verificado bit DEFAULT 0,
    registrado_en smalldatetime NOT NULL DEFAULT GETDATE()
);

-- ALTER TABLE usuario
-- ADD CONSTRAINT UQ_email UNIQUE (email);

-- Asegura que el nombre de usuario sea único en la tabla de usuarios
ALTER TABLE usuario
ADD CONSTRAINT usuario_username_uq UNIQUE (username);

CREATE TABLE dia (
    dia_id tinyint IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(15) NOT NULL
);

CREATE TABLE empresa (
    empresa_id int IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(1000) NOT NULL,
    categoria_id tinyint NOT NULL,
    CONSTRAINT empresa_categoria_fk FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);

CREATE TABLE ubicacion (
    ubicacion_id bigint IDENTITY PRIMARY KEY NOT NULL,
    latitud decimal(8,6) NOT NULL,
    longitud decimal(9,6) NOT NULL,
    enlace_maps varchar(4000) NULL
);


CREATE TABLE sucursal (
    sucursal_id bigint IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(1000) NOT NULL,
    creado_en smalldatetime NOT NULL DEFAULT GETDATE(),
    empresa_id int NOT NULL,
    ubicacion_id bigint NULL,
    CONSTRAINT sucursal_empresa_fk FOREIGN KEY (empresa_id) REFERENCES empresa(empresa_id),
    CONSTRAINT sucursal_ubicacion_fk FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(ubicacion_id)
);

CREATE TABLE horario (
    horario_id bigint IDENTITY PRIMARY KEY NOT NULL,
    hora_apertura time NOT NULL,
    hora_cierre time NOT NULL,
    dia_id tinyint NOT NULL,
    sucursal_id bigint NOT NULL,
    CONSTRAINT horario_dia_fk FOREIGN KEY (dia_id) REFERENCES dia(dia_id),
    CONSTRAINT horario_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)
);

-- Lugar que deseas visitar
CREATE TABLE visita (
    visita_id bigint IDENTITY PRIMARY KEY NOT NULL,
    visitado_en smalldatetime NOT NULL DEFAULT GETDATE(),
    visitado bit DEFAULT 0,
    confirmado bit DEFAULT 0,
    comentario TEXT NULL,
    calificacion tinyint NULL,
    usuario_id bigint NOT NULL,
    sucursal_id bigint NOT NULL,
    CONSTRAINT visita_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    CONSTRAINT visita_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)
);

CREATE TABLE recomendacion (
    recomendacion_id bigint IDENTITY PRIMARY KEY NOT NULL,
    aceptada bit NULL,
    recomendado_en smalldatetime NOT NULL DEFAULT GETDATE(),
    usuario_recomienda_id bigint NOT NULL,
    usuario_recomendado_id bigint NOT NULL,
    sucursal_id bigint NOT NULL,
    CONSTRAINT recomendacion_usuario_recomienda_fk FOREIGN KEY (usuario_recomienda_id) REFERENCES usuario(usuario_id),
    CONSTRAINT recomendacion_usuario_recomendado_fk FOREIGN KEY (usuario_recomendado_id) REFERENCES usuario(usuario_id),
    CONSTRAINT recomendacion_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)
);

-------------------------
CREATE TABLE calificacion (
    calificacion_id bigint IDENTITY PRIMARY KEY NOT NULL,
    puntaje tinyint NOT NULL,
    calificado_en smalldatetime NOT NULL DEFAULT GETDATE(),
    comentario TEXT NULL,
    usuario_id bigint NOT NULL,
    sucursal_id bigint NOT NULL,
    CONSTRAINT calificacion_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    CONSTRAINT calificacion_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id)
);

CREATE TABLE favorito (
    favorito_id bigint IDENTITY PRIMARY KEY NOT NULL,
    usuario_id bigint NOT NULL,
    sucursal_id bigint NOT NULL,
    CONSTRAINT favorito_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    CONSTRAINT favorito_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id),
    CONSTRAINT favorito_usuario_sucursal_uq UNIQUE (usuario_id, sucursal_id)
);

CREATE TABLE tipo_pago (
    tipo_pago_id tinyint IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE sucursal_tipo_pago (
    sucursal_tipo_pago_id bigint IDENTITY PRIMARY KEY NOT NULL,
    sucursal_id bigint NOT NULL,
    tipo_pago_id tinyint NOT NULL,
    CONSTRAINT sucursal_tipo_pago_sucursal_fk FOREIGN KEY (sucursal_id) REFERENCES sucursal(sucursal_id),
    CONSTRAINT sucursal_tipo_pago_tipo_pago_fk FOREIGN KEY (tipo_pago_id) REFERENCES tipo_pago(tipo_pago_id)
);

--
-- Teléfono depende de esta tabla
CREATE TABLE pais(
  pais_id TINYINT IDENTITY NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  nacionalidad VARCHAR(100) NULL,
  codigo VARCHAR(3) NOT NULL,
  iso_2 CHAR(2) NOT NULL,
  iso_3 CHAR(2) NOT NULL,
  CONSTRAINT  pais_pk PRIMARY KEY(pais_id)
);

CREATE TABLE telefono(
  telefono_id BIGINT IDENTITY NOT NULL,
  numero VARCHAR(15) NOT NULL,
  activo BIT NULL,
  pais_id TINYINT NOT NULL,
  CONSTRAINT telefono_pk PRIMARY KEY(telefono_id),
  CONSTRAINT telefono_pais_fk FOREIGN KEY(pais_id) REFERENCES pais(pais_id)
);

INSERT INTO telfono (numero, activo, pais_id) VALUES ('5512345678', 1, 1);


CREATE TABLE verificacion_telefono(
    verificacion_telefono_id bigint IDENTITY NOT NULL,
    codigo CHAR(6) NOT NULL,
    fecha_envio SMALLDATETIME NOT NULL DEFAULT GETDATE(), -- Fecha en que se crea registro
    fecha_expiracion SMALLDATETIME NOT NULL,
    fecha_confirmacion SMALLDATETIME NULL,
    telefono_id BIGINT NOT NULL,
    CONSTRAINT verificacion_telefono_pk PRIMARY KEY(verificacion_telefono_id),
    CONSTRAINT verificacion_telefono_telefono_fk FOREIGN KEY (telefono_id) REFERENCES telefono(telefono_id)
);


CREATE TABLE correo(
  correo_id BIGINT IDENTITY NOT NULL,
  correo_electronico VARCHAR(250) NOT NULL,
  activo BIT NULL,
  usuario_id BIGINT NOT NULL,
  CONSTRAINT correo_pk PRIMARY KEY(correo_id),
  CONSTRAINT correo_usuario_fk FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE verificacion_correo(
    verificacion_correo_id BIGINT IDENTITY NOT NULL,
    token uniqueidentifier NOT NULL DEFAULT NEWID(),
    fecha_envio SMALLDATETIME NOT NULL DEFAULT GETDATE(), -- Fecha en que se crea registro
    fecha_expiracion SMALLDATETIME NOT NULL,
    fecha_confirmacion SMALLDATETIME NULL,
    correo_id bigint NOT NULL,
    CONSTRAINT verificacion_correo_pk PRIMARY KEY(verificacion_correo_id),
    CONSTRAINT verificacion_correo_correo_fk FOREIGN KEY (correo_id) REFERENCES correo(correo_id),
    CONSTRAINT verificacion_correo_token_unique UNIQUE(token)
);
