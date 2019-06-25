create procedure nome_funcionario(in funcionario_id int, out funcionario_nome varchar(255)) begin select nome into funcionario_nome from funcionario where id = funcionario_id; end

create procedure pesquisar_funcionarios(in termo_pesquisa varchar(30)) begin select id, nome, cpf from funcionario where upper(nome) like upper(concat('%', termo_pesquisa, '%')); end