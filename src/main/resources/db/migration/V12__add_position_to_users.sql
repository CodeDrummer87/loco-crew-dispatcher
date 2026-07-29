-- Добавляем новые должности в таблицу positions (ещё не существует)
INSERT INTO positions (title, abbreviate) VALUES
                                              ('ЗавБригадами', 'ЗавБриг'),
                                              ('Нарядчик', 'Нарядч');

-- Добавляем колонку position_id в users
ALTER TABLE users ADD COLUMN position_id BIGINT;

-- Связываем с positions
ALTER TABLE users
    ADD CONSTRAINT fk_users_position
        FOREIGN KEY (position_id) REFERENCES positions(id);

-- Назначаем должности по ролям (НЕ по именам!)
UPDATE users SET position_id = (SELECT id FROM positions WHERE title = 'ЗавБригадами') WHERE role = 'ADMIN';
UPDATE users SET position_id = (SELECT id FROM positions WHERE title = 'Нарядчик') WHERE role = 'USER';