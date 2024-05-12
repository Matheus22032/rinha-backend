CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE OR REPLACE FUNCTION generate_searchable_text(_nome VARCHAR, _apelido VARCHAR, _stack JSON)
    RETURNS TRIGGER AS
$$
BEGIN
    RETURN _nome || _apelido || _stack;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE TABLE IF NOT EXISTS pessoa
(
    id         uuid         not null,
    apelido    varchar(32)  not null unique,
    nome       varchar(100) not null,
    nascimento varchar(255) not null,
    stack      text,
    primary key (id),
    searchable text GENERATED ALWAYS AS (generate_searchable_text(nome, apelido, stack)) STORED
);

CREATE INDEX IF NOT EXISTS idx_pessoa_searchable ON pessoa USING gist (searchable gist_trgm_ops);

CREATE UNIQUE INDEX IF NOT EXISTS idx_pessoa_apelido ON pessoa USING btree (apelido);