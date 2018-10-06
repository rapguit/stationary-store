# Stationary Store
### _Papelaria_

How to start the Stationary Store application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/stationary-store-1.0-SNAPSHOT.jar`
1. To check that your application is running enter url `http://localhost:8080`


How to start the Stationary Store application [Docker]
---

1. Run `docker build -t stationary-store .` at project root level
1. Start container with `docker run -p 8080:8080 stationary-store`
1. To check that your application is running enter url `http://localhost:8080/stationary-store`

Health Check
---

To see your applications health enter url `http://localhost:8080/api/healthcheck`


API Docs
---
It's under `src/docs/api-guide.html` folder.
