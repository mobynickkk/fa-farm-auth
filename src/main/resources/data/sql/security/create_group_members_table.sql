create table group_members (
	id serial primary key,
	username varchar(50) not null,
	group_id integer not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);