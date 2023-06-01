CREATE TABLE IF NOT EXISTS topicos(
	id BIGINT AUTO_INCREMENT NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	mensaje VARCHAR(255) NOT NULL,
	fecha_creacion DATE NOT NULL,
	estatus_topico VARCHAR(25) NOT NULL,
	autor VARCHAR(50) NOT NULL,
	curso VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID),
	constraint unq_title_msj unique (titulo,mensaje)
);