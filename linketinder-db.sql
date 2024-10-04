--DDL (DATA DEFINITION LANGUAGE) 

CREATE TABLE IF NOT EXISTS candidatos
(
    id SERIAL PRIMARY KEY,
    nome CHARACTER VARYING(100) NOT NULL,
    sobrenome CHARACTER VARYING(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    email CHARACTER VARYING(150) NOT NULL,
    telefone CHARACTER VARYING(20) NOT NULL,
    linkedin CHARACTER VARYING(150) NOT NULL,
    cpf CHARACTER VARYING(14) NOT NULL,
    estado CHARACTER VARYING(2) NOT NULL,
    cep CHARACTER VARYING(10) NOT NULL,
    descricao CHARACTER VARYING(200),
    formacao CHARACTER VARYING(50) NOT NULL,
    senha CHARACTER VARYING(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS empresas
(
    id SERIAL PRIMARY KEY,
    nome CHARACTER VARYING(100) NOT NULL,
    cnpj CHARACTER VARYING(18) NOT NULL,
    email CHARACTER VARYING(150) NOT NULL,
    pais CHARACTER VARYING(100) NOT NULL,
    estado CHARACTER VARYING(2) NOT NULL,
    cep CHARACTER VARYING(10) NOT NULL,
    descricao CHARACTER VARYING(200),
    senha character varying(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vagas
(
    id SERIAL PRIMARY KEY,
    nome CHARACTER VARYING(100) NOT NULL,
    descricao CHARACTER VARYING(200),
    local CHARACTER VARYING(100) NOT NULL,
    empresa_id INTEGER NOT NULL REFERENCES empresas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS competencias
(
    id serial PRIMARY KEY,
    nome CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS candidato_competencias
(
    id serial PRIMARY KEY,
    candidato_id integer REFERENCES candidatos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    competencia_id integer REFERENCES competencias(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS vaga_competencia
(
    id serial PRIMARY KEY,
    vaga_id integer REFERENCES vagas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    competencia_id integer REFERENCES competencias(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS curtida_candidato_vagas
(
    candidato_id integer REFERENCES candidatos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    vaga_id integer REFERENCES vagas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    data_curtida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(candidato_id, vaga_id)
);

CREATE TABLE IF NOT EXISTS curtida_empresa_candidato (
  id SERIAL PRIMARY KEY,
  empresa_id INTEGER REFERENCES empresas(id) ON DELETE CASCADE ON UPDATE CASCADE,
  candidato_id INTEGER REFERENCES candidatos(id) ON DELETE CASCADE ON UPDATE CASCADE,  
  data_curtida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS matches (
  id SERIAL PRIMARY KEY,
  candidato_id INTEGER REFERENCES candidatos(id) ON DELETE CASCADE ON UPDATE CASCADE,
  empresa_id INTEGER REFERENCES empresas(id) ON DELETE CASCADE ON UPDATE CASCADE,
  vaga_id INTEGER REFERENCES vagas(id) ON DELETE CASCADE ON UPDATE CASCADE,
  data_match TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--DML (DATA MANIPULATION LANGUAGE) 

-- Inserir competências predefinidas 
INSERT INTO Competencias (nome) VALUES ('Python'),('Java'),('Groovy');

-- Inserindo candidatos
INSERT INTO candidatos (nome,sobrenome,data_nascimento,email,telefone,linkedin,cpf,estado,cep,descricao,formacao,senha)
VALUES 
('Sandubinha', 'Silva', '1990-05-10', 'sanduba@gmail.com', '11999999999', 'https://www.linkedin.com/in/sandubinhadev/', '123.456.789-00', 'SP', '12345-678', 'Desenvolvedor fullstack', 'Superior Completo', 'senha123'),
('Mariana', 'Lima', '1988-07-14', 'mariana.lima@gmail.com', '11988888888', 'https://www.linkedin.com/in/marianalima/', '321.654.987-10', 'RJ', '98765-432', 'Analista de Sistemas', 'Superior Completo', 'senha123'),
('Carlos', 'Ferreira', '1992-03-22', 'carlos.ferreira@gmail.com', '11977777777', 'https://www.linkedin.com/in/carlosferreira/', '123.987.654-32', 'MG', '87654-321', 'Engenheiro de Software', 'Superior Completo', 'senha123'),
('Juliana', 'Souza', '1995-11-05', 'juliana.souza@gmail.com', '11966666666', 'https://www.linkedin.com/in/julianasouza/', '987.654.321-01', 'SP', '54321-098', 'Desenvolvedora Backend', 'Superior Completo', 'senha123'),
('Fernando', 'Oliveira', '1985-01-18', 'fernando.oliveira@gmail.com', '11955555555', 'https://www.linkedin.com/in/fernandooliveira/', '654.321.987-09', 'RS', '65432-109', 'Administrador de Sistemas', 'Superior Completo', 'senha123');


-- Relacionando competências predefinidas ao candidato Sandubinha
INSERT INTO Candidato_Competencias (candidato_id, competencia_id) VALUES (1, 1);  -- Relacionando Sandubinha à competência Python
INSERT INTO Candidato_Competencias (candidato_id, competencia_id) VALUES (2, 2);  -- Relacionando Mariana à competência Java
INSERT INTO Candidato_Competencias (candidato_id, competencia_id) VALUES (3, 3);  -- Relacionando Carlos à competência Groovy
INSERT INTO Candidato_Competencias (candidato_id, competencia_id) VALUES (4, 3);  -- Relacionando Juliana à competência Groovy
INSERT INTO Candidato_Competencias (candidato_id, competencia_id) VALUES (5, 3);  -- Relacionando Fernando à competência Groovy


-- Inserir empresa PastelSoft
INSERT INTO empresas (nome,cnpj,email,pais,estado,cep,descricao,senha)
VALUES 
('Pastelsoft', '12.345.678/0001-00', 'contato@pastelsoft.com', 'Brasil', 'SP', '98765-432', 'Empresa de desenvolvimento de ERPs', 'pastel123'),
('TechBurger', '23.456.789/0001-11', 'contato@techburger.com', 'Brasil', 'RJ', '12345-678', 'Empresa de soluções em TI e desenvolvimento de aplicativos', 'burger123'),
('CodePizza', '34.567.890/0001-22', 'contato@codepizza.com', 'Brasil', 'MG', '87654-321', 'Desenvolvedora de software especializado para automação de pizzarias', 'pizza123'),
('CloudSalad', '45.678.901/0001-33', 'contato@cloudsalad.com', 'Brasil', 'PR', '54321-098', 'Consultoria em computação em nuvem e migração de dados', 'salad123'),
('ByteCafe', '56.789.012/0001-44', 'contato@bytecafe.com', 'Brasil', 'RS', '65432-109', 'Cafeteria especializada no desenvolvimento de software sob medida', 'cafe123');

-- Inserir Vaga
INSERT INTO vagas (nome,descricao,local,empresa_id)
VALUES 
('Programador', 'Desenvolvedor FullStack', 'Brasil', 1),
('Engenheiro de Software', 'Desenvolvimento de Sistemas e Aplicações', 'Brasil', 2),
('Analista de Dados', 'Análise e Modelagem de Dados', 'Brasil', 3),
('DevOps', 'Gerenciamento de Infraestrutura e Automação', 'Brasil', 4),
('Arquiteto de Soluções', 'Planejamento e Design de Arquiteturas de Sistemas', 'Brasil', 5);

-- Relacionando competências a vaga que foi criada pela empresa PastelSoft
INSERT INTO Vaga_Competencia (vaga_id, competencia_id) VALUES (1, 1); -- Relacionando Vaga Programador à competência Python
INSERT INTO Vaga_Competencia (vaga_id, competencia_id) VALUES (2, 2); -- Relacionando Vaga Engenheiro de Software à competência Java



-- Simulando curtida do candidato a uma vaga
INSERT INTO Curtida_candidato_vagas (candidato_id,vaga_id) VALUES (1, 1)-- Candidato Sandubinha curtindo a vaga de Programador

-- Simulando curtida da empresa a um candidato
INSERT INTO Curtida_empresa_candidato (empresa_id,candidato_id) VALUES (1, 1)-- Empresa Pastelsoft curtindo o candidato Sandubinha


-- triggers

CREATE OR REPLACE FUNCTION verificar_match_candidato()
RETURNS TRIGGER AS $$
BEGIN
  -- Verificando se a empresa já curtiu o candidato
  IF EXISTS (
    SELECT 1
    FROM curtida_empresa_candidato
    WHERE empresa_id = (SELECT empresa_id FROM vagas WHERE id = NEW.vaga_id)
    AND candidato_id = NEW.candidato_id
  ) THEN
    -- Aqui é inserido o match na tabela matches
    INSERT INTO matches (candidato_id, empresa_id, vaga_id)
    VALUES (NEW.candidato_id, (SELECT empresa_id FROM vagas WHERE id = NEW.vaga_id), NEW.vaga_id);
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para ser ativado quand a tabela quando um candidato curtir uma vaga
CREATE TRIGGER verificar_match_trigger_candidato
AFTER INSERT ON curtida_candidato_vagas
FOR EACH ROW
EXECUTE FUNCTION verificar_match_candidato();



CREATE OR REPLACE FUNCTION verificar_match_empresa()
RETURNS TRIGGER AS $$
BEGIN
  -- Verificando se o candidato já curtiu uma vaga da empresa
  IF EXISTS (
    SELECT 1
    FROM curtida_candidato_vagas
    JOIN vagas ON vagas.id = curtida_candidato_vagas.vaga_id
    WHERE curtida_candidato_vagas.candidato_id = NEW.candidato_id
    AND vagas.empresa_id = NEW.empresa_id
  ) THEN
    -- Aqui é inserido o match na tabela matches
    INSERT INTO matches (candidato_id, empresa_id, vaga_id)
    VALUES (NEW.candidato_id, NEW.empresa_id, 
            (SELECT vaga_id FROM curtida_candidato_vagas
             JOIN vagas ON vagas.id = curtida_candidato_vagas.vaga_id
             WHERE curtida_candidato_vagas.candidato_id = NEW.candidato_id
             AND vagas.empresa_id = NEW.empresa_id LIMIT 1));
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para ser ativado a tabela quando uma empresa curtir um candidato
CREATE TRIGGER verificar_match_trigger_empresa
AFTER INSERT ON curtida_empresa_candidato
FOR EACH ROW
EXECUTE FUNCTION verificar_match_empresa();





