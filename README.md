# Spring-Shop

-------------------------------------------------------
## Proceedings
11/1 ~ 11/4 : Settings <br>
11/4 ~ 11/7 : <br>
- ljt528 : SignUp/SignIn/SignOut
- xkimido : Product
- nohheeyeon : Cart

~11/21 : <br>
- ljt528 : SignUp/SignIn/SignOut Completed
- xkimido : Product, Order Completed
- nohheeyeon : Cart, Order Completed

11/21 : <br>
- Spring Boot Project Successfully Launched and Merged into Main Branch
  <img width="715" alt="image" src="https://github.com/ShopSpringBoot/Spring-Shop/assets/130336617/223ab923-e398-4bd3-844b-104cf5475e70">
---------------
## 변수명

1. 회원 (User):
    - 회원 ID (UserID, PK)
    - 이름 (Name)
    - 이메일 (Email)
    - 비밀번호 (Password)
    - 전화번호 (Phone)

2. 역할 (Role):
    - 역할 ID (RoleID, PK)
    - 역할 이름 (RoleName)

3. 사용자 역할 (User_Role):
    - ID (ID, PK)
    - 회원 ID (UserID, FK)
    - 역할 ID (RoleID, FK)

4. 상품 카테고리 (Category):
    - 카테고리 ID (CategoryID, PK)
    - 카테고리 이름 (CategoryName)

5. 상품 (Product):
    - 상품 ID (ProductID, PK)
    - 상품명 (ProductName)
    - 가격 (Price)
    - 설명 (Description)
    - 이미지 URL (ImageURL)
    - 등록일 (DateAdded)
    - 카테고리 ID (CategoryID, FK)

6. 주문 (Order):
    - 주문 ID (OrderID, PK)
    - 주문 일자 (OrderDate)
    - 주문 상태 (OrderStatus)
    - 회원 ID (UserID, FK)

7. 주문 상세 (Order_Detail):
    - 주문 상세 ID (OrderDetailID, PK)
    - 주문 ID (OrderID, FK)
    - 상품 ID (ProductID, FK)
    - 수량 (Quantity)
    - 가격 (Price)

8. 장바구니 (Cart):
    - 장바구니 ID (CartID, PK)
    - 회원 ID (UserID, FK)

9. 장바구니 상세 (Cart_Detail):
    - 장바구니 상세 ID (CartDetailID, PK)
    - 장바구니 ID (CartID, FK)
    - 상품 ID (ProductID, FK)
    - 수량 (Quantity)

------------------------
## 예상 구현 기능

### 사용자 역할 및 권한:
- 비회원: 로그인 없이 제품만 조회 가능
- 회원: 제품 조회, 구매, 장바구니 추가 등의 기능 사용 가능
- 관리자: 회원 관리, 상품 관리, 공지사항 관리 등의 광범위한 권한 부여

### 주요 기능:

1. 회원가입 (SignUp):
    - 필요 정보: 이름, 생년월일, 주소, 전화번호, 아이디, 비밀번호
    - OAuth를 통한 소셜 로그인 옵션 추가 (예: 카카오, 네이버)

2. 로그인/로그아웃 (Login/Logout):
    - 아이디와 비밀번호를 통한 로그인
    - 소셜 로그인 옵션 (OAuth)
    - 로그인 사용자만 특정 페이지에 접근 권한 부여

3. 상품 관리 (Product):
    - 상품 정보: 이름, 상품번호, 추가, 삭제, 수량, 가격, 카테고리 등
    - 관리자만 상품 추가 및 삭제 권한 부여

4. 주문 관리 (Order):
    - 주문 정보: 주문자, 상품번호, 주문번호, 주문일자, 주문상태, 배송상태, 가격, 결제수단, 수령인 정보 등
    - 주문 상태 및 배송 상태 업데이트 기능

5. 장바구니 (Cart):
    - 장바구니에 상품 담기, 삭제, 수량 조절
    - 금액 및 배송비 계산
    - 상품 정보 표시 (이름, 카테고리 등)

6. 실시간 채팅 (Chat):
    - 웹사이트 내에서 실시간 채팅 구현
    - 사용자 간 및 관리자와 사용자 간의 채팅 지원

7. 게시판 (Board):
    - 웹사이트 내에서 게시판 구현
    - 게시글 등록, 조회, 수정, 삭제, 검색
    - 게시글 권한 설정, 추천 및 이미지 추가 기능
    - 사용자 아이디 검색 기능

8. 메인 페이지(Main Page):
    - 웹사이트 내에서 메인 페이지 구현

9. 댓글 (Comment):
    - 댓글 등록, 수정, 삭제
    - 댓글 추천 기능

10. 프로필(Profile):
- 사용자 프로필 설정기능

### DB 연결
- MySQL 데이터베이스 사용 : 프로젝트의 백엔드 데이터 저장소로 MySQL 데이터베이스를 사용

### 추가 기능 및 기술

##### 사용자 중심 기능:
1. 고급 검색 및 필터링 기능
2. 사용자 리뷰 및 평점 시스템
3. 위시리스트 기능
4. 개인화된 제품 추천

##### 통신 및 마케팅:
1. 모바일 알림 시스템
2. 가격 추적 및 알림
3. 자동화된 마케팅 이메일
4. 다국어 지원

##### 기술적 확장성 및 유연성:
1. 마이크로서비스 아키텍처
2. 컨테이너화 및 오케스트레이션
3. 메시지 큐 시스템
4. CI/CD 파이프라인
