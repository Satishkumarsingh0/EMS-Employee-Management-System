create database ems;
use ems;

create table employee(userid varchar(6) primary key, name varchar(50) not null, phone bigint not null, 
department varchar(50) not null, level varchar(20)not null, salary bigint not null, dob date not null, 
address varchar(50) not null, 
 empaddtime timestamp default current_timestamp,
 constraint check_level check(level in('entry','staff','manager','senior manager','executive')),
 constraint check_depart check(department in('human resources','sales','accounting','marketing','it','customer support'))); 
 
 #drop table employee;
 select * from employee;
 insert into employee 
 (userid, name, phone, department, level, salary, dob, address)
 values
 ('m01','manish kumar',9060426702, 'it', 'manager',50000, '2006-07-13', 'bihar');
 
 insert into employee (userid, name, phone, department, level, salary, dob, address) VALUES ("e01", "Tanuj", 1234567890, "sales",  "entry",20000, "2007-01-01", "sitamarhi");

 create table deletedemployee(userid varchar(6) primary key, name varchar(50) not null, phone bigint not null, 
department varchar(50) not null, level varchar(20)not null, salary bigint not null, dob date not null, 
address varchar(50) not null, 
 empaddtime timestamp default current_timestamp); 
 SELECT * from deletedemployee;
 delete from deletedemployee;
 
create table admins(userid varchar(20) primary key, password varchar(20) not null, 
addadmintime timestamp default current_timestamp,
foreign key (userid) references employee(userid));

insert into admins (userid, password) values("sa01","sa01");

insert into admins (userid, password) values("shamim","shamim");
insert into admins (userid, password) values("maaz","maaz");
insert into admins (userid, password) values("adnan","adnan");

drop table admins;
select * from admins;



create table superAdmin(userid varchar(20) primary key, password varchar(20) unique not null, 
suadminaddtie timestamp default current_timestamp
);
insert into superAdmin (userid, password) values("suSatish", "suSatish");
#drop table superAdmin;
select * from superadmin;




 create table admin(userid varchar(6) primary key, name varchar(50) not null, phone bigint unique not null, 
department varchar(50) default 'administration', level varchar(20) default 'admin', salary bigint not null,
 dob date not null, address varchar(50) not null,adminaddtime timestamp default current_timestamp); 
 #drop table admin;
 
 select * from admins;
 drop table admins;
 
 insert into admin 
 (userid,name,phone,salary,dob,address) 
 values
 ('sa01','Satish Kumar', 8757073613, 50000,'2000-10-04','ldh');
 
 select * from employee where userid = 101;
 
 show databases;
 select * from admins where userid = "sa01";