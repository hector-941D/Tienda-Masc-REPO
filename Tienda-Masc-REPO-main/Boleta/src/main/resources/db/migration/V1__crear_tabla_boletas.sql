CREATE TABLE metodos_pago (
    id_metodo_pago INT AUTO_INCREMENT NOT NULL,
    nombre_metodo_pago VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_metodo_pago)
);

CREATE TABLE metodos_envio (
    id_metodo_envio INT AUTO_INCREMENT NOT NULL,
    tipo_envio VARCHAR(255) NOT NULL,
    costo_envio INT NOT NULL,
    PRIMARY KEY (id_metodo_envio)
);

CREATE TABLE boletas (
    id_boleta INT AUTO_INCREMENT NOT NULL,
    numero_folio VARCHAR(255) NOT NULL,
    fecha_emision DATE NOT NULL,
    hora_emision TIME NOT NULL,
    monto_neto INT NOT NULL,
    monto_iva INT NOT NULL,
    monto_total INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    id_metodo_envio INT NOT NULL,
    id_cliente INT NULL,
    id_producto INT NULL,
    
    PRIMARY KEY (id_boleta),
    UNIQUE (numero_folio),
    CONSTRAINT fk_boletas_metodo_pago 
        FOREIGN KEY (id_metodo_pago) REFERENCES metodos_pago(id_metodo_pago),
    CONSTRAINT fk_boletas_metodo_envio 
        FOREIGN KEY (id_metodo_envio) REFERENCES metodos_envio(id_metodo_envio)
);

INSERT INTO metodos_pago (nombre_metodo_pago) VALUES 
('Efectivo'), 
('Tarjeta de Crédito'), 
('Transferencia');

INSERT INTO metodos_envio (tipo_envio, costo_envio) VALUES 
('Retiro en Tienda', 0), 
('Envío a Domicilio Express', 4500), 
('Envío Estándar', 2990);