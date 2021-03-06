GRANT ALL PRIVILEGES ON ctbrg.* TO 'ctbrg'@'localhost' IDENTIFIED BY 'ctbrgctbrg2';

create database ctbrg character set utf8;

create table tb_chat_message (
	seq INT AUTO_INCREMENT,
	created_time DATETIME not null,
	user_seq INT not null,
	message VARCHAR(1000) not null,
	PRIMARY KEY (seq)
);

alter table tb_chat_message add column language VARCHAR(5);

create table tb_user (
	seq INT AUTO_INCREMENT,
	user_id VARCHAR(100) not null,
	password VARCHAR(100) not null,
	nickname VARCHAR(100) not null,
	image MEDIUMBLOB,
	join_date DATETIME not null,
	del_yn INT,
	PRIMARY KEY (seq),
	UNIQUE KEY (user_id),
	UNIQUE KEY (nickname)
);

alter table tb_user add column image MEDIUMBLOB;

alter table tb_user add column role VARCHAR(10) DEFAULT 'user';

alter table tb_user add column facebook_username VARCHAR(100) DEFAULT NULL;

insert into tb_user (user_id, password, nickname, join_date) values ('izeye@naver.com', '1234', 'izeye', now());
insert into tb_user (user_id, password, nickname, join_date) values ('icpu@naver.com', '1234', 'icpu', now());
insert into tb_user (user_id, password, nickname, join_date) values ('toujour19@gmail.com', '1234', 'toujour19', now());
insert into tb_user (user_id, password, nickname, join_date) values ('514193717@qq.com', '1234', 'grace', now());
insert into tb_user (user_id, password, nickname, join_date) values ('j.x.tic@hotmail.com', '1234', 'Heidi', now());
insert into tb_user (user_id, password, nickname, join_date) values ('huiyuan989@msn.cn', '1234', 'kelly', now());
insert into tb_user (user_id, password, nickname, join_date) values ('Minjw0@nate.com', '1234', 'min ji won', now());
insert into tb_user (user_id, password, nickname, join_date) values ('mateushenriquebrum@gmail.com', '1234', 'mateusbrum', now());
insert into tb_user (user_id, password, nickname, join_date) values ('enosent7@gmail.com', '1234', 'enosent7', now());
insert into tb_user (user_id, password, nickname, join_date) values ('oasishun@gmail.com', '1234', 'oasishun', now());
insert into tb_user (user_id, password, nickname, join_date) values ('eliza@eliza.com', '1234', 'Eliza', now());

update tb_user set role='staff' where nickname in ('izeye', 'icpu', 'toujour19', 'enosent7', 'oasishun');
update tb_user set role='staff' where nickname in ('Hanael');

create table tb_visit_log (
	seq INT AUTO_INCREMENT,
	visit_date DATETIME not null,
	ip VARCHAR(100) not null,
	uri VARCHAR(100) not null,
	referer VARCHAR(1000),
	user_agent VARCHAR(1000) not null,
	PRIMARY KEY (seq)
);

DROP TABLE tb_qrcode_history;
CREATE TABLE tb_qrcode_history (
	seq INT AUTO_INCREMENT,
	text VARCHAR(1000) NOT NULL,
	width INT NOT NULL,
	height INT NOT NULL,
	created_time DATETIME NOT NULL,
	ip VARCHAR(100) NOT NULL,
	PRIMARY KEY (seq)
);

-- For gamification
DROP TABLE tb_reward;
CREATE TABLE tb_reward (
	seq INT AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	alias VARCHAR(100) NOT NULL,
	description VARCHAR(1000) NOT NULL,
	point INT,
	PRIMARY KEY (seq)
);

INSERT INTO tb_reward (name, alias, description, point) VALUES ('Login Reward', 'LOGIN', 'Reward by login', 1);
INSERT INTO tb_reward (name, alias, description, point) VALUES ('Chat Reward', 'CHAT', 'Reward by chat', 2);

ALTER TABLE tb_user ADD COLUMN point INT DEFAULT 0;

DROP TABLE tb_game_score;
CREATE TABLE tb_game_score (
	game_seq INT,
	user_seq INT,
	score INT,
	play_time DATETIME
);

ALTER TABLE tb_user ADD COLUMN country_code CHAR(2) DEFAULT NULL;

DROP TABLE tb_image;
CREATE TABLE tb_image (
	seq INT AUTO_INCREMENT,
	type INT,
	name VARCHAR(100),
	PRIMARY KEY (seq)
);

INSERT INTO tb_image (type, name) VALUES (1, 'amulet');
INSERT INTO tb_image (type, name) VALUES (1, 'cake');
INSERT INTO tb_image (type, name) VALUES (1, 'keyboard_1');
INSERT INTO tb_image (type, name) VALUES (1, 'keyboard_2');
INSERT INTO tb_image (type, name) VALUES (1, 'keyboard_3');
INSERT INTO tb_image (type, name) VALUES (1, 'knob_1');
INSERT INTO tb_image (type, name) VALUES (1, 'knob_2');
INSERT INTO tb_image (type, name) VALUES (1, 'mouse');
INSERT INTO tb_image (type, name) VALUES (1, 'stairs');
INSERT INTO tb_image (type, name) VALUES (1, 'switch');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_3');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_5');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_8');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_12');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_13');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_14');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_15');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_16');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_17');
INSERT INTO tb_image (type, name) VALUES (2, 'elevator_led_18');

DROP TABLE tb_algorithm_contest_history;
CREATE TABLE tb_algorithm_contest_history (
	seq INT AUTO_INCREMENT,
	user_seq INT,
	contest_seq INT,
	-- 1: Programming Challenges
	problem_id VARCHAR(128),
	submit_id VARCHAR(128),
	submit_time DATETIME,
	runtime FLOAT,
	language_seq INT,
	-- 1: Java
	source_url VARCHAR(1024),
	created_time DATETIME,
	modified_time DATETIME,
	PRIMARY KEY(seq),
	UNIQUE KEY (user_seq, contest_seq, problem_id, submit_id)
);

CREATE TABLE tb_algorithm_problem (
	contest_seq INT,
	-- 1: Programming Challenges
	problem_id VARCHAR(128),
	title VARCHAR(1024),
	url VARCHAR(1024),
	created_time DATETIME,
	modified_time DATETIME,
	PRIMARY KEY (contest_seq, problem_id)
);