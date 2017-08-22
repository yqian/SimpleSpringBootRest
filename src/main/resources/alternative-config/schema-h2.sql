--IT NEEDS TO BE IN src/main/resources TO BE EXECUTED
--Manually initialize the schema
--Spring boot already configured Hibernate to create schema based on entity annotation for in memory database
--In order to manually initialize the schema, 'spring.jpa.hibernate.ddl-auto=none' is required in application.properties
create table t_user (id	integer, name varchar(32), role	varchar(32), primary key(id));
