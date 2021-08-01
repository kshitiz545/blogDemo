Jwt Authentication is used in the project.

Please send Authorization header as "Bearer <jwt Token>" on every request matching to "/api/**".

You can get token from "/login".

Roles and one test admin is stored in database on startup.

Please check security.properties file in src/resources for information on admin and jwt.


