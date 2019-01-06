## Spring Boot based RESTful API, it offers an endpoint to retrieve data from Transferwise 


The purpose of this API is to offer an endpoint to connect to Transferwise, get a quote and store a log for the operation in the embedded H2 database.
The URL the applications fetches data from is https://api.sandbox.transferwise.tech/v1/quotes.
You must provide valid business_profile_id, sandbox_API_key, source_currency and rate_type in the resources/config.properties file.


### Prerequisites

Make sure you have Maven (I am using v3.3.9) and Java 1.8 SDK installed on your machine.

### Build the application

Navigate to the project folder, open a shell and run:
```
mvn clean package
```

### Run the application

Navigate to the project folder, open a shell and run:
```
java -jar .\target\transferwise-fetcher-1.0.0.jar
```

### Send the request

The API is reachable, if deployed locally, at http://localhost:8080/quote/

The REST method used to perform the request is POST and the body should adhere to the following examples:

{
	"userId": 1 ,
    "paymentAmount": 12.34
}

{
	"userId": "1" ,
    "paymentAmount": "12.34"
} 


### Notes:

A basic error-handling strategy is in place, a lot more could be done, for example the names of the fields who hold 
invalid values could be provided in the error message returned.
