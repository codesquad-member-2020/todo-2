DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS log;

CREATE TABLE IF NOT EXISTS user
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(20),
    password VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS project
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS category
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(64),
    is_deleted  BOOLEAN,
    project     BIGINT REFERENCES project (id),
    project_key INT
);

CREATE TABLE IF NOT EXISTS card
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(64),
    content      VARCHAR(500),
    time_created DATETIME,
    is_deleted   BOOLEAN,
    user         BIGINT REFERENCES user (id),
    category     BIGINT REFERENCES category (id),
    category_key INT
);

CREATE TABLE  IF NOT EXISTS log (
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user        BIGINT REFERENCES user(id),
    card        BIGINT REFERENCES card(id),
    card_title      VARCHAR(64),
    src_category    VARCHAR(64),
    dst_category    VARCHAR(64),
    action          ENUM('added', 'removed', 'updated', 'moved'),
    time_logged     DATETIME,
    project     BIGINT REFERENCES project(id),
    project_key INT
);
