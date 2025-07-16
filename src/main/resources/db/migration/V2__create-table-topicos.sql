CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    curso VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
