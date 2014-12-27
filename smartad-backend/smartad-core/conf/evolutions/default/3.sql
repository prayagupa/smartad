# --- !Ups

create table BRAND (name VARCHAR(100) PRIMARY KEY,color VARCHAR(100));

# --- !Downs

drop table "BRAND";

