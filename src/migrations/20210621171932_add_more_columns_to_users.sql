ALTER TABLE users
    ADD COLUMN email VARCHAR(50) NOT NULL UNIQUE,
    ADD COLUMN display_name INT(11) NOT NULL UNIQUE;