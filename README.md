This project is demonstration of Spring Boot with Rest Service and Data JPA  



## Setup

1. Add spring-boot-starter in pom.xml, spring boot will manage all the dependencies of the project.

2. Create main method in main class, annotated the class with `@SpringBootApplication`

3. Annotate controller class with `@RestController`, spring boot automatically generates beans for `@Autowired` annotated object

4. Use h2 in memory database, add dependency in pom.xml, spring boot automatically creates h2 database instance during startup



## Configure

1. Define a YAML file called `application.yml` with h2 database settings to allow spring boot set it up during startup, `application.properties` is alternative, please see `src\main\resources\alternative-config` for details

2. Define SQL INSERT to initialize h2 database  

Unfortunately, spring boot always starts with production code, those integration test configure have to be placed inside `\src\main` instead of `src\test`

3. Annotate `@Entity` with POJO class, spring boot automatically creates table in h2 database based on annotation

4. Annotate `@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})` with POJO class due to Hibernate proxy object lazy loading. Hibernate is automatically included by spring boot. Please refer [this]: https://stackoverflow.com/questions/24994440/no-serializer-found-for-class-org-hibernate-proxy-pojo-javassist-javassist for more details



## Test

1. Start the project `mvn spirng-boot:run`

2. Test "GET"

`curl http://locolhost:8080/user/list` will display all the users in h2 database

`curl http://localhost:8080/usr/1` will display single user with ID 1

3. Test "UPDATE"

`curl -H "Content-Type: application/json" -X "PUT" -d '{"id":1,"name":"Joe Schmo","role":"U R Fired"}' http://localhost:8080/user/1` will update the user record in h2 database.

4. Test "DELETE"

`curl -X "DELETE" http://localhost:8080/user/1` will delete user with ID 1 from h2 database

`curl -X "DELETE" http://localhost:8080/user/clear` will delete all users from h2 database

5. Verify result  

To verify it, access h2 database `http://localhost:8080/h2_console`, run `SELECT * FROM user`
