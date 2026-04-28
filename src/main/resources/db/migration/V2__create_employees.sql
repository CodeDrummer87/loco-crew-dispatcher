CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    personnel_number INTEGER NOT NULL UNIQUE,
    position_id BIGINT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    status_id BIGINT NOT NULL,

    CONSTRAINT fk_employees_position FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE CASCADE
    );

INSERT INTO employees (full_name, personnel_number, position_id, is_active, status_id)
VALUES
    ('Иванов Сергей Иванович', 1543, 1, TRUE, 0),
    ('Смирнов Андрей Петрович', 1544, 2, TRUE, 1),
    ('Васильев Николай Сергеевич', 1545, 3, TRUE, 1),
    ('Петров Михаил Александрович', 1546, 4, TRUE, 0),
    ('Федоров Алексей Дмитриевич', 1547, 1, TRUE, 1),
    ('Морозов Владимир Николаевич', 1548, 2, TRUE, 2),
    ('Волков Дмитрий Андреевич', 1549, 1, TRUE, 0),
    ('Алексеев Павел Владимирович', 1550, 2, TRUE, 0),
    ('Лебедев Артём Михайлович', 1551, 1, TRUE, 3),
    ('Семенов Максим Игоревич', 1552, 2, TRUE, 0),
    ('Павлов Евгений Викторович', 1553, 1, TRUE, 0),
    ('Степанов Роман Алексеевич', 1554, 2, TRUE, 0),
    ('Николаев Илья Валерьевич', 1555, 1, TRUE, 1),
    ('Захаров Виктор Борисович', 1556, 2, TRUE, 0),
    ('Борисов Олег Константинович', 1557, 1, TRUE, 0),
    ('Тимофеев Григорий Павлович', 1558, 2, TRUE, 0),
    ('Макаров Валентин Егорович', 1559, 1, TRUE, 2),
    ('Чернышов Константин Аркадьевич', 1560, 2, TRUE, 0),
    ('Ветров Борис Геннадьевич', 1561, 1, TRUE, 0),
    ('Громов Вадим Андреевич', 1562, 2, TRUE, 0);
