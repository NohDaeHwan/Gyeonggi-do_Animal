## JSP 동물지킴이 프로젝트

# 환경
- windows10
- jdk1.8
- tomcat9.0
- mysql8.0
- 공공 데이터 포털 API
- 주소 API
- AJAX
- JSON 파싱
- JSTL
- 인코딩 UTF-8
- GIT
- Summernote
- Bootstrap

- 데이터 포털 API_KEY="8bb16026b22043638166f4b4143dc2a2"
- 주소 API_KEY="U01TX0FVVEgyMDIxMDQwNDEyMzE1ODExMTAwNTA="

# Server.xml 설정 (프로토콜 오류 발생시)
- Connector URIEncoding="UTF-8" address="::1" port="8009" protocol="AJP/1.3" redirectPort="8443" secretRequired="false"

# MySQL 데이터베이스 생성 및 사용자 생성

```sql
create database animal;
```

- MySQL 테이블 생성
- animal 사용자로 접속
- use animal; 데이터 베이스 선택

```sql
CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(30) not null unique,
    password varchar(30) not null,
    email varchar(30) not null,
    address varchar(30),
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE board(
    id int primary key auto_increment,
    userId int,
    title varchar(30) not null,
    content longtext,
    readCount int default 0,
    createDate timestamp,
    foreign key (userId) references user (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE reply(
    id int primary key auto_increment,
    userId int,
    boardId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (boardId) references board (id) on delete cascade
) engine=InnoDB default charset=utf8;

CREATE TABLE journal(
    id int primary key auto_increment,
    userId int,
    title varchar(30) not null,
    content longtext,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
) engine=InnoDB default charset=utf8;
```