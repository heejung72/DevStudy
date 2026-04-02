package com.devstudy.init;

import com.devstudy.domain.Lesson;
import com.devstudy.domain.Question;
import com.devstudy.repository.LessonRepository;
import com.devstudy.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (lessonRepository.count() > 0) return;
        log.info("초기 학습 데이터 로딩 중...");
        initLessons();
        log.info("학습 데이터 로딩 완료!");
    }

    private void initLessons() {

        // =====================================================================
        // 레슨 1: Java 시작하기
        // =====================================================================
        Lesson l1 = lessonRepository.save(Lesson.builder()
                .title("Java 시작하기")
                .category("Java 기초")
                .orderNum(1)
                .difficulty("입문")
                .description("Java 프로그래밍 언어의 기본 개념과 첫 번째 프로그램 작성법을 배웁니다.")
                .content("""
                        <h3>Java란 무엇인가요?</h3>
                        <p>Java는 1995년 Sun Microsystems에서 개발한 객체지향 프로그래밍 언어입니다.<br>
                        <strong>"Write Once, Run Anywhere"</strong> — 한 번 작성하면 어디서든 실행되는 것이 특징입니다.</p>

                        <h3>Java의 특징</h3>
                        <ul>
                            <li><strong>플랫폼 독립성</strong>: JVM(Java Virtual Machine) 위에서 실행</li>
                            <li><strong>객체지향</strong>: 모든 것이 객체로 구성</li>
                            <li><strong>강한 타입 언어</strong>: 변수 타입을 반드시 선언</li>
                            <li><strong>자동 메모리 관리</strong>: Garbage Collector가 메모리 관리</li>
                        </ul>

                        <h3>Java 실행 과정</h3>
                        <p>소스코드(.java) → 컴파일(javac) → 바이트코드(.class) → JVM 실행</p>

                        <h3>main 메서드</h3>
                        <p>Java 프로그램의 시작점입니다. <code>public static void main(String[] args)</code>는 반드시 이 형태여야 합니다.</p>

                        <h3>출력하기</h3>
                        <p><code>System.out.println()</code>은 콘솔에 텍스트를 출력하고 줄 바꿈을 합니다.<br>
                        <code>System.out.print()</code>는 줄 바꿈 없이 출력합니다.</p>

                        <h3>주석(Comment)</h3>
                        <p>코드에 설명을 다는 것입니다. 실행되지 않습니다.</p>
                        <ul>
                            <li>한 줄 주석: <code>// 이것은 주석입니다</code></li>
                            <li>여러 줄 주석: <code>/* 여러 줄 주석 */</code></li>
                            <li>문서 주석: <code>/** 문서화 주석 */</code></li>
                        </ul>
                        """)
                .codeExample("""
                        // 첫 번째 Java 프로그램
                        public class HelloWorld {

                            // main 메서드: 프로그램의 시작점
                            public static void main(String[] args) {

                                // 화면에 출력하기
                                System.out.println("안녕하세요, Java!");
                                System.out.println("Hello, World!");

                                // 줄 바꿈 없이 출력
                                System.out.print("이름: ");
                                System.out.println("홍길동");

                                // 숫자 출력
                                System.out.println(2024);
                                System.out.println(3.14);
                            }
                        }

                        /* 출력 결과:
                         * 안녕하세요, Java!
                         * Hello, World!
                         * 이름: 홍길동
                         * 2024
                         * 3.14
                         */
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l1).orderNum(1)
                        .questionText("Java 프로그램의 시작점이 되는 메서드는 무엇인가요?")
                        .optionA("start()")
                        .optionB("main()")
                        .optionC("run()")
                        .optionD("init()")
                        .correctAnswer("B")
                        .explanation("Java 프로그램은 반드시 public static void main(String[] args) 메서드에서 시작합니다.")
                        .build(),
                Question.builder().lesson(l1).orderNum(2)
                        .questionText("콘솔에 텍스트를 출력하고 줄 바꿈까지 하는 메서드는?")
                        .optionA("System.out.print()")
                        .optionB("System.out.write()")
                        .optionC("System.out.println()")
                        .optionD("System.out.display()")
                        .correctAnswer("C")
                        .explanation("println()은 print line의 약자로 출력 후 자동으로 줄 바꿈을 합니다. print()는 줄 바꿈이 없습니다.")
                        .build(),
                Question.builder().lesson(l1).orderNum(3)
                        .questionText("Java 소스 파일의 확장자는 무엇인가요?")
                        .optionA(".class")
                        .optionB(".jar")
                        .optionC(".jav")
                        .optionD(".java")
                        .correctAnswer("D")
                        .explanation("Java 소스 파일은 .java 확장자를 사용합니다. 컴파일하면 .class 바이트코드 파일이 생성됩니다.")
                        .build(),
                Question.builder().lesson(l1).orderNum(4)
                        .questionText("Java에서 한 줄 주석을 작성하는 방법은?")
                        .optionA("# 주석")
                        .optionB("// 주석")
                        .optionC("-- 주석")
                        .optionD("** 주석")
                        .correctAnswer("B")
                        .explanation("Java에서 한 줄 주석은 //로 시작합니다. /* */는 여러 줄 주석입니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 2: 변수와 자료형
        // =====================================================================
        Lesson l2 = lessonRepository.save(Lesson.builder()
                .title("변수와 자료형")
                .category("Java 기초")
                .orderNum(2)
                .difficulty("입문")
                .description("데이터를 저장하는 변수와 기본 자료형(int, double, boolean, char, String)을 배웁니다.")
                .content("""
                        <h3>변수(Variable)란?</h3>
                        <p>데이터를 저장하는 <strong>메모리 공간</strong>에 붙인 이름입니다.<br>
                        Java에서는 변수를 사용하기 전에 반드시 <strong>타입을 선언</strong>해야 합니다.</p>

                        <h3>기본 자료형 (Primitive Type)</h3>
                        <table class="table table-bordered">
                            <thead><tr><th>타입</th><th>크기</th><th>범위</th><th>예시</th></tr></thead>
                            <tbody>
                                <tr><td><code>byte</code></td><td>1byte</td><td>-128 ~ 127</td><td>byte b = 10;</td></tr>
                                <tr><td><code>short</code></td><td>2byte</td><td>-32,768 ~ 32,767</td><td>short s = 1000;</td></tr>
                                <tr><td><code>int</code></td><td>4byte</td><td>약 ±21억</td><td>int i = 100;</td></tr>
                                <tr><td><code>long</code></td><td>8byte</td><td>매우 큰 정수</td><td>long l = 100L;</td></tr>
                                <tr><td><code>float</code></td><td>4byte</td><td>소수점 7자리</td><td>float f = 3.14f;</td></tr>
                                <tr><td><code>double</code></td><td>8byte</td><td>소수점 15자리</td><td>double d = 3.14;</td></tr>
                                <tr><td><code>boolean</code></td><td>1byte</td><td>true / false</td><td>boolean b = true;</td></tr>
                                <tr><td><code>char</code></td><td>2byte</td><td>문자 1개</td><td>char c = 'A';</td></tr>
                            </tbody>
                        </table>

                        <h3>참조 자료형 (Reference Type)</h3>
                        <p><code>String</code>은 가장 많이 사용되는 참조 자료형입니다.<br>
                        문자열(여러 문자의 모음)을 저장합니다.</p>

                        <h3>변수 명명 규칙</h3>
                        <ul>
                            <li>소문자로 시작 (camelCase 권장): <code>myName</code>, <code>totalCount</code></li>
                            <li>영문자, 숫자, _, $ 사용 가능</li>
                            <li>숫자로 시작 불가</li>
                            <li>Java 예약어 사용 불가 (int, class 등)</li>
                        </ul>

                        <h3>상수 (final)</h3>
                        <p><code>final</code> 키워드로 선언하면 값을 변경할 수 없습니다.<br>
                        상수 이름은 대문자 + 언더스코어로 표기합니다: <code>MAX_SIZE</code></p>
                        """)
                .codeExample("""
                        public class Variables {
                            public static void main(String[] args) {

                                // 정수형
                                int age = 25;
                                long population = 5_200_000_000L;  // L 접미사 필수

                                // 실수형
                                double pi = 3.14159;
                                float temperature = 36.5f;  // f 접미사 필수

                                // 문자형
                                char grade = 'A';

                                // 논리형
                                boolean isStudent = true;
                                boolean isAdult = false;

                                // 문자열
                                String name = "홍길동";
                                String greeting = "안녕하세요!";

                                // 출력
                                System.out.println("이름: " + name);
                                System.out.println("나이: " + age);
                                System.out.println("성인 여부: " + isAdult);
                                System.out.println("학점: " + grade);

                                // 상수 선언
                                final int MAX_SCORE = 100;
                                final String APP_NAME = "devStudy";

                                // 타입 추론 (var - Java 10+)
                                var message = "Java 공부 중!";  // String으로 추론
                                var count = 42;                  // int로 추론

                                System.out.println(message + " count: " + count);
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l2).orderNum(1)
                        .questionText("정수를 저장할 때 가장 일반적으로 사용하는 자료형은?")
                        .optionA("long")
                        .optionB("short")
                        .optionC("int")
                        .optionD("byte")
                        .correctAnswer("C")
                        .explanation("int는 4바이트로 약 ±21억 범위를 가지며 가장 일반적으로 사용되는 정수형입니다.")
                        .build(),
                Question.builder().lesson(l2).orderNum(2)
                        .questionText("참(true) 또는 거짓(false) 값만 저장할 수 있는 자료형은?")
                        .optionA("char")
                        .optionB("bit")
                        .optionC("flag")
                        .optionD("boolean")
                        .correctAnswer("D")
                        .explanation("boolean 자료형은 true 또는 false 값만 가질 수 있습니다.")
                        .build(),
                Question.builder().lesson(l2).orderNum(3)
                        .questionText("다음 중 올바른 long 타입 변수 선언은?")
                        .optionA("long n = 1000000;")
                        .optionB("long n = 1000000L;")
                        .optionC("long n = 1000000.0;")
                        .optionD("long n = 1000000f;")
                        .correctAnswer("B")
                        .explanation("long 타입 리터럴은 끝에 L 또는 l을 붙여야 합니다. 붙이지 않으면 int 범위 초과 시 오류가 발생할 수 있습니다.")
                        .build(),
                Question.builder().lesson(l2).orderNum(4)
                        .questionText("값을 변경할 수 없는 상수를 선언하는 키워드는?")
                        .optionA("const")
                        .optionB("static")
                        .optionC("final")
                        .optionD("immutable")
                        .correctAnswer("C")
                        .explanation("Java에서 상수는 final 키워드로 선언합니다. C/C++의 const와 달리 Java는 final을 사용합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 3: 연산자
        // =====================================================================
        Lesson l3 = lessonRepository.save(Lesson.builder()
                .title("연산자")
                .category("Java 기초")
                .orderNum(3)
                .difficulty("입문")
                .description("산술, 비교, 논리, 대입 연산자를 배우고 계산을 수행하는 방법을 익힙니다.")
                .content("""
                        <h3>산술 연산자</h3>
                        <table class="table table-bordered">
                            <thead><tr><th>연산자</th><th>의미</th><th>예시</th><th>결과</th></tr></thead>
                            <tbody>
                                <tr><td>+</td><td>덧셈</td><td>10 + 3</td><td>13</td></tr>
                                <tr><td>-</td><td>뺄셈</td><td>10 - 3</td><td>7</td></tr>
                                <tr><td>*</td><td>곱셈</td><td>10 * 3</td><td>30</td></tr>
                                <tr><td>/</td><td>나눗셈</td><td>10 / 3</td><td>3 (정수÷정수=정수)</td></tr>
                                <tr><td>%</td><td>나머지</td><td>10 % 3</td><td>1</td></tr>
                            </tbody>
                        </table>

                        <h3>비교 연산자 (결과: boolean)</h3>
                        <table class="table table-bordered">
                            <thead><tr><th>연산자</th><th>의미</th></tr></thead>
                            <tbody>
                                <tr><td>==</td><td>같다</td></tr>
                                <tr><td>!=</td><td>다르다</td></tr>
                                <tr><td>&gt;</td><td>크다</td></tr>
                                <tr><td>&lt;</td><td>작다</td></tr>
                                <tr><td>&gt;=</td><td>크거나 같다</td></tr>
                                <tr><td>&lt;=</td><td>작거나 같다</td></tr>
                            </tbody>
                        </table>

                        <h3>논리 연산자</h3>
                        <ul>
                            <li><code>&&</code> (AND): 둘 다 true여야 true</li>
                            <li><code>||</code> (OR): 하나라도 true면 true</li>
                            <li><code>!</code> (NOT): true↔false 반전</li>
                        </ul>

                        <h3>증감 연산자</h3>
                        <ul>
                            <li><code>++</code>: 1 증가 (i++: 후위, ++i: 전위)</li>
                            <li><code>--</code>: 1 감소</li>
                        </ul>

                        <h3>복합 대입 연산자</h3>
                        <p><code>+=</code>, <code>-=</code>, <code>*=</code>, <code>/=</code>, <code>%=</code></p>
                        """)
                .codeExample("""
                        public class Operators {
                            public static void main(String[] args) {

                                // 산술 연산자
                                int a = 10, b = 3;
                                System.out.println(a + b);   // 13
                                System.out.println(a - b);   // 7
                                System.out.println(a * b);   // 30
                                System.out.println(a / b);   // 3 (정수 나눗셈!)
                                System.out.println(a % b);   // 1 (나머지)

                                // 실수 나눗셈
                                double result = (double) a / b;
                                System.out.println(result);  // 3.3333...

                                // 비교 연산자
                                System.out.println(a > b);   // true
                                System.out.println(a == b);  // false
                                System.out.println(a != b);  // true

                                // 논리 연산자
                                boolean x = true, y = false;
                                System.out.println(x && y);  // false
                                System.out.println(x || y);  // true
                                System.out.println(!x);      // false

                                // 실용 예제: 짝수/홀수 판별
                                int num = 7;
                                boolean isEven = (num % 2 == 0);
                                System.out.println(num + "은 짝수? " + isEven);  // false

                                // 복합 대입
                                int score = 50;
                                score += 30;  // score = score + 30
                                System.out.println(score);   // 80
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l3).orderNum(1)
                        .questionText("int a = 10, b = 3; 일 때 a % b의 결과는?")
                        .optionA("3")
                        .optionB("0")
                        .optionC("1")
                        .optionD("3.33")
                        .correctAnswer("C")
                        .explanation("% 연산자는 나머지를 구합니다. 10 ÷ 3 = 3 나머지 1이므로 결과는 1입니다.")
                        .build(),
                Question.builder().lesson(l3).orderNum(2)
                        .questionText("int a = 5, b = 3; 일 때 a / b의 결과는?")
                        .optionA("1.666...")
                        .optionB("2")
                        .optionC("1")
                        .optionD("1.5")
                        .correctAnswer("C")
                        .explanation("정수 / 정수는 소수점을 버리고 정수만 반환합니다. 5 / 3 = 1 (나머지 2는 버림)")
                        .build(),
                Question.builder().lesson(l3).orderNum(3)
                        .questionText("true && false의 결과는?")
                        .optionA("true")
                        .optionB("false")
                        .optionC("null")
                        .optionD("0")
                        .correctAnswer("B")
                        .explanation("&& (AND) 연산자는 두 피연산자가 모두 true일 때만 true입니다. 하나라도 false면 false입니다.")
                        .build(),
                Question.builder().lesson(l3).orderNum(4)
                        .questionText("score += 10; 과 동일한 표현은?")
                        .optionA("score = score - 10;")
                        .optionB("score = 10;")
                        .optionC("score = score + 10;")
                        .optionD("score++;")
                        .correctAnswer("C")
                        .explanation("+= 는 복합 대입 연산자로 score = score + 10; 과 동일합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 4: 조건문
        // =====================================================================
        Lesson l4 = lessonRepository.save(Lesson.builder()
                .title("조건문")
                .category("Java 기초")
                .orderNum(4)
                .difficulty("초급")
                .description("if-else, switch 조건문으로 조건에 따라 다른 코드를 실행하는 방법을 배웁니다.")
                .content("""
                        <h3>if 문</h3>
                        <p>조건이 <code>true</code>일 때만 코드 블록을 실행합니다.</p>
                        <pre><code>if (조건) {
                            // 조건이 true일 때 실행
                        }</code></pre>

                        <h3>if-else 문</h3>
                        <p>조건이 true이면 if 블록, false이면 else 블록을 실행합니다.</p>

                        <h3>if-else if-else 문</h3>
                        <p>여러 조건을 순서대로 검사합니다. 첫 번째 true 조건의 블록만 실행됩니다.</p>

                        <h3>중첩 if 문</h3>
                        <p>if 문 안에 또 다른 if 문을 작성할 수 있습니다.</p>

                        <h3>switch 문</h3>
                        <p>하나의 변수 값에 따라 여러 경우를 처리합니다.<br>
                        각 case 끝에 <code>break</code>를 넣지 않으면 다음 case도 실행됩니다 (fall-through).</p>

                        <h3>삼항 연산자</h3>
                        <p>간단한 조건문을 한 줄로 작성: <code>조건 ? 참일때값 : 거짓일때값</code></p>
                        """)
                .codeExample("""
                        public class Conditions {
                            public static void main(String[] args) {

                                // if-else 기본
                                int score = 75;
                                if (score >= 90) {
                                    System.out.println("A학점");
                                } else if (score >= 80) {
                                    System.out.println("B학점");
                                } else if (score >= 70) {
                                    System.out.println("C학점");
                                } else {
                                    System.out.println("D학점");
                                }
                                // 출력: C학점

                                // switch 문
                                int day = 3;
                                switch (day) {
                                    case 1: System.out.println("월요일"); break;
                                    case 2: System.out.println("화요일"); break;
                                    case 3: System.out.println("수요일"); break;
                                    case 4: System.out.println("목요일"); break;
                                    case 5: System.out.println("금요일"); break;
                                    default: System.out.println("주말");
                                }
                                // 출력: 수요일

                                // 삼항 연산자
                                int age = 20;
                                String status = (age >= 18) ? "성인" : "미성년자";
                                System.out.println(status);  // 성인

                                // 짝수/홀수 판별
                                int num = 7;
                                if (num % 2 == 0) {
                                    System.out.println(num + "은 짝수");
                                } else {
                                    System.out.println(num + "은 홀수");
                                }
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l4).orderNum(1)
                        .questionText("int x = 5; 일 때 다음 코드의 출력은?\nif (x > 3) { System.out.println(\"A\"); } else { System.out.println(\"B\"); }")
                        .optionA("B")
                        .optionB("A와 B 모두 출력")
                        .optionC("A")
                        .optionD("아무것도 출력 안 함")
                        .correctAnswer("C")
                        .explanation("x = 5이고 5 > 3은 true이므로 if 블록의 \"A\"가 출력됩니다.")
                        .build(),
                Question.builder().lesson(l4).orderNum(2)
                        .questionText("switch 문에서 break를 생략하면 어떻게 되나요?")
                        .optionA("컴파일 오류가 발생한다")
                        .optionB("해당 case만 실행된다")
                        .optionC("다음 case도 계속 실행된다 (fall-through)")
                        .optionD("default 블록으로 이동한다")
                        .correctAnswer("C")
                        .explanation("break 없이 case를 작성하면 fall-through가 발생하여 다음 case 코드가 계속 실행됩니다.")
                        .build(),
                Question.builder().lesson(l4).orderNum(3)
                        .questionText("삼항 연산자 (10 > 5) ? \"크다\" : \"작다\" 의 결과는?")
                        .optionA("작다")
                        .optionB("크다")
                        .optionC("true")
                        .optionD("오류")
                        .correctAnswer("B")
                        .explanation("10 > 5는 true이므로 ? 앞의 조건이 참일 때의 값인 \"크다\"가 반환됩니다.")
                        .build(),
                Question.builder().lesson(l4).orderNum(4)
                        .questionText("여러 조건을 순서대로 검사할 때 가장 적합한 구조는?")
                        .optionA("중첩 if만 사용")
                        .optionB("switch 문")
                        .optionC("if-else if-else 문")
                        .optionD("삼항 연산자")
                        .correctAnswer("C")
                        .explanation("if-else if-else 구조는 여러 조건을 순서대로 검사하고 첫 번째 true 조건만 실행하는 데 적합합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 5: 반복문
        // =====================================================================
        Lesson l5 = lessonRepository.save(Lesson.builder()
                .title("반복문")
                .category("Java 기초")
                .orderNum(5)
                .difficulty("초급")
                .description("for, while, do-while 반복문으로 코드를 반복 실행하는 방법을 배웁니다.")
                .content("""
                        <h3>for 문</h3>
                        <p>반복 횟수가 정해진 경우에 사용합니다.</p>
                        <pre><code>for (초기화; 조건; 증감) {
                            // 반복 실행할 코드
                        }</code></pre>

                        <h3>while 문</h3>
                        <p>조건이 true인 동안 계속 반복합니다. 반복 횟수를 모를 때 적합합니다.</p>
                        <pre><code>while (조건) {
                            // 반복 실행할 코드
                        }</code></pre>

                        <h3>do-while 문</h3>
                        <p>코드를 먼저 한 번 실행하고, 그 후 조건을 검사합니다. <strong>최소 1번은 반드시 실행</strong>됩니다.</p>

                        <h3>향상된 for문 (for-each)</h3>
                        <p>배열이나 컬렉션의 요소를 순서대로 꺼낼 때 편리합니다.</p>
                        <pre><code>for (타입 변수명 : 배열/컬렉션) {
                            // 각 요소 처리
                        }</code></pre>

                        <h3>break와 continue</h3>
                        <ul>
                            <li><code>break</code>: 반복문을 즉시 종료</li>
                            <li><code>continue</code>: 현재 반복을 건너뛰고 다음 반복으로</li>
                        </ul>
                        """)
                .codeExample("""
                        public class Loops {
                            public static void main(String[] args) {

                                // for 문: 1부터 5까지 출력
                                for (int i = 1; i <= 5; i++) {
                                    System.out.print(i + " ");
                                }
                                System.out.println();  // 출력: 1 2 3 4 5

                                // while 문
                                int count = 0;
                                while (count < 3) {
                                    System.out.println("반복 " + count);
                                    count++;
                                }

                                // for-each 문
                                int[] numbers = {10, 20, 30, 40, 50};
                                int sum = 0;
                                for (int num : numbers) {
                                    sum += num;
                                }
                                System.out.println("합계: " + sum);  // 합계: 150

                                // break: 특정 조건에서 반복 종료
                                for (int i = 1; i <= 10; i++) {
                                    if (i == 5) break;
                                    System.out.print(i + " ");
                                }
                                System.out.println();  // 출력: 1 2 3 4

                                // continue: 짝수만 출력
                                for (int i = 1; i <= 10; i++) {
                                    if (i % 2 != 0) continue;  // 홀수 건너뜀
                                    System.out.print(i + " ");
                                }
                                System.out.println();  // 출력: 2 4 6 8 10

                                // 구구단 (중첩 for문)
                                for (int dan = 2; dan <= 3; dan++) {
                                    for (int j = 1; j <= 9; j++) {
                                        System.out.println(dan + " x " + j + " = " + (dan * j));
                                    }
                                }
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l5).orderNum(1)
                        .questionText("for (int i = 0; i < 5; i++) 에서 반복문은 몇 번 실행되나요?")
                        .optionA("4번")
                        .optionB("6번")
                        .optionC("5번")
                        .optionD("무한 반복")
                        .correctAnswer("C")
                        .explanation("i가 0,1,2,3,4 일 때 조건 i < 5가 true이므로 5번 실행됩니다. i=5가 되면 false가 되어 종료합니다.")
                        .build(),
                Question.builder().lesson(l5).orderNum(2)
                        .questionText("반복문을 즉시 빠져나오는 키워드는?")
                        .optionA("continue")
                        .optionB("exit")
                        .optionC("stop")
                        .optionD("break")
                        .correctAnswer("D")
                        .explanation("break는 현재 반복문을 즉시 종료합니다. continue는 현재 반복만 건너뛰고 다음 반복을 계속합니다.")
                        .build(),
                Question.builder().lesson(l5).orderNum(3)
                        .questionText("do-while 문의 특징은?")
                        .optionA("조건이 false여도 최소 1번은 실행된다")
                        .optionB("반드시 짝수번 실행된다")
                        .optionC("조건을 먼저 검사하고 실행한다")
                        .optionD("break를 사용할 수 없다")
                        .correctAnswer("A")
                        .explanation("do-while은 코드를 먼저 실행하고 조건을 검사하므로, 조건이 처음부터 false여도 최소 1번은 실행됩니다.")
                        .build(),
                Question.builder().lesson(l5).orderNum(4)
                        .questionText("배열의 모든 요소를 순서대로 꺼낼 때 가장 간결한 반복문은?")
                        .optionA("while 문")
                        .optionB("do-while 문")
                        .optionC("일반 for 문")
                        .optionD("향상된 for문 (for-each)")
                        .correctAnswer("D")
                        .explanation("향상된 for문(for-each)은 인덱스 없이 배열/컬렉션의 요소를 순서대로 꺼낼 수 있어 가장 간결합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 6: 배열
        // =====================================================================
        Lesson l6 = lessonRepository.save(Lesson.builder()
                .title("배열 (Array)")
                .category("자료구조")
                .orderNum(6)
                .difficulty("초급")
                .description("같은 타입의 데이터를 여러 개 저장하는 배열(Array)을 배웁니다.")
                .content("""
                        <h3>배열이란?</h3>
                        <p>같은 타입의 데이터를 <strong>연속된 메모리 공간</strong>에 저장하는 자료구조입니다.<br>
                        배열의 각 요소는 <strong>인덱스(0부터 시작)</strong>로 접근합니다.</p>

                        <h3>배열 선언과 생성</h3>
                        <pre><code>// 선언 후 생성
                        int[] arr = new int[5];   // 크기 5의 int 배열

                        // 선언과 동시에 초기화
                        int[] arr = {10, 20, 30, 40, 50};

                        // new 키워드로 초기화
                        int[] arr = new int[]{10, 20, 30};</code></pre>

                        <h3>배열 특징</h3>
                        <ul>
                            <li>크기가 <strong>고정</strong>됩니다 (생성 후 크기 변경 불가)</li>
                            <li>인덱스는 <strong>0부터 시작</strong></li>
                            <li><code>arr.length</code>로 배열 길이 확인</li>
                            <li>범위 초과 시 <code>ArrayIndexOutOfBoundsException</code> 발생</li>
                        </ul>

                        <h3>기본값</h3>
                        <p>배열 생성 시 자동으로 기본값이 채워집니다:</p>
                        <ul>
                            <li>int, long, short, byte: <code>0</code></li>
                            <li>double, float: <code>0.0</code></li>
                            <li>boolean: <code>false</code></li>
                            <li>String, 객체: <code>null</code></li>
                        </ul>

                        <h3>2차원 배열</h3>
                        <p>배열 안에 배열이 있는 구조입니다. 행과 열로 이루어진 표 형태로 생각하면 됩니다.</p>
                        """)
                .codeExample("""
                        import java.util.Arrays;

                        public class ArrayExample {
                            public static void main(String[] args) {

                                // 1차원 배열
                                int[] scores = {85, 92, 78, 95, 88};

                                // 인덱스로 접근 (0부터 시작)
                                System.out.println("첫 번째 점수: " + scores[0]);  // 85
                                System.out.println("마지막 점수: " + scores[4]);  // 88
                                System.out.println("배열 길이: " + scores.length);  // 5

                                // 배열 순회
                                int total = 0;
                                for (int score : scores) {
                                    total += score;
                                }
                                double avg = (double) total / scores.length;
                                System.out.printf("평균: %.1f%n", avg);  // 평균: 87.6

                                // 배열 출력 (Arrays 유틸)
                                System.out.println(Arrays.toString(scores));
                                // [85, 92, 78, 95, 88]

                                // 배열 정렬
                                Arrays.sort(scores);
                                System.out.println(Arrays.toString(scores));
                                // [78, 85, 88, 92, 95]

                                // 2차원 배열 (3x3 행렬)
                                int[][] matrix = {
                                    {1, 2, 3},
                                    {4, 5, 6},
                                    {7, 8, 9}
                                };
                                System.out.println(matrix[1][2]);  // 6 (2행 3열)

                                // 2차원 배열 출력
                                for (int[] row : matrix) {
                                    System.out.println(Arrays.toString(row));
                                }
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l6).orderNum(1)
                        .questionText("int[] arr = {10, 20, 30}; 에서 arr[1]의 값은?")
                        .optionA("10")
                        .optionB("0")
                        .optionC("30")
                        .optionD("20")
                        .correctAnswer("D")
                        .explanation("배열 인덱스는 0부터 시작합니다. arr[0]=10, arr[1]=20, arr[2]=30 입니다.")
                        .build(),
                Question.builder().lesson(l6).orderNum(2)
                        .questionText("배열의 길이를 구하는 방법은?")
                        .optionA("arr.size()")
                        .optionB("arr.count")
                        .optionC("arr.length")
                        .optionD("length(arr)")
                        .correctAnswer("C")
                        .explanation("배열에서 길이는 .length 속성으로 접근합니다. (괄호 없음! 메서드가 아닌 필드입니다)")
                        .build(),
                Question.builder().lesson(l6).orderNum(3)
                        .questionText("new int[5]로 배열을 생성하면 각 요소의 기본값은?")
                        .optionA("null")
                        .optionB("1")
                        .optionC("-1")
                        .optionD("0")
                        .correctAnswer("D")
                        .explanation("int 배열은 생성 시 모든 요소가 0으로 초기화됩니다. boolean은 false, String/객체는 null입니다.")
                        .build(),
                Question.builder().lesson(l6).orderNum(4)
                        .questionText("배열 생성 후 크기를 변경하려면?")
                        .optionA("arr.resize(10);")
                        .optionB("arr.length = 10;")
                        .optionC("배열은 크기 변경이 불가능하다")
                        .optionD("arr = new int[10]; 으로 새 배열 생성")
                        .correctAnswer("C")
                        .explanation("Java 배열은 생성 후 크기를 변경할 수 없습니다. 크기 변경이 필요하면 ArrayList를 사용하거나 새 배열을 생성해야 합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 7: 메서드
        // =====================================================================
        Lesson l7 = lessonRepository.save(Lesson.builder()
                .title("메서드 (Method)")
                .category("Java 기초")
                .orderNum(7)
                .difficulty("초급")
                .description("코드를 재사용하기 위한 메서드 정의, 매개변수, 반환값, 오버로딩을 배웁니다.")
                .content("""
                        <h3>메서드란?</h3>
                        <p>특정 작업을 수행하는 <strong>코드 블록</strong>에 이름을 붙인 것입니다.<br>
                        코드 재사용, 가독성 향상, 유지보수 용이성을 위해 사용합니다.</p>

                        <h3>메서드 구조</h3>
                        <pre><code>접근제어자 반환타입 메서드명(매개변수) {
                            // 실행 코드
                            return 반환값;  // 반환타입이 void가 아닐 때
                        }</code></pre>

                        <h3>반환 타입</h3>
                        <ul>
                            <li><code>void</code>: 반환값 없음</li>
                            <li><code>int</code>, <code>String</code> 등: 해당 타입의 값을 반환</li>
                        </ul>

                        <h3>매개변수 (Parameter)</h3>
                        <p>메서드에 전달하는 입력값입니다. 없어도 됩니다.</p>

                        <h3>메서드 오버로딩 (Overloading)</h3>
                        <p>같은 이름의 메서드를 <strong>매개변수의 타입이나 개수</strong>를 다르게 하여 여러 개 정의합니다.</p>

                        <h3>재귀 메서드</h3>
                        <p>메서드가 자기 자신을 호출하는 것입니다. 팩토리얼, 피보나치 등에 활용됩니다.</p>
                        """)
                .codeExample("""
                        public class Methods {

                            // 반환값 없는 메서드
                            static void printGreeting(String name) {
                                System.out.println("안녕하세요, " + name + "님!");
                            }

                            // 반환값 있는 메서드
                            static int add(int a, int b) {
                                return a + b;
                            }

                            // 메서드 오버로딩 (같은 이름, 다른 매개변수)
                            static double add(double a, double b) {
                                return a + b;
                            }

                            static int add(int a, int b, int c) {
                                return a + b + c;
                            }

                            // 재귀 메서드 (팩토리얼)
                            static int factorial(int n) {
                                if (n <= 1) return 1;      // 종료 조건
                                return n * factorial(n - 1); // 재귀 호출
                            }

                            // 최댓값 반환
                            static int max(int a, int b) {
                                return (a > b) ? a : b;
                            }

                            public static void main(String[] args) {
                                printGreeting("홍길동");       // 안녕하세요, 홍길동님!

                                int result1 = add(3, 4);
                                double result2 = add(1.5, 2.3);
                                int result3 = add(1, 2, 3);

                                System.out.println(result1);   // 7
                                System.out.println(result2);   // 3.8
                                System.out.println(result3);   // 6

                                System.out.println("5! = " + factorial(5));  // 5! = 120
                                System.out.println("최댓값: " + max(10, 7));  // 최댓값: 10
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l7).orderNum(1)
                        .questionText("반환값이 없는 메서드의 반환 타입은?")
                        .optionA("null")
                        .optionB("nothing")
                        .optionC("empty")
                        .optionD("void")
                        .correctAnswer("D")
                        .explanation("반환값이 없는 메서드는 void 타입을 사용합니다. void 메서드에서는 return; 으로 조기 종료할 수 있지만 값을 반환하지 않습니다.")
                        .build(),
                Question.builder().lesson(l7).orderNum(2)
                        .questionText("메서드 오버로딩(Overloading)이란?")
                        .optionA("메서드를 상속받아 재정의하는 것")
                        .optionB("같은 이름의 메서드를 매개변수를 다르게 하여 여러 개 정의하는 것")
                        .optionC("메서드를 삭제하는 것")
                        .optionD("메서드를 다른 클래스로 이동하는 것")
                        .correctAnswer("B")
                        .explanation("오버로딩은 같은 메서드 이름으로 매개변수의 타입/개수가 다른 메서드를 여러 개 정의하는 것입니다. 반환 타입만 다른 것은 오버로딩이 아닙니다.")
                        .build(),
                Question.builder().lesson(l7).orderNum(3)
                        .questionText("메서드를 사용하는 주요 이유가 아닌 것은?")
                        .optionA("코드 재사용")
                        .optionB("가독성 향상")
                        .optionC("실행 속도 증가")
                        .optionD("유지보수 용이")
                        .correctAnswer("C")
                        .explanation("메서드는 코드 재사용, 가독성, 유지보수를 위해 사용합니다. 실행 속도 증가는 메서드 사용의 주된 목적이 아닙니다.")
                        .build(),
                Question.builder().lesson(l7).orderNum(4)
                        .questionText("재귀 메서드에서 반드시 필요한 것은?")
                        .optionA("반환값")
                        .optionB("종료 조건 (base case)")
                        .optionC("여러 개의 매개변수")
                        .optionD("static 키워드")
                        .correctAnswer("B")
                        .explanation("재귀 메서드에는 반드시 종료 조건이 있어야 합니다. 없으면 무한 재귀가 발생하여 StackOverflowError가 발생합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 8: 객체와 클래스
        // =====================================================================
        Lesson l8 = lessonRepository.save(Lesson.builder()
                .title("객체와 클래스")
                .category("객체지향(OOP)")
                .orderNum(8)
                .difficulty("중급")
                .description("객체지향 프로그래밍의 핵심인 클래스와 객체, 생성자, 필드, 메서드를 배웁니다.")
                .content("""
                        <h3>객체지향 프로그래밍(OOP)이란?</h3>
                        <p>실세계의 사물을 <strong>객체</strong>로 모델링하여 프로그래밍하는 패러다임입니다.<br>
                        4대 특성: <strong>캡슐화, 상속, 다형성, 추상화</strong></p>

                        <h3>클래스(Class)</h3>
                        <p>객체를 만들기 위한 <strong>설계도(틀)</strong>입니다.<br>
                        클래스는 <strong>필드(속성)</strong>와 <strong>메서드(행동)</strong>로 구성됩니다.</p>

                        <h3>객체(Object)</h3>
                        <p>클래스를 기반으로 생성된 <strong>실체</strong>입니다.<br>
                        <code>new</code> 키워드로 생성합니다.</p>

                        <h3>생성자(Constructor)</h3>
                        <p>객체 생성 시 자동으로 호출되는 특수 메서드입니다.<br>
                        클래스 이름과 동일하고 반환 타입이 없습니다.</p>

                        <h3>캡슐화 (Encapsulation)</h3>
                        <p>필드를 <code>private</code>으로 감추고 <code>getter/setter</code>로 접근합니다.<br>
                        데이터를 보호하고 잘못된 접근을 방지합니다.</p>

                        <h3>접근 제어자</h3>
                        <ul>
                            <li><code>public</code>: 어디서든 접근 가능</li>
                            <li><code>private</code>: 같은 클래스 내에서만 접근</li>
                            <li><code>protected</code>: 같은 패키지 + 자식 클래스</li>
                            <li>default: 같은 패키지 내에서만</li>
                        </ul>
                        """)
                .codeExample("""
                        // 클래스 정의
                        public class Person {

                            // 필드 (private으로 캡슐화)
                            private String name;
                            private int age;

                            // 기본 생성자
                            public Person() {
                                this.name = "이름없음";
                                this.age = 0;
                            }

                            // 매개변수 생성자
                            public Person(String name, int age) {
                                this.name = name;
                                this.age = age;
                            }

                            // Getter
                            public String getName() { return name; }
                            public int getAge() { return age; }

                            // Setter (유효성 검사 가능)
                            public void setAge(int age) {
                                if (age >= 0) this.age = age;
                            }

                            // 메서드
                            public void introduce() {
                                System.out.println("안녕하세요! 저는 " + name + ", " + age + "살입니다.");
                            }

                            @Override
                            public String toString() {
                                return "Person{name='" + name + "', age=" + age + "}";
                            }
                        }

                        // 메인 클래스
                        public class Main {
                            public static void main(String[] args) {

                                // 객체 생성
                                Person p1 = new Person("홍길동", 25);
                                Person p2 = new Person();

                                p1.introduce();   // 안녕하세요! 저는 홍길동, 25살입니다.
                                p2.introduce();   // 안녕하세요! 저는 이름없음, 0살입니다.

                                // getter/setter
                                System.out.println(p1.getName());  // 홍길동
                                p1.setAge(26);
                                System.out.println(p1.getAge());   // 26

                                System.out.println(p1);  // Person{name='홍길동', age=26}
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l8).orderNum(1)
                        .questionText("Java에서 객체를 생성할 때 사용하는 키워드는?")
                        .optionA("create")
                        .optionB("make")
                        .optionC("new")
                        .optionD("object")
                        .correctAnswer("C")
                        .explanation("Java에서 객체는 new 키워드로 생성합니다. 예: Person p = new Person();")
                        .build(),
                Question.builder().lesson(l8).orderNum(2)
                        .questionText("생성자(Constructor)의 특징으로 올바른 것은?")
                        .optionA("반환 타입이 void이다")
                        .optionB("클래스 이름과 다른 이름을 가진다")
                        .optionC("클래스 이름과 같고 반환 타입이 없다")
                        .optionD("static으로 선언해야 한다")
                        .correctAnswer("C")
                        .explanation("생성자는 클래스 이름과 동일하고 반환 타입이 아예 없습니다 (void도 아님). 객체 생성 시 자동으로 호출됩니다.")
                        .build(),
                Question.builder().lesson(l8).orderNum(3)
                        .questionText("캡슐화에서 필드를 외부에서 접근하지 못하게 하는 접근 제어자는?")
                        .optionA("public")
                        .optionB("protected")
                        .optionC("default")
                        .optionD("private")
                        .correctAnswer("D")
                        .explanation("private은 같은 클래스 내에서만 접근 가능합니다. 캡슐화에서 필드는 보통 private으로 선언하고 getter/setter로 접근합니다.")
                        .build(),
                Question.builder().lesson(l8).orderNum(4)
                        .questionText("this 키워드의 역할은?")
                        .optionA("부모 클래스를 참조한다")
                        .optionB("현재 객체(자신)를 참조한다")
                        .optionC("새 객체를 생성한다")
                        .optionD("인터페이스를 구현한다")
                        .correctAnswer("B")
                        .explanation("this는 현재 객체 자신을 가리킵니다. 생성자에서 매개변수와 필드 이름이 같을 때 this.필드명으로 구분합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 9: 상속과 다형성
        // =====================================================================
        Lesson l9 = lessonRepository.save(Lesson.builder()
                .title("상속과 다형성")
                .category("객체지향(OOP)")
                .orderNum(9)
                .difficulty("중급")
                .description("extends로 클래스를 상속하고, 오버라이딩과 다형성을 이해합니다.")
                .content("""
                        <h3>상속 (Inheritance)</h3>
                        <p>부모 클래스의 필드와 메서드를 자식 클래스가 물려받는 것입니다.<br>
                        <code>extends</code> 키워드를 사용합니다.</p>
                        <p>장점: 코드 재사용, 계층 구조 표현</p>

                        <h3>메서드 오버라이딩 (Overriding)</h3>
                        <p>부모의 메서드를 자식 클래스에서 <strong>재정의</strong>하는 것입니다.<br>
                        <code>@Override</code> 어노테이션을 붙이는 것이 권장됩니다.</p>

                        <h3>super 키워드</h3>
                        <ul>
                            <li><code>super()</code>: 부모 생성자 호출</li>
                            <li><code>super.메서드()</code>: 부모 메서드 호출</li>
                        </ul>

                        <h3>다형성 (Polymorphism)</h3>
                        <p>같은 타입의 참조변수로 여러 종류의 객체를 다룰 수 있는 성질입니다.<br>
                        부모 타입으로 자식 객체를 참조할 수 있습니다.</p>

                        <h3>instanceof 연산자</h3>
                        <p>객체가 특정 클래스의 인스턴스인지 확인합니다.</p>
                        """)
                .codeExample("""
                        // 부모 클래스
                        class Animal {
                            String name;

                            Animal(String name) {
                                this.name = name;
                            }

                            void sound() {
                                System.out.println(name + "이 소리를 낸다");
                            }

                            void eat() {
                                System.out.println(name + "이 먹는다");
                            }
                        }

                        // 자식 클래스 - 상속
                        class Dog extends Animal {

                            Dog(String name) {
                                super(name);  // 부모 생성자 호출
                            }

                            @Override
                            void sound() {  // 오버라이딩
                                System.out.println(name + ": 멍멍!");
                            }

                            void fetch() {  // 자식만의 메서드
                                System.out.println(name + "이 공을 가져온다");
                            }
                        }

                        class Cat extends Animal {
                            Cat(String name) { super(name); }

                            @Override
                            void sound() {
                                System.out.println(name + ": 야옹~");
                            }
                        }

                        public class Main {
                            public static void main(String[] args) {

                                Dog dog = new Dog("바둑이");
                                Cat cat = new Cat("나비");

                                dog.sound();   // 바둑이: 멍멍!
                                dog.eat();     // 바둑이이 먹는다 (상속)
                                dog.fetch();   // 바둑이이 공을 가져온다

                                cat.sound();   // 나비: 야옹~

                                // 다형성: 부모 타입으로 자식 객체 참조
                                Animal[] animals = {dog, cat, new Dog("흰둥이")};
                                for (Animal a : animals) {
                                    a.sound();  // 각 객체의 오버라이딩된 메서드 호출
                                }

                                // instanceof 확인
                                System.out.println(dog instanceof Animal);  // true
                                System.out.println(dog instanceof Dog);     // true
                                System.out.println(cat instanceof Dog);     // false
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l9).orderNum(1)
                        .questionText("Java에서 클래스 상속에 사용하는 키워드는?")
                        .optionA("inherits")
                        .optionB("implements")
                        .optionC("super")
                        .optionD("extends")
                        .correctAnswer("D")
                        .explanation("Java에서 클래스 상속은 extends 키워드를 사용합니다. implements는 인터페이스 구현에 사용됩니다.")
                        .build(),
                Question.builder().lesson(l9).orderNum(2)
                        .questionText("오버라이딩(Overriding)과 오버로딩(Overloading)의 차이는?")
                        .optionA("오버라이딩은 매개변수를 다르게, 오버로딩은 같게 한다")
                        .optionB("오버라이딩은 부모 메서드 재정의, 오버로딩은 같은 이름 다른 매개변수")
                        .optionC("둘 다 같은 의미이다")
                        .optionD("오버라이딩은 정적 메서드에만, 오버로딩은 인스턴스 메서드에만 사용")
                        .correctAnswer("B")
                        .explanation("오버라이딩은 상속 관계에서 부모 메서드를 자식이 재정의하는 것이고, 오버로딩은 같은 클래스 내에서 이름은 같되 매개변수가 다른 메서드를 여러 개 정의하는 것입니다.")
                        .build(),
                Question.builder().lesson(l9).orderNum(3)
                        .questionText("다형성에서 Animal a = new Dog(); 이 가능한 이유는?")
                        .optionA("Dog가 Animal을 상속받기 때문에")
                        .optionB("두 클래스의 이름이 비슷하기 때문에")
                        .optionC("Animal이 interface이기 때문에")
                        .optionD("불가능하다")
                        .correctAnswer("A")
                        .explanation("자식 클래스(Dog)는 부모 클래스(Animal) 타입의 변수에 저장될 수 있습니다. 이것이 다형성의 핵심입니다.")
                        .build(),
                Question.builder().lesson(l9).orderNum(4)
                        .questionText("@Override 어노테이션의 역할은?")
                        .optionA("새 메서드를 생성한다")
                        .optionB("메서드를 삭제한다")
                        .optionC("오버라이딩이 올바른지 컴파일러가 검증하도록 한다")
                        .optionD("메서드를 private으로 만든다")
                        .correctAnswer("C")
                        .explanation("@Override는 컴파일러에게 이 메서드가 오버라이딩임을 알려 오류를 체크하게 합니다. 없어도 오버라이딩은 되지만 붙이는 것이 권장됩니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 10: 인터페이스와 추상 클래스
        // =====================================================================
        Lesson l10 = lessonRepository.save(Lesson.builder()
                .title("인터페이스와 추상 클래스")
                .category("객체지향(OOP)")
                .orderNum(10)
                .difficulty("중급")
                .description("interface와 abstract class로 설계를 표준화하고 유연한 구조를 만드는 법을 배웁니다.")
                .content("""
                        <h3>추상 클래스 (Abstract Class)</h3>
                        <p>추상 메서드를 포함하는 클래스입니다. <code>abstract</code> 키워드를 사용합니다.<br>
                        <strong>직접 인스턴스화 불가능</strong>, 반드시 상속받아야 합니다.</p>
                        <p>일반 메서드도 가질 수 있습니다 (부분 구현).</p>

                        <h3>인터페이스 (Interface)</h3>
                        <p>추상 메서드의 집합입니다. <code>interface</code> 키워드로 정의합니다.<br>
                        <code>implements</code>로 구현하며, <strong>다중 구현</strong>이 가능합니다.</p>
                        <p>Java 8부터 <code>default</code> 메서드와 <code>static</code> 메서드도 포함 가능합니다.</p>

                        <h3>추상 클래스 vs 인터페이스</h3>
                        <table class="table table-bordered">
                            <thead><tr><th>구분</th><th>추상 클래스</th><th>인터페이스</th></tr></thead>
                            <tbody>
                                <tr><td>키워드</td><td>abstract</td><td>interface</td></tr>
                                <tr><td>다중</td><td>단일 상속</td><td>다중 구현</td></tr>
                                <tr><td>일반 메서드</td><td>가능</td><td>default만 가능</td></tr>
                                <tr><td>필드</td><td>가능</td><td>상수(final)만</td></tr>
                            </tbody>
                        </table>

                        <h3>언제 사용하나요?</h3>
                        <ul>
                            <li><strong>추상 클래스</strong>: IS-A 관계, 공통 코드 공유가 필요할 때</li>
                            <li><strong>인터페이스</strong>: CAN-DO 관계, 기능 명세만 필요할 때</li>
                        </ul>
                        """)
                .codeExample("""
                        // 인터페이스 정의
                        interface Drawable {
                            void draw();  // 추상 메서드
                            default void print() {
                                System.out.println("그리기 완료");
                            }
                        }

                        interface Resizable {
                            void resize(int factor);
                        }

                        // 추상 클래스
                        abstract class Shape {
                            String color;

                            Shape(String color) {
                                this.color = color;
                            }

                            abstract double area();  // 추상 메서드 (구현 없음)

                            void describe() {         // 일반 메서드
                                System.out.println("색상: " + color);
                            }
                        }

                        // 구체 클래스 (다중 인터페이스 구현)
                        class Circle extends Shape implements Drawable, Resizable {
                            double radius;

                            Circle(String color, double radius) {
                                super(color);
                                this.radius = radius;
                            }

                            @Override
                            public double area() {
                                return Math.PI * radius * radius;
                            }

                            @Override
                            public void draw() {
                                System.out.println("원 그리기 (반지름: " + radius + ")");
                            }

                            @Override
                            public void resize(int factor) {
                                radius *= factor;
                            }
                        }

                        public class Main {
                            public static void main(String[] args) {
                                Circle c = new Circle("빨강", 5.0);
                                c.describe();     // 색상: 빨강
                                c.draw();         // 원 그리기 (반지름: 5.0)
                                c.print();        // 그리기 완료 (default 메서드)
                                System.out.printf("넓이: %.2f%n", c.area());

                                c.resize(2);
                                System.out.printf("확대 후 반지름: %.1f%n", c.radius);
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l10).orderNum(1)
                        .questionText("인터페이스를 구현할 때 사용하는 키워드는?")
                        .optionA("extends")
                        .optionB("inherit")
                        .optionC("abstract")
                        .optionD("implements")
                        .correctAnswer("D")
                        .explanation("인터페이스 구현에는 implements를 사용합니다. extends는 클래스 상속에, implements는 인터페이스 구현에 사용합니다.")
                        .build(),
                Question.builder().lesson(l10).orderNum(2)
                        .questionText("추상 클래스와 인터페이스의 차이로 올바른 것은?")
                        .optionA("인터페이스는 다중 구현이 가능하지만 추상 클래스는 단일 상속만 가능")
                        .optionB("추상 클래스는 인터페이스를 구현할 수 없다")
                        .optionC("인터페이스는 필드를 가질 수 없지만 추상 클래스는 가질 수 있다")
                        .optionD("A와 C 모두 맞다")
                        .correctAnswer("D")
                        .explanation("인터페이스는 다중 구현 가능(A)이고, 인터페이스는 상수(static final)만 가질 수 있고 추상 클래스는 일반 필드도 가질 수 있습니다(C). 두 가지 모두 맞습니다.")
                        .build(),
                Question.builder().lesson(l10).orderNum(3)
                        .questionText("추상 클래스의 특징으로 올바르지 않은 것은?")
                        .optionA("abstract 키워드를 사용한다")
                        .optionB("직접 인스턴스 생성이 불가능하다")
                        .optionC("일반 메서드를 가질 수 있다")
                        .optionD("다중 상속이 가능하다")
                        .correctAnswer("D")
                        .explanation("Java는 단일 상속만 지원합니다. 추상 클래스도 하나만 상속받을 수 있습니다. 다중 구현은 인터페이스만 가능합니다.")
                        .build(),
                Question.builder().lesson(l10).orderNum(4)
                        .questionText("Java 8부터 인터페이스에 추가된 기능은?")
                        .optionA("필드 선언")
                        .optionB("생성자 추가")
                        .optionC("default 메서드 (구현 포함)")
                        .optionD("private 메서드 (Java 9 이전)")
                        .correctAnswer("C")
                        .explanation("Java 8부터 interface에 default 키워드로 구현이 있는 메서드를 추가할 수 있습니다. 이를 통해 기존 인터페이스에 새 메서드를 추가해도 구현 클래스가 깨지지 않습니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 11: 컬렉션 (ArrayList, HashMap)
        // =====================================================================
        Lesson l11 = lessonRepository.save(Lesson.builder()
                .title("컬렉션 (ArrayList, HashMap)")
                .category("자료구조")
                .orderNum(11)
                .difficulty("중급")
                .description("배열의 한계를 극복하는 ArrayList와 키-값 쌍을 저장하는 HashMap을 배웁니다.")
                .content("""
                        <h3>컬렉션 프레임워크</h3>
                        <p>데이터를 효율적으로 저장하고 관리하기 위한 자료구조 모음입니다.</p>

                        <h3>ArrayList</h3>
                        <p>크기가 동적으로 변하는 배열입니다.</p>
                        <ul>
                            <li><code>add(element)</code>: 요소 추가</li>
                            <li><code>get(index)</code>: 요소 조회</li>
                            <li><code>set(index, element)</code>: 요소 수정</li>
                            <li><code>remove(index)</code>: 요소 삭제</li>
                            <li><code>size()</code>: 크기</li>
                            <li><code>contains(element)</code>: 포함 여부</li>
                        </ul>

                        <h3>HashMap</h3>
                        <p>키(Key)와 값(Value) 쌍으로 데이터를 저장합니다. 키는 중복 불가, 값은 중복 가능.</p>
                        <ul>
                            <li><code>put(key, value)</code>: 추가/수정</li>
                            <li><code>get(key)</code>: 값 조회</li>
                            <li><code>remove(key)</code>: 삭제</li>
                            <li><code>containsKey(key)</code>: 키 존재 확인</li>
                            <li><code>keySet()</code>: 모든 키</li>
                            <li><code>values()</code>: 모든 값</li>
                        </ul>

                        <h3>언제 무엇을 사용할까?</h3>
                        <ul>
                            <li><strong>순서가 중요하고 인덱스로 접근</strong> → ArrayList</li>
                            <li><strong>키로 빠르게 검색</strong> → HashMap</li>
                            <li><strong>중복 없는 집합</strong> → HashSet</li>
                        </ul>
                        """)
                .codeExample("""
                        import java.util.*;

                        public class Collections {
                            public static void main(String[] args) {

                                // ===== ArrayList =====
                                List<String> fruits = new ArrayList<>();
                                fruits.add("사과");
                                fruits.add("바나나");
                                fruits.add("딸기");
                                fruits.add("바나나");  // 중복 허용

                                System.out.println(fruits);           // [사과, 바나나, 딸기, 바나나]
                                System.out.println(fruits.get(1));    // 바나나
                                System.out.println(fruits.size());    // 4
                                System.out.println(fruits.contains("딸기"));  // true

                                fruits.remove("바나나");  // 첫 번째 "바나나" 제거
                                System.out.println(fruits);  // [사과, 딸기, 바나나]

                                // for-each 순회
                                for (String fruit : fruits) {
                                    System.out.println("과일: " + fruit);
                                }

                                // ===== HashMap =====
                                Map<String, Integer> scores = new HashMap<>();
                                scores.put("홍길동", 95);
                                scores.put("김철수", 87);
                                scores.put("이영희", 92);
                                scores.put("홍길동", 98);  // 키 중복 → 값 덮어쓰기

                                System.out.println(scores.get("홍길동"));  // 98
                                System.out.println(scores.size());         // 3

                                // 모든 항목 순회
                                for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                                    System.out.println(entry.getKey() + ": " + entry.getValue());
                                }

                                // 키 존재 확인
                                if (scores.containsKey("김철수")) {
                                    System.out.println("김철수 점수: " + scores.get("김철수"));
                                }
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l11).orderNum(1)
                        .questionText("ArrayList와 배열의 가장 큰 차이점은?")
                        .optionA("ArrayList는 정수만 저장 가능")
                        .optionB("ArrayList는 크기가 동적으로 변한다")
                        .optionC("배열은 객체를 저장할 수 없다")
                        .optionD("ArrayList는 인덱스를 사용하지 않는다")
                        .correctAnswer("B")
                        .explanation("배열은 생성 시 크기가 고정되지만, ArrayList는 요소 추가/삭제에 따라 크기가 동적으로 변합니다.")
                        .build(),
                Question.builder().lesson(l11).orderNum(2)
                        .questionText("HashMap에서 이미 존재하는 키로 put()을 하면?")
                        .optionA("예외가 발생한다")
                        .optionB("두 값이 모두 저장된다")
                        .optionC("기존 값이 새 값으로 덮어씌워진다")
                        .optionD("아무 일도 일어나지 않는다")
                        .correctAnswer("C")
                        .explanation("HashMap은 키가 중복될 수 없습니다. 같은 키로 put하면 기존 값이 새 값으로 업데이트됩니다.")
                        .build(),
                Question.builder().lesson(l11).orderNum(3)
                        .questionText("List<String> list = new ArrayList<>(); 에서 <String>의 의미는?")
                        .optionA("배열 크기를 String으로 초기화")
                        .optionB("제네릭: 이 리스트에는 String만 저장 가능함을 명시")
                        .optionC("String 클래스를 상속받는다는 의미")
                        .optionD("변수 이름")
                        .correctAnswer("B")
                        .explanation("<String>은 제네릭(Generic) 타입 파라미터입니다. 이 리스트에 String 타입만 저장되도록 타입 안전성을 보장합니다.")
                        .build(),
                Question.builder().lesson(l11).orderNum(4)
                        .questionText("키로 빠르게 값을 검색해야 할 때 가장 적합한 자료구조는?")
                        .optionA("ArrayList")
                        .optionB("LinkedList")
                        .optionC("배열")
                        .optionD("HashMap")
                        .correctAnswer("D")
                        .explanation("HashMap은 해시 기반으로 키를 이용한 O(1) 조회가 가능합니다. 이름으로 값을 찾거나, 카운팅할 때 매우 유용합니다.")
                        .build()
        ));

        // =====================================================================
        // 레슨 12: 예외 처리
        // =====================================================================
        Lesson l12 = lessonRepository.save(Lesson.builder()
                .title("예외 처리 (Exception)")
                .category("Java 기초")
                .orderNum(12)
                .difficulty("중급")
                .description("프로그램 오류를 우아하게 처리하는 try-catch-finally와 사용자 정의 예외를 배웁니다.")
                .content("""
                        <h3>예외(Exception)란?</h3>
                        <p>프로그램 실행 중 발생하는 <strong>비정상적인 상황</strong>입니다.<br>
                        예외를 처리하지 않으면 프로그램이 강제 종료됩니다.</p>

                        <h3>예외 계층 구조</h3>
                        <p>Throwable → Error / Exception<br>
                        Exception → RuntimeException (unchecked) / Checked Exception</p>
                        <ul>
                            <li><strong>Checked</strong>: 컴파일러가 처리 강제 (IOException, SQLException)</li>
                            <li><strong>Unchecked</strong>: 런타임 예외 (NullPointerException, ArrayIndexOutOfBoundsException)</li>
                        </ul>

                        <h3>try-catch-finally</h3>
                        <pre><code>try {
                            // 예외가 발생할 수 있는 코드
                        } catch (예외타입 e) {
                            // 예외 처리
                        } finally {
                            // 항상 실행 (자원 해제 등)
                        }</code></pre>

                        <h3>주요 예외 클래스</h3>
                        <ul>
                            <li><code>NullPointerException</code>: null 참조</li>
                            <li><code>ArrayIndexOutOfBoundsException</code>: 배열 범위 초과</li>
                            <li><code>NumberFormatException</code>: 숫자 변환 실패</li>
                            <li><code>ClassCastException</code>: 잘못된 형 변환</li>
                        </ul>

                        <h3>사용자 정의 예외</h3>
                        <p>Exception을 상속받아 커스텀 예외를 만들 수 있습니다.</p>

                        <h3>throws와 throw</h3>
                        <ul>
                            <li><code>throw</code>: 예외를 직접 발생시킴</li>
                            <li><code>throws</code>: 메서드가 예외를 발생시킬 수 있음을 선언</li>
                        </ul>
                        """)
                .codeExample("""
                        // 사용자 정의 예외
                        class InsufficientBalanceException extends RuntimeException {
                            public InsufficientBalanceException(String message) {
                                super(message);
                            }
                        }

                        class BankAccount {
                            private int balance;

                            BankAccount(int balance) {
                                this.balance = balance;
                            }

                            void withdraw(int amount) {
                                if (amount > balance) {
                                    throw new InsufficientBalanceException(
                                        "잔액 부족! 현재 잔액: " + balance + ", 출금 요청: " + amount);
                                }
                                balance -= amount;
                                System.out.println(amount + "원 출금 완료. 잔액: " + balance);
                            }
                        }

                        public class ExceptionExample {
                            public static void main(String[] args) {

                                // 기본 예외 처리
                                try {
                                    int[] arr = new int[3];
                                    arr[5] = 10;  // 범위 초과!
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("배열 범위 초과: " + e.getMessage());
                                } finally {
                                    System.out.println("finally 항상 실행");
                                }

                                // 숫자 변환 예외
                                try {
                                    int num = Integer.parseInt("abc");  // 오류!
                                } catch (NumberFormatException e) {
                                    System.out.println("숫자 변환 실패: " + e.getMessage());
                                }

                                // 사용자 정의 예외
                                BankAccount account = new BankAccount(10000);
                                try {
                                    account.withdraw(5000);   // 성공
                                    account.withdraw(8000);   // 잔액 부족 예외!
                                } catch (InsufficientBalanceException e) {
                                    System.out.println("오류: " + e.getMessage());
                                }

                                // 다중 catch
                                try {
                                    String s = null;
                                    s.length();  // NullPointerException
                                } catch (NullPointerException e) {
                                    System.out.println("null 참조 오류!");
                                } catch (Exception e) {
                                    System.out.println("기타 오류: " + e.getMessage());
                                }
                            }
                        }
                        """)
                .build());

        questionRepository.saveAll(List.of(
                Question.builder().lesson(l12).orderNum(1)
                        .questionText("예외 처리에서 finally 블록은 언제 실행되나요?")
                        .optionA("예외가 발생했을 때만")
                        .optionB("예외가 발생하지 않았을 때만")
                        .optionC("항상 실행 (예외 발생 여부 무관)")
                        .optionD("catch 블록이 없을 때만")
                        .correctAnswer("C")
                        .explanation("finally 블록은 예외 발생 여부와 관계없이 항상 실행됩니다. 주로 파일/DB 연결 닫기 등 자원 해제에 사용합니다.")
                        .build(),
                Question.builder().lesson(l12).orderNum(2)
                        .questionText("null 객체의 메서드를 호출할 때 발생하는 예외는?")
                        .optionA("IllegalArgumentException")
                        .optionB("NullPointerException")
                        .optionC("RuntimeException")
                        .optionD("ClassNotFoundException")
                        .correctAnswer("B")
                        .explanation("null 참조에 접근하면 NullPointerException이 발생합니다. Java 14부터는 어떤 변수가 null인지 상세히 알려줍니다.")
                        .build(),
                Question.builder().lesson(l12).orderNum(3)
                        .questionText("예외를 직접 발생시키는 키워드는?")
                        .optionA("throws")
                        .optionB("raise")
                        .optionC("catch")
                        .optionD("throw")
                        .correctAnswer("D")
                        .explanation("throw new Exception()처럼 throw 키워드로 예외를 직접 발생시킵니다. throws는 메서드 시그니처에서 예외 발생 가능성을 선언할 때 사용합니다.")
                        .build(),
                Question.builder().lesson(l12).orderNum(4)
                        .questionText("Integer.parseInt(\"abc\")를 실행하면 발생하는 예외는?")
                        .optionA("NullPointerException")
                        .optionB("IllegalStateException")
                        .optionC("NumberFormatException")
                        .optionD("ParseException")
                        .correctAnswer("C")
                        .explanation("숫자로 변환할 수 없는 문자열을 parseInt로 변환하면 NumberFormatException이 발생합니다.")
                        .build()
        ));

        log.info("총 {}개 레슨, 퀴즈 문제 초기화 완료", lessonRepository.count());
    }
}
