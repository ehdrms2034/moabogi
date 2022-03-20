# moabogi
스프링 배치 기반 기술블로그 크롤링 서버

다음과 같이 프로젝트가 구성될 예정이다.

### 웹 서버
React - Nginx - 스프링 Web 서버 - mysql

### batch 서버
스프링 배치서버 - mysql

스케쥴링은 (jenkins or quartz) 중 선택한다.

스프링 배치 job의 모니터링과 추적은 어떤 도구로 할지 고려해본다.
1. 단순 로그 및 웹 모니터링
2. jenkins
