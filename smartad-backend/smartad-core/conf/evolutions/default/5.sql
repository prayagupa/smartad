# --- !Ups

CREATE TABLE `Product` (
    `id` int(11) NOT NULL,
    `name` varchar(255) NOT NULL,
    `brand` int(11) NOT NULL,
    `price` int(11),
    `created` datetime
  ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


# --- !Downs

drop table "Product"
