# MyBatis DB연결 세팅

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### 테이블 생성
```sql
create table users(
    id number primary key,
    username varchar2(20),
    password varchar2(20),
    email varchar2(50),
    createdAt TIMESTAMP
);

CREATE SEQUENCE users_seq 
INCREMENT BY 1 
START WITH 1;
```

### 더미데이터 추가
```sql
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'ssar', '1234', 'ssar@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'cos', '1234', 'cos@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'hong', '1234', 'hong@nate.com', sysdate);
commit;
```


### 내가 사용한 쿼리들
```sql

SELECT b.id, b.title, b.content, b.usersId, b.createdAt,
					 u.username, u.password, u.email
		from boards b INNER JOIN  users u
		ON b.usersId = u.id 
		where b.id =  #{id}
        
        
        drop table member;
drop table board;
drop sequence board_seq;




CREATE TABLE member(
    id number(9,0),
    username VARCHAR2(12) not null,
    password VARCHAR2(20) not null, 
    CONSTRAINT member_pk PRIMARY KEY(id),
    CONSTRAINT member_username_uk UNIQUE(username)
 );
CREATE SEQUENCE member_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE comment(
 id number(9,0),
 comment VARCHAR2(200) not null,
 boardId 
 );
 
 
 select *
 from product;
 select *
 from customer;
 
 select *
 from orders;
 rollback;
 
 UPDATE product SET qty = 8 WHERE id =42;
 
 DELETE FROM orders WHERE id!=1;
 drop SEQUENCE order_seq;
 CREATE SEQUENCE order_seq
 start with 1
 INCREMENT by 1;
 commit;
 
 rollback;
 

-------------------------2022 09 01 Spring ( Mybatis JOINT ) ----------------------
create table users(
    id number primary key,
    username varchar2(20) UNIQUE,
    password varchar2(20),
    email varchar2(50),
    createdAt TIMESTAMP
);



CREATE SEQUENCE users_seq 
INCREMENT BY 1 
START WITH 1;

insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'ssar', '1234', 'ssar@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'cos', '1234', 'cos@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'hong', '1234', 'hong@nate.com', sysdate);
commit;

select *
from users;

select * from users where username='ssar' and password='1234';

drop table users;
drop SEQUENCE users_seq;

drop table boards;
drop SEQUENCE boardss_seq;

create table boards(
    id number primary key,
    title varchar2(150),
    content clob,
    usersId number,
    createdAt TIMESTAMP,
    CONSTRAINT boards_users_fk foreign key(usersId) references users(id)
);

CREATE SEQUENCE boards_seq 
INCREMENT BY 1 
START WITH 1;

select *
from boards;

SELECT b.title, b.content, u.username
FROM boards b LEFT OUTER JOIN users u
ON b.usersId = u.id
Where b.id = 1;

SELECT b.title, b.content, u.username
FROM boards b, users u
WHERE b.usersId = u.id;


		SELECT b.id, b.title, b.content, u.username, b.createdat
		from boards b LEFT OUTER JOIN  users u
		ON b.usersId = u.id 
		where b.id =  #{id}
```
