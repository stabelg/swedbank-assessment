create table application_user_detail(
 id bigint not null unique primary key auto_increment,
 name varchar(50),
 billing_address varchar(150),
 shipping_address varchar(150));

create table application_user(
 id bigint not null unique primary key auto_increment,
 username varchar(50) unique,
 email varchar(50) unique,
 password varchar,
 details_id bigint);

ALTER TABLE application_user
ADD FOREIGN KEY (details_id)
REFERENCES application_user_detail(id)

