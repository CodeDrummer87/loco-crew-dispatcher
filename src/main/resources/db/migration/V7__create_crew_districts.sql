CREATE TABLE IF NOT EXISTS crew_districts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50)
);

INSERT INTO crew_districts (title)
VALUES ('Входная - Ишим'),
       ('Входная - Иртышское'),
       ('Входная - Барабинск'),
       ('Московка - Петропавловск'),
       ('Московка - Иртышское'),
       ('Московка - Ишим');