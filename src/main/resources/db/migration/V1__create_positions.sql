CREATE TABLE IF NOT EXISTS positions (
    id BIGSERIAL PRIMARY KEY,
    position_name VARCHAR(25) NOT NULL,
    abbreviate VARCHAR(10) NOT NULL
    );

INSERT INTO positions (position_name,  abbreviate)
VALUES
    ('Машинист', 'ТЧМ'),
    ('Помощник машиниста', 'ТЧПМ'),
    ('Дублёр', 'Дублёр'),
    ('Машинист-инструктор', 'ТЧМИ');
