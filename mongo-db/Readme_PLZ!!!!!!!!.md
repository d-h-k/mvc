# 몽고디비 붙이는법 간단히 실습해보기

- 원문출처 : https://www.mongodb.com/compatibility/spring-boot
 
## 몽고디비 도커 이미지 설치
```shell
docker pull mongo:5.0
```

```shell
docker-compose up -d
```
- 혹은
```shell
docker run --name just-mongo -v ~/data:/data/db -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=password  mongo:5.0
```
-v ~/data:/data/db는 호스트(컨테이너를 구동하는 로컬 컴퓨터)의 ~/data 디렉터리와 컨테이너의 /data/db 디렉터리를 마운트시킨다. 
- 위처럼 볼륨을 설정하지 않으면 컨테이너를 삭제할 때 컨테이너에 저장되어있는 데이터도 삭제되기 때문에 복구할 수 없다.

```shell
docker start just-mongo
```
- 몽고디비 도커 시작
```shell
docker exec -it just-mongo bash
```
- 몽고디비 도커 bash쉘 접근

```
use admin
db.createUser({ user: 'root', pwd: 'password', roles: ['root'] })
```
