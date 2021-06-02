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

# MySQL 데이터베이스 생성 및 테이블 생성

```sql
create database animal;
```

- MySQL 테이블 생성

```sql
use animal;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `roadAddress` varchar(100) NOT NULL,
  `jibunAddress` varchar(100) NOT NULL,
  `createDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `journal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `title` varchar(30) NOT NULL,
  `treatDate` varchar(10) NOT NULL,
  `visitHosptl` varchar(20) NOT NULL,
  `hosptlRank` int NOT NULL,
  `content` longtext,
  `createDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
  FOREIGN KRY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `animalhosptl` (
  `SIGUN_NM` varchar(10) NOT NULL,
  `BIZPLC_NM` varchar(40) NOT NULL,
  `BSN_STATE_NM` varchar(10) NOT NULL,
  `ROADNM_ZIP_CD` varchar(10) NOT NULL,
  `REFINE_ROADNM_ADDR` varchar(100) NOT NULL,
  `REFINE_LOTNO_ADDR` varchar(100) NOT NULL,
  `LOCPLC_FACLT_TELNO` varchar(25) NOT NULL,
  `REFINE_WGS84_LAT` float NOT NULL,
  `REFINE_WGS84_LOGT` float NOT NULL,
  `TOTAL_RANK` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `animalpharmacy` (
  `SIGUN_NM` varchar(10) NOT NULL,
  `BIZPLC_NM` varchar(40) NOT NULL,
  `BSN_STATE_NM` varchar(10) NOT NULL,
  `ROADNM_ZIP_CD` varchar(10) NOT NULL,
  `REFINE_ROADNM_ADDR` varchar(100) NOT NULL,
  `REFINE_LOTNO_ADDR` varchar(100) NOT NULL,
  `LOCPLC_FACLT_TELNO` varchar(25) NOT NULL,
  `REFINE_WGS84_LAT` float NOT NULL,
  `REFINE_WGS84_LOGT` float NOT NULL,
  `TOTAL_RANK` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `animalfacilit` (
  `SIGUN_NM` varchar(10) NOT NULL,
  `ENTRPS_NM` varchar(40) NOT NULL,
  `ACEPTNC_ABLTY_CNT` varchar(10) NOT NULL,
  `REFINE_ZIP_CD` varchar(10) NOT NULL,
  `REFINE_ROADNM_ADDR` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```