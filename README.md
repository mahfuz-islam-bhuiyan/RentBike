To run the project follow this,

- Clone the repo
- Initiate a  `mvn clean install -Dmaven.test.skip=true` at root of the project.
- Now, initiate a `docker-compose up`
- Application should be up and running at _http://localhost:8080_


###### Swagger

- To access Swagger UI with default application's port settings, head over to _http://localhost:8080/swagger-ui.html_

###### Sample Workflow

Run the following API. This would generate some dummy entries in DB.

http://localhost:8080/swagger-ui.html#/bike/createDummyBikes

Now, to get available bikes from current location(Say, lat: 24.747097028461063, long: 90.42023967463295) with distance not more than 1 mile, we can hit this,

http://localhost:8080/bike/nearestBikesByLatLong?fromLatitude=24.747097028461063&fromLongitude=90.42023967463295&withinMiles=1

Moreover, there are other endpoints to create more bikes, view and also update the location & status of them. 