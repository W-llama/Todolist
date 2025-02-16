# 📌 클러쉬 신입 개발자 과제 (일정관리 앱)

## 개요

### 목적
클러쉬 신입 개발자 과제
- 일정관리 앱 개발
  - Calendar
  - Todo

## 주요기능
1. Auth : 로그인 회원가입이 가능합니다.
2. Calendar : 일정관리가 가능합니다. ( 생성, 조회, 수정, 삭제, 초대(승인, 거부))
3. Todo : 할일관리가 가능합니다. ( 생성, 조회, 수정, 삭제)

## ERD

![todolist](https://github.com/user-attachments/assets/78ffb866-b62d-4c51-9e91-b0d4a65f7c74)

## 실행하기 위해 필요 정보

```.env
MYSQL_URL={YOUR_MYSQL_URI}
MYSQL_USERNAME={YOUR_MYSQL_USERNAME}
MYSQL_PW={YOUR_MYSQL_PW}
JWT_SECRET_KEY={YOUR_JWT_SECRET_KEY}
```

이 프로젝트는 bootstrap을 사용합니다. 다음 링크에서 css, js를 다운받고

https://getbootstrap.kr/docs/5.3/getting-started/download/

**/resources/static 내에 css, js 를 넣어주세요

## BackEnd Test
아직 프론트 개발이 완료되지 않은 상태이므로 미완성 된 부분의 테스트는 Swagger를 통해 진행 부탁드리겠습니다.

###진행완료
- BackEnd
  - Auth : 로그인, 회원가입, 로그아웃 구현 완료
  - Calendar : CRUD, 초대( 초대, 승인, 거절)
  - Todo : CRUD
- FrontEnd
  - Auth : 로그인, 회원가입, 로그아웃 구현 완료
  - Calendar : CRUD, 초대 (초대하기)

## 데이터 베이스 설정
1. MySQL 실행
   
```bash
docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=todo -p 3306:3306 -d mysql:8.0
```

2. 백업된 todo.sql 파일을 **/resources/database 에서 가져와 실행하세요.
 
```bash
mysql -u root -p todo < todo.sql
```

## Test Case
테스트 케이스에 대한 자세한 내용은 [Test Case Documentation.md](Test%20Case%20Documentation.md) 문서를 참고해 주세요.

