#FROM openjdk:11 as BUILD
FROM maven:3.6.3-jdk-11
COPY . /app/source
WORKDIR /app/source
RUN mvn clean package
RUN cp /app/source/target/quotes-great-people.jar /app
WORKDIR /app
#COPY /app/source/target/quotes-great-people.jar /app
ENTRYPOINT ["java", "-jar", "quotes-great-people.jar"]


#FROM openjdk:11
#EXPOSE 8080
#RUN mkdir /app
#COPY --from=BUILD /app/target/quotes-great-people.jar /app
#WORKDIR /app
#CMD ["java", "-jar", "quotes-great-people.jar"]
