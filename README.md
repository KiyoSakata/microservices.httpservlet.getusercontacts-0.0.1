# HTTPServlet Micro Service - GetUserContacts

GetUserContacts is a Java HTTPServlet standalone micro service. The micro service exposes 1 API to provide the contact details of a single user.

GetUserContacts consumes a third party API to get a list of users to store and search against.

[Third Party API](https://jsonplaceholder.typicode.com/users)


## Installation

* Clone or Download the project source code from: https://github.com/KiyoSakata/microservices.httpservlet.getusercontacts-0.0.1
* Import the project into eclipse. Selecting option Import -> Existing Maven Projects.

## Usage

* From eclipse, run Main class GetUserContactApplication by clicking Run as -> Java Application. Tomcat server will be started. 
* Using command prompt:  
    1. Go to the root folder of the project and run the command:

    ```bash
    mvn clean package
    ```  

    2. An executable JAR will be created in target folder. Run this JAR as:

    ```bash
    java -jar target/getUserContacts-0.0.1.jar 
    ```

## Running the tests

* From eclipse, run test class GetContactServletTests by clicking Run as -> JUnit Test.
* From Browser, access the URLs below

    1. http://localhost:8080/getusercontacts?ID=1
    2. http://localhost:8080/getusercontacts?Username=Bret

Refer to application.properties to get the appropriate user credentials.

## Built with

* Java 1.8
* Maven
* Spring Boot
* Jackson JSON