delete from ad;
delete from avito_user;

insert into avito_user(id, username, password, fullname) values
(1, 'pavel', '$2a$10$7HST6koWWD6bmgXYnht7/OXcvkmygCUqCZUd4D1l/7T9.pLVH7Ply', 'Павел Сергеевич'),
(2, 'dmitrii', '$2a$10$7HST6koWWD6bmgXYnht7/OXcvkmygCUqCZUd4D1l/7T9.pLVH7Ply', 'Дмитрий Александрович');