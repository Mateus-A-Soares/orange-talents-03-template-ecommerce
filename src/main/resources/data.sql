INSERT INTO usuario (login, senha, cadastro) VALUES('default@email.com', '', '0000-01-01 00:00:00.00');
INSERT INTO categoria (nome)  VALUES ('categoria 1');
INSERT INTO produto (categoria_id, descricao, instante_cadastro, nome, quantidade, usuario_id, valor)
VALUES (1, 'Produto cadastrado na execução', '0000-01-01 00:00:00.00', 'Produto teste', 200, 1, 20.20);
insert into caracteristica (nome, produto_id, valor) values ('Característica 1', 1, 'Valor 1');
insert into caracteristica (nome, produto_id, valor) values ('Característica 2', 1, 'Valor 2');
insert into caracteristica (nome, produto_id, valor) values ('Característica 3', 1, 'Valor 3');