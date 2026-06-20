CREATE TABLE IF NOT EXISTS useful_articles (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    master_id BIGINT,
    creating_date DATE DEFAULT CURRENT_DATE,
    short_description VARCHAR(500),
    cover_image_url VARCHAR(255),
    views_count BIGINT DEFAULT 0,
    reading_time_minutes INTEGER,

    CONSTRAINT fk_useful_articles_master
    FOREIGN KEY (master_id)
    REFERENCES masters(id)
    ON DELETE SET NULL);

CREATE INDEX idx_useful_articles_master_id ON useful_articles(master_id);