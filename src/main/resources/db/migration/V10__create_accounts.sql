CREATE TABLE IF NOT EXISTS accounts (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO accounts (username, password, role)
VALUES ('admin',
        '$2a$10$SCNSWTJGfjwtlyEYrDrrlOhTG0/BkNr3sLrPN5fZkuto69vBGjia6',
        'ROLE_ADMIN')

ON CONFLICT (username) DO NOTHING;