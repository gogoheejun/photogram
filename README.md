# 포토그램 - 인스타그램 클론 코딩

### 관련 TIL 작성링크
- [YML파일이해하기](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/00_YML%ED%8C%8C%EC%9D%BC%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0.md)
- [20210714_컨트롤러](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/01_%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC.md)
- [20210715_회원가입01](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/02_%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85(1).md)
- [20210716_회원가입02](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/03_%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85(2).md)
- [20210717_로그인](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/04_%EB%A1%9C%EA%B7%B8%EC%9D%B8.md)
- [20210718_회원정보수정](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/05_%ED%9A%8C%EC%9B%90%EC%A0%95%EB%B3%B4%EC%88%98%EC%A0%95.md)
- [20210723_구독하기api](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/06_%EA%B5%AC%EB%8F%85%ED%95%98%EA%B8%B0api.md)
- [20210724_프로필페이지](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/07_%ED%94%84%EB%A1%9C%ED%95%84%ED%8E%98%EC%9D%B4%EC%A7%80.md)
- [20210725_구독정보 뷰 렌더링](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/08_%EA%B5%AC%EB%8F%85%EC%A0%95%EB%B3%B4%20%EB%B7%B0%20%EB%A0%8C%EB%8D%94%EB%A7%81.md)
- [20210726_스토리 페이지](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/08_%EA%B5%AC%EB%8F%85%EC%A0%95%EB%B3%B4%20%EB%B7%B0%20%EB%A0%8C%EB%8D%94%EB%A7%81.md)
- [20210727_기타(인기페이지,프로필추가사항)](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/11_%EA%B8%B0%ED%83%80-%EC%9D%B8%EA%B8%B0%ED%8E%98%EC%9D%B4%EC%A7%80%2C%ED%94%84%EB%A1%9C%ED%95%84%20%EC%B6%94%EA%B0%80%EC%82%AC%ED%95%AD.md)
- [20210729_댓글](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/12_%EB%8C%93%EA%B8%80.md)
- [20210729_AOP처리](https://github.com/gogoheejun/TIL/blob/main/java_spring/%EC%9D%B8%EC%8A%A4%ED%83%80%EB%A7%8C%EB%93%A4%EA%B8%B0/13_AOP%EC%B2%98%EB%A6%AC.md)



### STS 툴에 세팅하기 - 플러그인 설정
- https://blog.naver.com/getinthere/222322821611

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

```xml
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.43</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
```

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/costa?serverTimezone=Asia/Seoul
    username: costa
    password: costa1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

file:
  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```
