DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id int auto_increment primary key,
    email varchar(64) unique not null,
    name varchar(64),
    nickname varchar(64),
    photo_url varchar(255)
);

INSERT INTO user(email, name, nickname, photo_url) values
                                                          ('hinux@aho.com', 'honuz', 'hodor', 'https://a.com/ppp'),
                                                          ('devdong@db.man', 'devdong', 'pororo', 'https://a.com/pororo'),
                                                          ('navergogo@naver.com', 'dhkim', 'DongHunKim', 'https://www.donghunkim.com');
explain select count(*) from user;

DESC user;

SELECT * from user;


DROP TABLE IF EXISTS food;

CREATE TABLE food(
  name varchar(64),
  cal int,
  user int references user(id)
);

DESC food;

SELECT * from food;


#  1:1 관계는 그냥 하나로 합쳐라, 왜냐하면 조인같은 헛짓거리를 하니까 말아지 >> 이미 사라진 user 와 1:1 관계였던 깃헙객체엔티티의 자리
#DROP TABLE IF EXISTS github;
#DESC github;
#INSERT INTO github(nickname, photo_url, user)
#    values('hodor', 'https://a.com/ppp',1),
#           ('pororo','https://a.com/pororo',2);
#SELECT * from github;