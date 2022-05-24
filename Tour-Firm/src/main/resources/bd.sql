drop database if exists TourFirm;
create database TourFirm;

use TourFirm;
create table COUNTRYS(
                         id bigint primary key auto_increment,
                         name varchar(30),
                         id_visa bigint
);

create table CITYS(
                      id bigint primary key auto_increment,
                      name varchar(30),
                      id_country bigint
);

create table VISAS
(
                      id bigint primary key auto_increment,
                      name varchar(30)
);

create table USERS
(
                      id bigint primary key auto_increment,
                      password varchar(255),
                      email varchar(100) unique,
                      firstName varchar(30),
                      lastName varchar(30),
                      isAdmin boolean,
                      id_country bigint
);

create table HOTELS
(
                       id bigint primary key auto_increment,
                       name varchar(100),
                       numberRooms int,
                       id_city bigint,
                       priceNight int,
                       discription text
);

create table BOOKINGS
(
                         id bigint primary key auto_increment,
                         startDate date,
                         endDate date,
                         amountRooms int,
                         price int,
                         id_user bigint,
                         id_hotel bigint
);

create table USERS_VISAS
(
                            id_user bigint,
                            id_visa bigint,
                            primary key (id_user,id_visa)
);

alter table CITYS add foreign key (id_country) references COUNTRYS(id);

alter table USERS add foreign key (id_country) references COUNTRYS(id);

alter table COUNTRYS add foreign key (id_visa) references VISAS(id);

alter table HOTELS add foreign key (id_country) references COUNTRYS(id);
alter table HOTELS add foreign key (id_city) references CITYS(id);

alter table BOOKINGS add foreign key (id_user) references USERS (id) ON DELETE CASCADE;
alter table BOOKINGS add foreign key (id_hotel) references HOTELS(id);

alter table USERS_VISAS add foreign key (id_user) references USERS (id);
alter table USERS_VISAS add foreign key (id_visa) references VISAS (id);