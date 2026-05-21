CREATE TABLE IF NOT EXISTS tb_movie_category (
    movie_id     BIGINT NOT NULL,
    category_id  BIGINT NOT NULL,

    CONSTRAINT pk_movie_category
    PRIMARY KEY (movie_id, category_id),

    CONSTRAINT fk_movie_category_movie
    FOREIGN KEY (movie_id) REFERENCES tb_movie(id) ON DELETE CASCADE,

    CONSTRAINT fk_movie_category_category
    FOREIGN KEY (category_id) REFERENCES tb_category(id) ON DELETE CASCADE
);