DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, user_id)
    VALUES ('dinner', 1000, 100000),
      ('2 dinner', 1000, 100000),
      ('supper', 500, 100000),
      ('dinner', 500, 100001),
      ('2 dinner', 900, 100001),
      ('supper', 200, 100001);
