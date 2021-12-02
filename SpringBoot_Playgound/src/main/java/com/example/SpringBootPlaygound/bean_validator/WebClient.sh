curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"안녕하세요 반가워용\"}" http://127.0.0.1:8080/valid

curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"익셉션 발생해유======================================================================================\"}" http://127.0.0.1:8080/valid