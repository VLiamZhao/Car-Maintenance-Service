insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/files, /customer, /car, /maintenance', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/customer, /car, /maintenance', 'Y', 'Y', 'Y', 'N'),
('user', '/car, /maintenance', 'Y', 'N', 'N', 'N');
commit;

insert into customer_role values
(1, 1),
(2, 3);
commit;