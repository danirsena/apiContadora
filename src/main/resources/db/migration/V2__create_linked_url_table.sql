-- V2__create_click_table.sql
-- Cria a tabela para armazenar os links aninhados e o relacionamento com a tabela principal

CREATE TABLE click (
    -- ID Sequencial para a tabela de links aninhados
    id BIGSERIAL PRIMARY KEY,

    -- O timestamp da hora que o link foi criado
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    -- Chave Estrangeira: Referência à tabela url_tracker (o ID da URL principal)
    link_id BIGINT NOT NULL,

    -- Define a restrição de chave estrangeira
    -- Garante que link_id sempre aponte para um ID válido em url_tracker.
    -- ON DELETE CASCADE: Se a linha em url_tracker for deletada, todos os links aninhados relacionados também serão.
    CONSTRAINT fk_url_tracker
        FOREIGN KEY (link_id)
        REFERENCES url_tracker (id)
        ON DELETE CASCADE
);

-- Opcional: Adicionar um índice na chave estrangeira para otimizar buscas por grupo
CREATE INDEX idx_click_link_id ON click (link_id);