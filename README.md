### To run app follow steps:
1) `mvn install`
2) `mvn spring-boot:run`

### Api
##### Create User
* GET `/user/create?email=...&name=...&password=...&surname=...&city=...&street=...&house=...`
* POST `/user/create` and JSON in body
* `email` and `password` are required
##### Find user by email
* GET `/user/{email}`
##### Get all users
* GET `/user/all`

### Assumptions
* `/create` GET endpoint just to make testing easy (from browser), I know GET is not best option for creating resources
* tried to keep it simple
* in memory Mongo data base 