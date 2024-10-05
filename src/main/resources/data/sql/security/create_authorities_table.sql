create table authorities (
    id uuid primary key,
	username varchar(50) not null,
	role_code varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username),
	constraint fk_auhtorities_roles foreign key(role_code) references roles(role_code)
);