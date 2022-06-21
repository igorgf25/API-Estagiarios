CREATE TABLE tb_categoria(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tecnologia VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_starter(
    cpf VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quatro_letras VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    categoria_id BIGINT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES tb_categoria(id)
);