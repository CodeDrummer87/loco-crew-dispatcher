CREATE TABLE IF NOT EXISTS statuses (
id BIGSERIAL PRIMARY KEY,
title VARCHAR(30) NOT NULL
);

INSERT INTO statuses(title)
VALUES ('в работе'),
       ('на вызове'),
       ('в ожидании явки'),
       ('на лечении'),
       ('в отпуске'),
       ('на обучении');