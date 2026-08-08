CREATE TABLE IF NOT EXISTS usuario(
usr_id BIGSERIAL PRIMARY KEY,
usr_nome VARCHAR(100) NOT NULL,
usr_email VARCHAR(100) NOT NULL,
usr_senha VARCHAR(100) NOT NULL,
usr_telefone VARCHAR(100) NOT NULL,
usr_cpf VARCHAR(100) NOT NULL,
usr_ra VARCHAR(100) NOT NULL,
usr_nivel INTEGER NOT NULL,

usr_responsavel_id BIGINT,
CONSTRAINT fk_usuario_responsavel 
	FOREIGN KEY (usr_responsavel_id)
		REFERENCES usuario (usr_id)
);

CREATE TABLE IF NOT EXISTS paciente(
pac_id BIGSERIAL PRIMARY KEY,
pac_nome VARCHAR(100) NOT NULL,
pac_email VARCHAR(100) NOT NULL,
pac_data_nascimento DATE NOT NULL,
pac_telefone VARCHAR(100) NOT NULL,
pac_cpf VARCHAR(100) NOT NULL

);

CREATE TABLE IF NOT EXISTS ficha_avaliacao(
pre_id BIGSERIAL PRIMARY KEY,
pre_data_avaliacao TIMESTAMP NOT NULL,
usr_id BIGINT NOT NULL,
pac_id BIGINT NOT NULL,
CONSTRAINT fk_usuario_ficha_avaliacao
	FOREIGN KEY (usr_id)
		REFERENCES usuario (usr_id),
CONSTRAINT fk_paciente_ficha_avaliacao
	FOREIGN KEY (pac_id)
		REFERENCES paciente (pac_id)
);

CREATE TABLE IF NOT EXISTS metrica(
met_id BIGSERIAL PRIMARY KEY,
met_nome_teste VARCHAR(100) NOT NULL,
met_pontuacao INTEGER NOT NULL,
pre_id BIGINT NOT NULL,
CONSTRAINT fk_paciente_metrica
	FOREIGN KEY (pre_id)
		REFERENCES ficha_avaliacao (pre_id)

);
CREATE TABLE IF NOT EXISTS tipo_exercicio(
tpe_id BIGSERIAL PRIMARY KEY,
tpe_nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS exercicio(
exe_id BIGSERIAL PRIMARY KEY,
exe_nome VARCHAR(100) NOT NULL,
exe_descricao VARCHAR(200) NOT NULL,
exe_codigo_nome VARCHAR(100) NOT NULL,
tpe_id BIGINT NOT NULL,
CONSTRAINT fk_tipo_exercicio_exercicio
	FOREIGN KEY (tpe_id)
		REFERENCES tipo_exercicio (tpe_id)
);

CREATE TABLE IF NOT EXISTS sessao(
ses_id BIGSERIAL PRIMARY KEY,
ses_data_hora TIMESTAMP NOT NULL,
ses_ordem_atual INTEGER,
usr_id BIGINT NOT NULL,
pac_id BIGINT NOT NULL,
ses_status VARCHAR(100) NOT NULL,
CONSTRAINT fk_usuario_sessao
	FOREIGN KEY (usr_id)
		REFERENCES usuario (usr_id),
CONSTRAINT fk_paciente_sessao
	FOREIGN KEY (pac_id)
		REFERENCES paciente (pac_id)
);

CREATE TABLE IF NOT EXISTS sessao_fase(
sesf_id BIGSERIAL PRIMARY KEY,
sesf_ordem INTEGER NOT NULL,
ses_id BIGINT NOT NULL,
exe_id BIGINT NOT NULL,
CONSTRAINT fk_sessao_sessao_fase
	FOREIGN KEY (ses_id)
		REFERENCES sessao (ses_id),
CONSTRAINT fk_exercicio_sessao_fase
	FOREIGN KEY (exe_id)
		REFERENCES exercicio (exe_id)
);

CREATE TABLE IF NOT EXISTS resultado_sessao(
res_id BIGSERIAL PRIMARY KEY,
res_duracao INTEGER NOT NULL,
res_observacao VARCHAR(100),
ses_id BIGINT NOT NULL,
CONSTRAINT fk_sessao_resultado_sessao
	FOREIGN KEY (ses_id)
		REFERENCES sessao (ses_id)
);

CREATE TABLE IF NOT EXISTS aprovacao_sessao(
apr_id BIGSERIAL PRIMARY KEY,
apr_data_hora TIMESTAMP NOT NULL,
apr_motivo VARCHAR(100),
apr_status VARCHAR(100) NOT NULL,
ses_id BIGINT NOT NULL,
usr_id BIGINT NOT NULL,
CONSTRAINT fk_sessao_aprovacao_sessao
	FOREIGN KEY (ses_id)
		REFERENCES sessao (ses_id),
CONSTRAINT fk_usuario_aprovacao_sessao
	FOREIGN KEY (usr_id)
		REFERENCES usuario (usr_id)
);