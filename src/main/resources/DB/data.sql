INSERT INTO USERS(id, name, surname, username, password, country, city, street, post_code, age, email)
VALUES ('1', 'test_user', 'test_user', 'user', 'user', 'LT', 'Vilnius', 'Savanoriu av.', 'LT-96555', '18',
        'user@gmail.com'), /*USER*/
       ('2', 'test_admin', 'test_admin', 'admin', 'admin', 'LT', 'Kaunas', 'Kauno Str.', 'LT-57555', '30',
        'admin@gmail.com'); /*admin*/

INSERT INTO ROLES(id, name)
VALUES ('1', 'USER'),
       ('2', 'ADMIN');

INSERT INTO USERS_ROLES(user_id, role_id)
VALUES ('1', '1'),
       ('2', '1'),
       ('2', '2');