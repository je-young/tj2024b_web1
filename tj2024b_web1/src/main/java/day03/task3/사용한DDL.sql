drop database if exists mydb0116;
create database mydb0116;
use mydb0116;
create table visit(
	num int auto_increment ,
    content longtext , 
    age int ,
    constraint primary key(num)
);

drop database if exists waiting;
create database waiting;
use waiting;
create table waiting(
	num int auto_increment ,
    phone longtext , 
    count int ,
    constraint primary key(num)
);

drop database if exists waitingDB;
create database waiting;
use waiting;
create table waiting(
	num int auto_increment ,
    phone longtext , 
    count int ,
    constraint primary key(num)
);