# --- !Ups

create table Marketplace (
    id int primary key not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    contact smallint not null
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# --- !Downs

drop table Marketplace;
