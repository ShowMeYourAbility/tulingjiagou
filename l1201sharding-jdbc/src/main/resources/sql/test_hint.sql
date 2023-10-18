#根据雪花算法自增主键分库，随机数字user_id奇偶分表
CREATE TABLE test_hint (
    cid BIGINT(20) PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    cstatus varchar(10) NOT NULL
);
CREATE TABLE test_hint_1 (
    cid BIGINT(20) PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    cstatus varchar(10) NOT NULL
);
CREATE TABLE test_hint_2 (
    cid BIGINT(20) PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    cstatus varchar(10) NOT NULL
);
CREATE TABLE test_hint_3 (
    cid BIGINT(20) PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    user_id BIGINT(20) NOT NULL,
    cstatus varchar(10) NOT NULL
);