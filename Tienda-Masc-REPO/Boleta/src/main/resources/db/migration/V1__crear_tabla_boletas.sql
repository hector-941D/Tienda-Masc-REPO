CREATE TABLE metodos_pago (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo_pago VARCHAR(255) NOT NULL
);

CREATE TABLE metodos_envio (
    id_metodo_envio INT AUTO_INCREMENT PRIMARY KEY,
    tipo_envio VARCHAR(255) NOT NULL,
    costo_envio INT NOT NULL
);

CREATE TABLE boletas (
    id_boleta INT AUTO_INCREMENT PRIMARY KEY,
    numero_folio VARCHAR(50) NOT NULL UNIQUE,
    fecha_emision DATE NOT NULL,
    hora_emision TIME NOT NULL,
    monto_neto INT NOT NULL,
    monto_iva INT NOT NULL,
    monto_total INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    id_metodo_envio INT NOT NULL,
    id_cliente INT NOT NULL,
    id_producto INT NOT NULL,
    

    CONSTRAINT fk_boletas_metodo_pago FOREIGN KEY (id_metodo_pago) 
        REFERENCES metodos_pago (id_metodo_pago),
        
    CONSTRAINT fk_boletas_metodo_envio FOREIGN KEY (id_metodo_envio) 
        REFERENCES metodos_envio (id_metodo_envio)
);