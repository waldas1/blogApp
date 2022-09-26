INSERT INTO USERS(id, name, surname, username, password, country, age, email)
VALUES ('97591abe-5108-4bc2-afaa-6bc6a339619c', 'test_user', 'test_user', 'user', '{bcrypt}$2a$10$AsRCsrfh4423vjPr0xKpZeNpYixVcNtDpiGdM5xcIejUXOttH2jcu', 'LT', '18', 'user@gmail.com'), /*USER*/
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', 'test_admin', 'test_admin', 'admin', '{bcrypt}$2a$10$9Ox9WgR8X5SD04lLSdCwJ.AITH/cAZmcZ9tMkqJUFYSc0krItXT9W', 'LT', '30', 'admin@gmail.com'); /*admin*/

INSERT INTO ROLES(id, name)
VALUES ('7f74bb02-9f14-43ce-8b28-8c0c889d1558', 'USER'),
       ('25dde1c9-f740-46a7-a598-d62f37126950', 'ADMIN');

INSERT INTO USERS_ROLES(user_id, role_id)
VALUES ('97591abe-5108-4bc2-afaa-6bc6a339619c', '7f74bb02-9f14-43ce-8b28-8c0c889d1558'),
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', '7f74bb02-9f14-43ce-8b28-8c0c889d1558'),
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', '25dde1c9-f740-46a7-a598-d62f37126950');

 INSERT INTO CONTENTS(id, picURL, pic_Comment, date)
 VALUES ('2d7a5462-21a5-11ed-861d-0242ac120002', 'https://images.pexels.com/photos/36029/aroni-arsa-children-little.jpg?auto=compress&cs=tinysrgb&w=1600', 'Good', '2022-09-04'),
         ('6ab6633c-2229-11ed-861d-0242ac120002', 'https://images.pexels.com/photos/36029/aroni-arsa-children-little.jpg?auto=compress&cs=tinysrgb&w=1600', 'Nice', '2022-09-04');
