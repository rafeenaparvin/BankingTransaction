FROM maven:3.8.2-jdk-8

WORKDIR ../BankingSolution
COPY . .
RUN mvn clean install -D skipTests

CMD mvn spring-boot:run
