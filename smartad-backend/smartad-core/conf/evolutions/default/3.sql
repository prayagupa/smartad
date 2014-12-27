# --- !Ups

create table Brand (id int primary key not null auto_increment, name VARCHAR(100),color VARCHAR(100));

# --- !Downs

drop table "Brand";

