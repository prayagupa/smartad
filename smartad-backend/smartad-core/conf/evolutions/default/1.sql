# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `Brand` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`color` VARCHAR(254) NOT NULL);
create table `Product` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(254) NOT NULL,`brand` BIGINT NOT NULL,`price` DOUBLE NOT NULL,`created` BIGINT);

# --- !Downs

drop table `Product`;
drop table `Brand`;

