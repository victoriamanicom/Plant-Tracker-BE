drop table if exists plant CASCADE;


create table plant 

(
	id integer PRIMARY KEY AUTO_INCREMENT,
 	img_url varchar(255),
 	is_succulent boolean not null,
 	leaf_colour varchar(255),
 	name varchar(255) UNIQUE,
 	pot_size integer not null
 );