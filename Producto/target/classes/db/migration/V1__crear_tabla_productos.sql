CREATE TABLE tipo(
    id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(30) NOT NULL
);

CREATE TABLE marca(
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre_marca VARCHAR(30) NOT NULL
);

CREATE TABLE especie(
    id_especies INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especie VARCHAR(70) NOT NULL
);

CREATE TABLE producto(
    id_productos INT AUTO_INCREMENT PRIMARY KEY,
    nombre_productos VARCHAR(30) NOT NULL,
    precio INT NOT NULL,

    tipo_id INT NOT NULL,
    marca_id INT NOT NULL,
    especie_id INT NOT NULL,

    CONSTRAINT fk_producto_tipo
        FOREIGN KEY(tipo_id)
        REFERENCES tipo(id_tipo),

    CONSTRAINT fk_producto_marca
        FOREIGN KEY(marca_id)
        REFERENCES marca(id_marca),

    CONSTRAINT fk_producto_especie
        FOREIGN KEY(especie_id)
        REFERENCES especie(id_especies)
);