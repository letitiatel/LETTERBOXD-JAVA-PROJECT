select * from TEST;
create table VAR(
                    job_id number(6) constraint pk_job primary key,
                    job_titlu varchar2(50) constraint job_titlu_null not null

);
insert into VAR(job_id,job_titlu) values (10,'Cofetar');
insert into VAR(job_id,job_titlu) values (11,'Casier');
insert into VAR(job_id,job_titlu) values (1,'Doctor2');

select * from VAR;




create table USERR (
                      id_user number(6) constraint pk_iduser primary key ,
                      username varchar2(50) constraint username_null not null ,
                      email varchar2(255) constraint email_null not null ,
                      password varchar2(255) constraint parola_null not null
);

select * from USERR;


create table GENRE (
                      id_genre number(6) constraint pk_idgenre primary key ,
                      name  varchar2(50) constraint name_null not null

);

select * from GENRE;

create table RATING (
                      id_rating number(6) constraint pk_idrating primary key,
                      nota number(6) constraint nota_null not null
);

select * from RATING;


create table ACTOR (
                        id_actor number(6) constraint pk_idactor primary key,
                        name varchar2(50) constraint name1_null not null,
                        age number(6) constraint age_null not null,
                        numberofplays number(6) constraint numberofplays_null not null,
                        numberofawards number(6) constraint numberofawards_null not null

);

select * from ACTOR;





