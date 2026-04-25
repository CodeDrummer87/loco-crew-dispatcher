CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    personnel_number INTEGER NOT NULL UNIQUE,
    position_id BIGINT,
    is_at_work BOOLEAN DEFAULT TRUE,
    status_id BIGINT,

    CONSTRAINT fk_employees_position FOREIGN KEY (position_id) REFERENCES positions(id) ON DELETE CASCADE,
    CONSTRAINT fk_employees_status FOREIGN KEY (status_id) REFERENCES statuses(id) ON DELETE CASCADE
    );

INSERT INTO employees (full_name, personnel_number, position_id, is_at_work, status_id)
VALUES
    ('Иванов Сергей Иванович', 1543, 1, TRUE, 1),
    ('Смирнов Андрей Петрович', 1544, 2, TRUE, 1),
    ('Васильев Николай Сергеевич', 1545, 3, FALSE, 2),
    ('Петров Михаил Александрович', 1546, 4, TRUE, 1),
    ('Федоров Алексей Дмитриевич', 1547, 1, TRUE, 1),
    ('Морозов Владимир Николаевич', 1548, 2, FALSE, 3),
    ('Волков Дмитрий Андреевич', 1549, 1, TRUE, 1),
    ('Алексеев Павел Владимирович', 1550, 2, TRUE, 1),
    ('Лебедев Артём Михайлович', 1551, 1, FALSE, 4),
    ('Семенов Максим Игоревич', 1552, 2, TRUE, 1),
    ('Павлов Евгений Викторович', 1553, 1, TRUE, 1),
    ('Степанов Роман Алексеевич', 1554, 2, TRUE, 1),
    ('Николаев Илья Валерьевич', 1555, 1, FALSE, 2),
    ('Захаров Виктор Борисович', 1556, 2, TRUE, 1),
    ('Борисов Олег Константинович', 1557, 1, TRUE, 1),
    ('Тимофеев Григорий Павлович', 1558, 2, TRUE, 1),
    ('Макаров Валентин Егорович', 1559, 1, FALSE, 3),
    ('Чернышов Константин Аркадьевич', 1560, 2, TRUE, 1),
    ('Ветров Борис Геннадьевич', 1561, 1, TRUE, 1),
    ('Громов Вадим Андреевич', 1562, 2, TRUE, 1);
