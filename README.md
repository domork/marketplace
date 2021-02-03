# marketplace
Since I changed the DB from H2 to postgres it is recommended to install the postgres

https://www.postgresql.org/download/

In backend\src\main\resources\application.properties you should have follow values:

spring.session.jdbc.initialize-schema= always

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=none

spring.datasource.schema=classpath:sql/createSchema.sql



spring.jpa.show-sql=true

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres

spring.datasource.username=server

spring.datasource.password=admin

spring.datasource.initialization-mode=always

spring.datasource.continue-on-error=true




