# Sample Micro Service Framework

## What covered

* Jackson configuration to support JSON 
* Custom exception (Base exception from Business, System, Persistence, DataFormat and HTTP exceptions) 
* PCF support 
* Environment support for application.properties 
* Server name, port customization 
* Build and API version info 
* HTTPS csrf 
* Cache controller configuration 
* Object mapper 
* Rest controllers 
* Service layer for business logic implementation 
* Validation for the request entity 

## Jackson - Configuration

    Jackson is configured for below currently.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.registerModule(customSerializersModule());
        mapper.registerModule(new JodaModule());

## Quick start 

### Repository: Source code is available in Github under the path https://github.com/Divya-Reddy05/taco_loco. 
* Build: 
* 	Prerequisites: Java, Maven 
* 	Steps: 
 1.	Navigate Taco loco source and run below commands to generate the JAR file (JAR will be generated under target folder) 
* mvn clean package (or) mvn clean install 

* Running application: 
 1.	Execute below command to run the application in default port (8080) 
*   java -jar target/taco-loco-1.0.jar 
 2.	To customize the server port add below command to above 
* -Dserver.port=8123 (use any port in the place of 8123) 
* Postman collections link: 
* 	https://www.getpostman.com/collections/c8579e86384d97862114 
