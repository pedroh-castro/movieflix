CREATE TABLE IF NOT EXISTS tb_movie_streaming (
    movie_id     BIGINT NOT NULL,
    streaming_id  BIGINT NOT NULL,

    CONSTRAINT pk_movie_streaming
    PRIMARY KEY (movie_id, streaming_id),

    CONSTRAINT fk_movie_streaming_movie
    FOREIGN KEY (movie_id) REFERENCES tb_movie(id) ON DELETE CASCADE,

    CONSTRAINT fk_movie_streaming_streaming
    FOREIGN KEY (streaming_id) REFERENCES tb_streaming(id) ON DELETE CASCADE
);