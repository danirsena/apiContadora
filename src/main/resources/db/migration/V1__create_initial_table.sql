CREATE TABLE url_tracker (
    -- ID sequencial, não nulo e chave primária
    id BIGSERIAL PRIMARY KEY,

    -- Coluna para a URL, não nula
    url VARCHAR(512) NOT NULL,

    -- Contador de acessos, não nulo, inicia em 0
    click_count INTEGER NOT NULL DEFAULT 0
);