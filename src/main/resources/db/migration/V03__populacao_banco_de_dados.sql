INSERT INTO tb_categoria(tecnologia, nome) VALUES ("Java", "Turma 1 - 2022");
INSERT INTO tb_categoria(tecnologia, nome) VALUES ("C#", "Turma 2 - 2022");
INSERT INTO tb_categoria(tecnologia, nome) VALUES ("Cobol", "Turma 3 - 2022");

INSERT INTO tb_starter(cpf, nome, quatro_letras, email, categoria_id) VALUES ("94059820016", "Ubiratan", "ubir", "ubir@gft.com", 1);
INSERT INTO tb_starter(cpf, nome, quatro_letras, email, categoria_id) VALUES ("54689112053", "Clécio", "clec", "clec@gft.com", 2);
INSERT INTO tb_starter(cpf, nome, quatro_letras, email, categoria_id) VALUES ("08201255046", "Bianchi", "bian", "bian@gft.com", 3);

INSERT INTO tb_usuario(usuario, senha, email) VALUES ("Admin", "$2a$10$zM89QbBICrmxh5inDS7Un.WdbOlrR1vcQtiODMUj/qQEgaREHjvBK", "admin@teste.com");
INSERT INTO tb_usuario(usuario, senha, email) VALUES ("User", "$2a$10$zM89QbBICrmxh5inDS7Un.WdbOlrR1vcQtiODMUj/qQEgaREHjvBK", "user@teste.com");

INSERT INTO tb_role(role) VALUES ("ROLE_ADMIN");
INSERT INTO tb_role(role) VALUES ("ROLE_USER");

INSERT INTO tb_user_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role(usuario_id, role_id) VALUES (2, 2);