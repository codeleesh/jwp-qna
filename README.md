# QNA
## 진행 방법
* QnA 서비스를 만들어가면서 JPA로 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 1단계 - 엔티티 매핑
 - 아래의 DDL(Data Definition Language)을 보고 유추하여 엔티티 클래스와 리포지토리 클래스를 작성해 본다.
   - [X] 답변
   - [X] 삭제이력
   - [X] 질문
   - [X] 사용자
 - `@DataJpaTest`를 사용하여 학습 테스트를 해 본다.
   - 답변 
     - [X] 답변 생성 테스트
     - [X] 생성된 답변 조회 테스트
     - [X] 삭제된 답변 조회 테스트
   - 질문
     - [X] 질문 생성 테스트
     - [X] 생성된 질문 조회 테스트
     - [X] 삭제된 질문 조회 테스트
     - [X] 질문의 대한 답변 생성 테스트
   - 사용자
     - [X] 사용자 생성 테스트
     - [X] 사용자 아이디 조회 테스트
     - [X] 사용자 정보 수정 테스트
     - [X] 사용자 이름과 이메일 일치 여부 테스트
     - [X] 게스트 사용자 여부 테스트

## 2단계
 - QnA 서비스를 만들어가면서 JPA로 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다.
   - 객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.
     - 답변
       - 질문과의 관계
         - [X] 답변은 하나의 질문에만 속할 수 있다.
         - [X] 답변과 질문은 다대일(N:1) 관계
       - 작성자(사용자)와의 관계
         - [X] 답변은 하나의 작성자에게만 속할 수 있다.
         - [X] 답변과 작성자는 다대일(N:1) 관계
         - [X] 작성자와 답변은 일대다(1:N) 관계
     - 삭제 이력
       - 작성자(사용자)와의 관계
         - [X] 삭제 이력은 하나의 작성자에게만 속할 수 있다.
         - [X] 삭제 이력과 작성자는 다대일(N:1) 관계
         - [X] 작성자와 삭제 이력은 일대다(1:N) 관계
     - 질문
       - 작성자(사용자)의 관계
         - [X] 질문은 하나의 작성자에게만 속할 수 있다.
         - [X] 질문과 작성자는 다대일(N:1) 관계
         - [X] 작성자와 질문은 일대다(1:N) 관계
       - 답변과의 관계
         - [X] 하나의 질문은 여러 답변을 작성할 수 있다.
         - [X] 질문과 달변은 일대다(1:N) 관계
## 3단계
- QnA 서비스를 만들어가면서 JPA로 실제 도메인 모델을 어떻게 구성하고 객체와 테이블을 어떻게 매핑해야 하는지 알아본다. 
- 객체
  - 답변
    - [X] 로그인한 사용자와 답변자가 같은 경우 삭제할 수 있다.
      - [X] 답변 삭제 시 삭제 히스토리가 남아야 한다.
      - [X] 답변 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
      - [X] 답변을 삭제한 시간과 삭제 이력이 생성된 시간이 동일해야 한다.
    - [X] 로그인한 사용자와 답변자가 다른 경우 삭제할 수 없다.
  - 답변들
    - [X] 질문자가 질문 삭제 모든 답변자가 질문자와 같다면 삭제할 수 있다.
    - [X] 질문자가 질문 삭제 시 질문자가 아닌 다른 사용자의 답변이 있다면 삭제할 수 없다.
  - 질문
    - [X] 로그인한 사용자와 질문자가 같은 경우 답변이 없다면 삭제할 수 있다.
      - [X] 질문 삭제 시 삭제 히스토리가 남아야 한다.
      - [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
      - [X] 답변을 삭제한 시간과 삭제 이력이 생성된 시간이 동일해야 한다.
    - [X] 질문자가 질문 삭제 시 다른 사용자의 답변이 있다면 삭제할 수 없다.
    - [X] 질문자가 질문 삭제 시 모든 답변자가 질문자와 같다면 삭제할 수 있다.
      - [X] 질문 삭제 시 삭제 히스토리가 남아야 한다.
      - [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
      - [X] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
  - 사용자
    - [X] 게스트 사용자인지 확인할 수 있다. 