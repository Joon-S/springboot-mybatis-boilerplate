# SpringBoot Boilerplate
[![build](https://img.shields.io/badge/Build-Success-success)](https://github.com/Joon-S/springboot-mybatis-boilerplate)
[![build](https://img.shields.io/badge/Writer-JoonS-blue)](https://github.com/Joon-S)

해당 프로젝트는 단순하게 __SpringBoot, MyBatis__ 으로 기초세팅이 된것이기 때문에 추가로 커스텀이나 세팅을 하고싶을경우에는 수정이 필요하다. 
기본적인 스프링 세팅을 따르고 있기때문에 별도의 주석등은 소스상에 추가하지 않았지만 설명이 필요한 부분은 주석을 남겨두었다.
`springBoot` 와 `Mybatis` 를 제외하고 추가된 기타 라이브러리는 다음과 같다.  

라이브러리 | 설명
--------- | ---------
`Junit` | API 단위테스트 
`Spring Rest Docs` | `Junit` 을 통해 패스된것들을 이용하여 API 문서 자동작성


## 구조설명
폴더순으로 설명을 이어나가도록 하겠다. 기본적인 controller, mapper, model, service 는 설명에서 제외시켰다.  
* src/docs/asciidoc
    * index.adoc
      - API 문서를 html 파일로 뽑아내기 위해서 넣었다. **_참고로 gradle 패스와 maven 패스는 다르다_**
      또한 snippets 이라는 변수가 존재하는데 이건 include 키워드에 넣을 변수로써 문서맨위에 선언해둔것으로 각 컴퓨터 패스에 맞게 바꿔줘야한다.
* config
    * DataBaseConnector  
      - Application.yml 파일에 해당 클래스파일에 필요한 부분을 똑같이 넣을순있지만 추가적인 세부세팅을 위해서 따로 클래스로 빼놓았다.
      만약 다른 디비와 연결이 필요할시 다른 클래스를 만들어주고 `mapper` 경로가 겹치지않도록 설정해두면 된다. 아노테이션을 만들어서 `mapper` 인터페이스에
      별도의 설정도 가능한데 간략하게 말하면 `@MapperScan` 부분에 annotationClass 를 넣어주면 된다.
    * EnhanceMybatisLogDriver
      - mybatis 로그를 좀더 예쁘게 보기위해서 넣어두었다.

      
## 사용방법
테스트를 통과해야 jar 파일을 뽑아내도록해도 되고 그냥 jar 파일을 뽑아내던 상관없다. 세팅은 Gradle 에서 할수있다.
테스트가 완료되면 `~/build/generated-snippets/divisions` 폴더에 adoc 파일들이 생성되게 된다.
테스트 및 html 파일을 뽑아내기 위해서는 아래와 같이 타이핑을 하면 된다.
성공적으로 잘 진행이 되면 `~/build/asciidoc/html5/index.html` 파일이 생성된다.
```bash
D:\repository\intellij-repository\springboot-mybatis-boilerplate>gradlew asciidoctor

> Task :test
2019-10-15 11:42:15.513  INFO 16196 --- [       Thread-4] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
2019-10-15 11:42:15.514  INFO 16196 --- [       Thread-4] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2019-10-15 11:42:15.524  INFO 16196 --- [       Thread-4] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

> Task :asciidoctor
io/console not supported; tty will not be manipulated

BUILD SUCCESSFUL in 15s
5 actionable tasks: 3 executed, 2 up-to-date

```

