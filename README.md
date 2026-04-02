# devStudy - 초보 개발자를 위한 Java 학습 플랫폼

> 프로그래밍이 처음이어도 괜찮아요! 기초부터 차근차근, 퀴즈로 확인하면서 배워봐요.

---

## 목차

- [소개](#소개)
- [기술 스택](#기술-스택)
- [시작하기](#시작하기)
- [커리큘럼](#커리큘럼)
- [주요 기능](#주요-기능)
- [프로젝트 구조](#프로젝트-구조)

---

## 소개

**devStudy**는 Java를 처음 배우는 개발자를 위한 웹 기반 학습 플랫폼입니다.

- 12개의 단계별 레슨으로 Java 기초부터 객체지향까지 학습
- 레슨마다 코드 예제와 4문제 퀴즈 제공 (총 48문제)
- 퀴즈 결과와 해설로 즉각적인 피드백
- 세션 기반 진행도 추적

---

## 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.5 |
| View | Thymeleaf |
| Database | H2 (In-Memory) |
| ORM | Spring Data JPA |
| Build | Gradle |
| CSS | Bootstrap 5.3 |
| Syntax Highlight | Highlight.js |

---

## 시작하기

### 사전 요구사항

- JDK 17 이상

### 실행

```bash
# 프로젝트 디렉토리로 이동
cd /Users/hee/devStudy

# 빌드 및 실행
./gradlew bootRun
```

브라우저에서 `http://localhost:8081` 접속

> H2 in-memory DB를 사용하므로 별도의 데이터베이스 설치가 필요 없습니다.  
> 앱 실행 시 레슨/퀴즈 데이터가 자동으로 초기화됩니다.

### H2 콘솔 (개발용)

`http://localhost:8081/h2-console`

```
JDBC URL : jdbc:h2:mem:devstudy
Username : sa
Password : (비움)
```

---

## 커리큘럼

### Java 기초

| # | 레슨 | 난이도 | 설명 |
|---|------|--------|------|
| 1 | Java 시작하기 | 입문 | Hello World, main 메서드, 출력, 주석 |
| 2 | 변수와 자료형 | 입문 | int, double, boolean, char, String, final |
| 3 | 연산자 | 입문 | 산술, 비교, 논리, 증감, 복합 대입 연산자 |
| 4 | 조건문 | 초급 | if-else, switch, 삼항 연산자 |
| 5 | 반복문 | 초급 | for, while, do-while, for-each, break/continue |
| 7 | 메서드 | 초급 | 선언, 매개변수, 반환값, 오버로딩, 재귀 |
| 12 | 예외 처리 | 중급 | try-catch-finally, throw/throws, 사용자 정의 예외 |

### 자료구조

| # | 레슨 | 난이도 | 설명 |
|---|------|--------|------|
| 6 | 배열 | 초급 | 1차원/2차원 배열, 인덱스, Arrays 유틸 |
| 11 | 컬렉션 | 중급 | ArrayList, HashMap, 제네릭 |

### 객체지향(OOP)

| # | 레슨 | 난이도 | 설명 |
|---|------|--------|------|
| 8 | 객체와 클래스 | 중급 | 클래스, 객체, 생성자, 캡슐화, getter/setter |
| 9 | 상속과 다형성 | 중급 | extends, 오버라이딩, super, instanceof |
| 10 | 인터페이스와 추상 클래스 | 중급 | interface, abstract, implements, default 메서드 |

---

## 주요 기능

### 레슨 학습

- 개념 설명 (HTML 렌더링)
- 코드 예제 (Syntax Highlighting 적용)
- 코드 한 번에 복사 버튼
- 이전/다음 레슨 네비게이션

### 퀴즈

- 레슨당 4지선다 4문제
- 모든 문제에 답해야 제출 버튼 활성화
- 제출 후 문제별 정답/오답 표시
- 각 문제 해설 제공
- **60점 이상 통과** (재도전 횟수 제한 없음, 최고점 저장)

### 진행도 추적

- 세션 기반 진행도 저장
- 레슨별 완료 여부 및 최고 점수 기록
- 전체 달성률 표시

---

## 프로젝트 구조

```
devStudy/
├── src/
│   └── main/
│       ├── java/com/devstudy/
│       │   ├── DevStudyApplication.java     # 앱 진입점
│       │   ├── controller/
│       │   │   ├── HomeController.java      # 홈, 진행도
│       │   │   ├── LessonController.java    # 레슨 목록/상세
│       │   │   └── QuizController.java      # 퀴즈, 채점
│       │   ├── domain/
│       │   │   ├── Lesson.java              # 레슨 엔티티
│       │   │   ├── Question.java            # 퀴즈 문제 엔티티
│       │   │   └── UserProgress.java        # 진행도 엔티티
│       │   ├── repository/                  # Spring Data JPA
│       │   ├── service/
│       │   │   ├── LessonService.java       # 레슨 조회/네비게이션
│       │   │   └── QuizService.java         # 채점, 진행도 저장
│       │   └── init/
│       │       └── DataInitializer.java     # 초기 레슨/퀴즈 데이터
│       └── resources/
│           ├── application.properties
│           ├── templates/                   # Thymeleaf 템플릿
│           │   ├── layout.html
│           │   ├── index.html
│           │   ├── lessons.html
│           │   ├── lesson-detail.html
│           │   ├── quiz.html
│           │   ├── quiz-result.html
│           │   └── progress.html
│           └── static/
│               ├── css/style.css
│               └── js/main.js
├── build.gradle
└── settings.gradle
```

---

## 학습 팁

1. **직접 따라 치기** — 코드 예제를 복사하지 말고 IntelliJ나 VS Code에서 직접 타이핑하세요.
2. **반복이 최선** — 퀴즈를 틀렸다면 레슨을 다시 읽고 재도전하세요.
3. **모르면 검색** — 구글, Stack Overflow, [공식 Java 문서](https://docs.oracle.com/en/java/)를 적극 활용하세요.
4. **순서대로** — 레슨 번호 순서대로 학습하는 것을 권장합니다.
