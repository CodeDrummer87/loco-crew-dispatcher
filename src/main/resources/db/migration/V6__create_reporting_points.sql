CREATE TABLE IF NOT EXISTS reporting_points (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(35) NOT NULL
    );

INSERT INTO reporting_points (title)
VALUES
    ('ст.Входная'),
    ('ст.Московка'),
    ('ст.Омск-Пассажирский');