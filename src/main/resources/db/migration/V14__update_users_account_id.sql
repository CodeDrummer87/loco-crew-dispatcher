UPDATE users u
SET account_id = a.id
FROM accounts a
WHERE (u.full_name, a.username) IN (
    ('Белова Екатерина Дмитриевна', 'belovaed'),
    ('Ковалёв Денис Сергеевич', 'kovalevds'),
    ('Фёдорова Мария Андреевна', 'fedorovama'),
    ('Григорьев Игорь Олегович', 'grigorevio'),
    ('Тимофеева Наталья Павловна', 'timofeevanp'),
    ('Никифоров Сергей Евгеньевич', 'nikiforovse'),
    ('Кузьмина Ольга Сергеевна', 'kuzminaos')
    )