ALTER TABLE users
    ADD COLUMN fullname VARCHAR(200) NOT NULL,
    ADD COLUMN personnel_number INTEGER UNIQUE NOT NULL,
    ADD COLUMN date_of_employment DATE NOT NULL,
    ADD COLUMN is_active BOOLEAN DEFAULT true;

INSERT INTO users (username, password, role, fullname, personnel_number, date_of_employment, is_active)
VALUES
    ('volkovam', 'Volkov4564!', 'ADMIN', 'Волков Александр Михайлович',
     1563, '1999-01-15', true),
    ('belovaed', 'Belova@234347rzd', 'USER', 'Белова Екатерина Дмитриевна',
     1564, '2004-03-20', true),
    ('kovalevds', 'Kovalev!299gf', 'USER', 'Ковалев Денис Сергеевич',
     1565, '2019-07-10', true),
    ('fedorovama', 'Fedorova$204vbh', 'ADMIN', 'Федорова Мария Андреевна',
     1566, '2016-11-01', true),
    ('grigorievio', 'Grigoriev_2324ghk', 'USER', 'Григорьев Игорь Олегович',
     1567, '2023-02-14', true),
    ('timofeevanp', 'Timofeeva!2020', 'USER', 'Тимофеева Наталья Павловна',
     1568, '2020-09-05', true),
    ('nikiforovse', 'Nikiforov@qqw3424343', 'USER', 'Никифоров Сергей Евгеньевич',
     1569, '2024-01-25', true),
    ('kuzminaos', 'Kuzmina$2131@', 'USER', 'Кузьмина Ольга Сергеевна',
     1570, '2021-06-30', true);