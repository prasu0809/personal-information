[PersonalInformation project]
=====

Getting started
-------------------------------------
PersonalInformation project building on JAVA 8 and Maven 3.6.1.
This project based on Spring Boot and Rest Framework 
```
./mvnw clean install

### Run Springboot app with Maven wrapper
./mvnw spring-boot:run

### Run testcases
-/mvnw test

### install maven wrapper
mvn -N io.takari:maven:wrapper
```
#### Maven Profiles
**war**  personal-information.war

###### With Tomact:
**war** and **embedded_tomcat** profiles. 
**This configuration enabled by default**

URL calls in order to request each REST operations:
---------------------------------------------------
###Create operation : POST
URL : http://localhost:8080/personalInfo/createInfo
Request Body:
{"name":"Prasanna","lastName":"Kotha","address":"Chile", "phoneNum":"123425", "hairColor":"Black"}

###Retrieve operation : GET
URL : http://localhost:8080/personalInfo/allInformation
Retrieve by Id operation : GET
URL : http://localhost:8080/personalInfo/getPersonalInfoByID/1

### Update operation : PUT
URL : http://localhost:8080/personalInfo/updateInfo/1
Request Body:
{"name":"Prasanna","lastName":"last","address":"Chile", "phoneNum":"123425", "hairColor":"Black"}

### Delete operation : DELETE
URL : http://localhost:8080/personalInfo/deletePersonalInfo/1

### to view the database
URL : http://localhost:8080/h2

Configuration
-------------
application.properties


Testing
-------------------------------------
#### UNIT Tests (MockMvc)
com.personalinformation.controller.PersonalInformationControllerTest


Release note
-------------------------------------