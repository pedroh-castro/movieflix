CREATE TABLE IF NOT EXISTS tb_movie (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    descritpion TEXT,
    realease_date DATE,
    rating NUMERIC(3,1),
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);