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

## 데이터 베이스 설정
1. MySQL 실행
   
```bash
docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=todo -p 3306:3306 -d mysql:8.0
```

2. 백업된 todo.sql 파일을 **/resources/database 에서 가져와 실행하세요.
 
```bash
mysql -u root -p todo < todo.sql
```

## 주력으로 사용한 라이브러리
- Lombok
  - java 코드에서 반복적으로 사용하는 코드를 줄여주는 라이브러리를 사용했습니다. 이 라이브러리는 어노테이션 기반으로 Getter, Setter, Builder, NoArgsConstructor, AllArgsConstructor, RequiredArgsConstructor 을 사용하여 코드의 가독성을 높이고 유지보수성을 늘렸습니다.
    특히, @Builder 패턴을 자주 활용하여 객체 생성 시 코드의 직관성을 높이고, 불필요한 생성자 오버로딩을 줄일 수 있도록 하였습니다.
## API 명세서

![swaggerapi명세서](https://github.com/user-attachments/assets/eb78c6fc-1ecf-4229-97f9-2c27e09011f1)

## Test Case
테스트 케이스에 대한 자세한 내용은 [Test Case Documentation.md](Test%20Case%20Documentation.md) 문서를 참고해 주세요.

### 진행완료
- BackEnd
  - Auth : 로그인, 회원가입, 로그아웃 구현 완료
  - Calendar : CRUD, 초대( 초대, 승인, 거절)
  - Todo : CRUD
- FrontEnd
  - Auth : 로그인, 회원가입, 로그아웃 구현 완료
  - Calendar : CRUD, 초대 (초대하기)




