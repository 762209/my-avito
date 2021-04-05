delete from ad;
insert into ad (id, ad_category, adress, city, created_at, description, name, price, user_id) values
(1, 'ANIMAL', 'улица Лесная дом 59А', 'Москва', '2021-04-05 17:41:03.413237', 'Продом кота в хорошие руки',
'кот', 3000, 1),
(2, 'ANIMAL', 'улица Лесная дом 59А', 'Москва', '2021-04-05 17:41:03.413237', 'Продом собаку в хорошие руки',
'собака', 5000, 1),
(3, 'ANIMAL', 'улица Лесная дом 59А', 'Москва', '2021-04-05 17:41:03.413237', 'Продом попугая в хорошие руки',
'попугай', 5000, 1);

alter sequence hibernate_sequence restart with 10;