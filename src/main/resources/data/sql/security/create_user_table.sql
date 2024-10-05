create table users (
	username varchar(50) not null primary key,
	password varchar(500) not null,
	email varchar(50) not null,
	first_name varchar(50) not null,
	second_name varchar(50),
	phone varchar(12),
	constraint proper_email check (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
	constraint proper_phone check (phone ~* '(^8|7|\+7)((\d{10})|(\s\(\d{3}\)\s\d{3}\s\d{2}\s\d{2}))$')
);