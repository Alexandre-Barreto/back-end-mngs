DROP TABLE IF EXISTS mngs.usuario;
CREATE TABLE mngs.usuario (
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    nome VARCHAR(60) NOT NULL,
    administrador BIT NOT NULL,	
    
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mngs.pais;
CREATE TABLE mngs.pais (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    sigla VARCHAR(2) NOT NULL,
    gentilico VARCHAR(30) NOT NULL,
    
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mngs.token;
CREATE TABLE mngs.token (
    id INT NOT NULL AUTO_INCREMENT,
    token VARCHAR(255) NOT NULL,
    login VARCHAR(50) NOT NULL,
    expiracao TIMESTAMP NOT NULL,
    administrador BIT NOT NULL,	
    
    PRIMARY KEY (id)
);






