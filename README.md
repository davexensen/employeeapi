# employeeapi

##Running the application

Run the following command via a terminal prompt to add the docker image to the registry

    ./gradlew bootJar -Pprod jibDockerBuild
    
Next run the following docker-compose command via a terminal prompt

    docker-compose -f src/main/docker/app.yml up -d
    
After 30s the service/database is now running and you can execute REST commands

GET - http://localhost:8080/api/employee - Get all employees

GET - http://localhost:8080/api/employee/{id} - Get employee by ID

POST - http://localhost:8080/api/employee - Create employee

PUT - http://localhost:8080/api/employee - Update employee

DELETE (Requires authentication) - http://localhost:8080/api/employee/{id} - Delete employee by ID

To authenticate, POST the following body to
http://localhost:8080/api/authenticate

    {
      "username":"admin",
      "password":"admin"
    }
    
The response will have your JWT token, add this to your request headers as:
   
    Authorization: Bearer {token}
    i.e. 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU3Mjk4MTY5OX0.kirs_1KYuyJT7gkNguKpwmmj4CUzjIile77T64QJ6ATi1C08SKH6PtrFPheb1FlzRiqiO4dI5iFQlSfkcJD_Zw'

##JHipster documentation

This application was generated using JHipster 6.4.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.4.1](https://www.jhipster.tech/documentation-archive/v6.4.1).

## Development

To start your application in the dev profile, simply run:

    ./gradlew

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the employeeapi application for production, run:

    ./gradlew -Pprod clean bootJar

To ensure everything worked, run:

    java -jar build/libs/*.jar

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    ./gradlew -Pprod -Pwar clean bootWar

## Testing

To launch your application's tests, run:

    ./gradlew test integrationTest jacocoTestReport

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the gradle plugin.

Then, run a Sonar analysis:

```
./gradlew -Pprod clean check jacocoTestReport sonarqube
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/postgresql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/postgresql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./gradlew bootJar -Pprod jibDockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.4.1 archive]: https://www.jhipster.tech/documentation-archive/v6.4.1
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.4.1/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.4.1/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.4.1/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.4.1/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.4.1/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.4.1/setting-up-ci/
