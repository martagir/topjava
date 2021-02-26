DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2021-02-25 09:17:40.523000', 'Завтрак', 300, 100000),
       ('2021-02-26 12:17:40.523000', 'Обед',    250, 100000),
       ('2021-02-26 18:17:40.523000', 'Ужин',    250, 100000),
       ('2021-02-26 09:17:40.523000', 'Завтрак', 250, 100001),
       ('2021-02-26 12:17:40.523000', 'Обед',    250, 100001),
       ('2021-02-26 18:17:40.523000', 'Ужин',    250, 100001);
