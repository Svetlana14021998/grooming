CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    birthday DATE,
    role VARCHAR(20) NOT NULL,
    photo_name VARCHAR(30),
    master_id BIGINT,
    about_me TEXT,
    achievements TEXT,
    telegram VARCHAR(30),
    vk VARCHAR(30),
    hobby TEXT,
    education VARCHAR(200),

    CONSTRAINT fk_user_master FOREIGN KEY (master_id) REFERENCES masters(id) ON DELETE SET NULL,
    CONSTRAINT chk_user_role CHECK (role IN ('ADMIN', 'MANAGER', 'MASTER'))
    );

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_master_id ON users(master_id);