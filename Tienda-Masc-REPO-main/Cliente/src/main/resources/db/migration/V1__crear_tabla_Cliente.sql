CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nuevocliente VARCHAR(20) NOT NULL,

    CONSTRAINT fk_cliente_region 
        FOREIGN KEY (id_region) REFERENCES REGION (id_region);
);

CREATE TABLE COMUNA(

    id_comuna INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comuna VARCHAR(50) NOT NULL,
    id_region INT NULL

);

CREATE TABLE COMUNA(

    id_region INT AUTO_INCREMENT PRIMARY KEY,
    region VARCHAR(50) NOT NULL,
    id_region INT NULL
    
);

INSERT INTO cliente (nombre, bando, midiclorianos) VALUES ('Grogu', 'Luminoso', 15000);
