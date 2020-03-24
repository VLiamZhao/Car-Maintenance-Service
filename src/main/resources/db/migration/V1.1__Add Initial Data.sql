insert into customer (name, description) values
('John Smith', 'Uber driver'),
('Liam Zhao', 'Student');
commit;

insert into car (type, price, regi_date, owner_id) values
('Toyota Camry', '9000.05', '2020-01-24', '1'),
('Lexus E300', '20000.99', '2020-01-29', '2');
commit;

insert into maintenance (component, cost, date, description, car_id) values
('front glass','150.00','2020-03-01', 'change', '1'),
('back seats','30.00','2020-03-15', 'clean', '2');
commit;
