insert into customer (name, password, email, description) values
('John Smith', '25f9e794323b453885f5181f1b624d0b', 'js@gmail.com', 'Uber driver'),
('Liam Zhao', '25f9e794323b453885f5181f1b624d0b', 'lz@gmail.com', 'Student');
commit;

insert into car (type, price, regi_date, owner_id) values
('Toyota Camry', '9000.05', '2020-01-24', '1'),
('Lexus E300', '20000.99', '2020-01-29', '2');
commit;

insert into maintenance (component, cost, date, description, car_id) values
('front glass','150.00','2020-03-01', 'change', '1'),
('back seats','30.00','2020-03-15', 'clean', '2');
commit;
