CREATE TABLE categoria (
    categoria_id tinyint IDENTITY PRIMARY KEY NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(1000) NULL
);

CREATE TABLE usuario (
    usuario_id bigint IDENTITY PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    email_verificado bit DEFAULT 0,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) NULL,
    fecha_nacimiento date NOT NULL DEFAULT GETDATE(),
    telefono VARCHAR(15) NULL,
    telefono_verificado bit DEFAULT 0,
    registrado_en smalldatetime NOT NULL DEFAULT GETDATE()
);

ALTER TABLE usuario
ADD CONSTRAINT UQ_email UNIQUE (email);

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
    CONSTRAINT favorito_usuario_sucural_uq UNIQUE (usuario_id, sucursal_id)
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