Jwt Authentication is used in the project.

Please send Authorization header as "Bearer <jwt Token>" on every request matching to "/api/**".

You can get token from "/login".

Roles and one test admin is stored in database on startup.

Please check security.properties file in src/resources for information on admin and jwt.

Currently every request and response is in json format, some of which had to be multipart/form-data.
  
Logging has not been implemented currently.
  
Some of jwtauthentication exception remain unhandled.

