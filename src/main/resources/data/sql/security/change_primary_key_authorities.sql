alter table authorities drop column id;
alter table authorities add primary key (username, role_code);