use david;
drop table if exists quarterback;
create table quarterback (
  rank int unsigned not null auto_increment,
  lastName varchar(30) not null,
  firstName varchar(30) not null,
  yards int varchar(10) not null,
  team varchar(30) not null,
  opponent varchar(30) not null,
  primary key(rank)
);
