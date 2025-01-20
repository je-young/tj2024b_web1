drop database if exists waitingDB;
create database waitingDB;
use waitingDB;

create table waiting(
	num int auto_increment,
    constraint primary key ( num ),
    phone varchar(13) not null,
    count int not null
);

select * from waiting;