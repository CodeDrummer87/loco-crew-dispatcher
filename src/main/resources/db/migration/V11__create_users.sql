CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    personnel_number INTEGER UNIQUE NOT NULL,
    date_of_employment DATE NOT NULL,
    is_active BOOLEAN DEFAULT true,
    account_id BIGINT UNIQUE,

    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE SET NULL
);

INSERT INTO users (full_name, personnel_number, date_of_employment, is_active)
VALUES
        ('Волков Александр Михайлович', 1563, '1991-01-15',true),
        ('Белова Екатерина Дмитриевна', 1564, '2004-03-20', true),
        ('Ковалёв Денис Сергеевич', 1565, '2019-07-10', true),
        ('Фёдорова Мариа Андреевна', 1566, '2016-11-01', true),
        ('Григорьев Игорь Олегович', 1567, '2023-02-11', true),
        ('Тимофеева Наталья Павловна', 1568, '2020-09-05', true),
        ('Никифоров Сергей Евгеньевич', 1569, '2024-01-25', true),
        ('Кузьмина Ольга Сергеевна', 1570, '2021-06-30', true);