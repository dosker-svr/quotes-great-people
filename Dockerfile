FROM maven:3.6.3-jdk-11
COPY . /app/source
WORKDIR /app/source
RUN mvn clean package
RUN cp /app/source/target/quotes-great-people.jar /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "quotes-great-people.jar"]