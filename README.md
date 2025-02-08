# Test Application for Qindel Group

Technical test for Qindel Group consisting of generating an application in SpringBoot that offers a REST query endpoint, through which, from the data:

- Application date
- Product identifier (product_id)
- String identifier (brand_id)

The following data is returned:
- Product identifier (product_id)
- String identifier (brand_id)
- Rate to be applied (price_list)
- Application dates (start_date and end_date)
- Final price to be applied (price)

## Steps followed

The instructions indicated in the email received about the technical test have been followed to build this application and its tests.

### Project creation

The Spring Initializr tool (https://start.spring.io/) has been used to generate a Spring project with the indicated data.

Although the email with the test data contained an image indicating the versions:
- Spring Boot: 2.7.1
- Java 11

The versions used were:
- Spring Boot: 3.4.1
- Java 17

Because the Spring Initializr tool did not offer the indicated options, due to the existence of the new versions available.

### Database initialization

The instructions indicated in the email were followed to create an H2 database as described in the link included (https://www.baeldung.com/spring-boot-h2-database).

After configuring the database options, the project was started and the H2 Console tool was accessed, where the Prices table was created and the previously indicated data was added.

```postgresql
CREATE TABLE PRICES (
 BRAND_ID INT,
 START_DATE TIMESTAMP,
 END_DATE TIMESTAMP,
 PRICE_LIST INT,
 PRODUCT_ID INT,
 PRIORITY INT,
 REAL PRICE,
 CURR VARCHAR(4)
);

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1,TO_TIMESTAMP('2020-06-14-00.00.00','YYYY-MM-DD-HH24-MI-SS'),TO_TIMESTAMP('2020-12-31-23.59.59','YYYY-MM-DD-HH24-MI-SS'), 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1,TO_TIMESTAMP('2020-06-14-15.00.00','YYYY-MM-DD-HH24-MI-SS'),TO_TIMESTAMP('2020-06-14-18.30.00','YYYY-MM-DD-HH24-MI-SS'), 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1,TO_TIMESTAMP('2020-06-15-00.00.00','YYYY-MM-DD-HH24-MI-SS'),TO_TIMESTAMP('2020-06-15-11.00.00','YYYY-MM-DD-HH24-MI-SS'), 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1,TO_TIMESTAMP('2020-06-15-16.00.00','YYYY-MM-DD-HH24-MI-SS'),TO_TIMESTAMP('2020-12-31-23.59.59','YYYY-MM-DD-HH24-MI-SS'), 4, 35455, 1, 38.95, 'EUR');
```

### Endpoint development

After creating the database as indicated, the development of the endpoint that returns the required data with the parameters indicated in the request has begun.

**Model**

The Price object has been created, its attributes have been defined and its methods have been indicated using the lombok annotations.

**Repository**

A method has been created that performs a query to obtain the rows of the Prices table, based on the string identifier, the product identifier, and the indicated date; ordered by their priority.

**Service**

A method has been created that makes a call to the repository and obtains the result of the search that has the highest priority, which will correspond to the price that will be applied to that product at the given time.

**Controller**

A method has been created for the price query endpoint that receives the parameters necessary to perform the price search and returns a JSON object representing the retrieved Price object.
The parameters that will be received are:

- String identifier (brand_id): as part of the REST path
- Product identifier (product_id): as part of the REST path
- Application date: as a query parameter, indicated in the format that was observed in the received email, that is, _"YYYY-MM-DD-HH.MI.SS"_.

So the URL will follow the model _"/price/{brandId}/{productId}"._

### Tests

Test methods have been created to verify that the cases indicated in the email about the technical test return the indicated results.
To do this, MockMVC has been used to simulate the HTTP calls to the controller.

## Future Changes

Below are changes that should be taken into account when further developing this application:

### Tests

In addition to the end-to-end tests performed with the indicated test cases, test cases with different types of data and extreme value cases should be performed to ensure the robustness of the application.

Likewise, tools such as SonarQube could be used to evaluate the code and detect possible points in the code that could be improved.

### Changes in architecture

Due to the small size of the application, it has been decided to use a basic architecture for a SpringBoot project.
If the functionality of this application were to increase, it could be refactored to an architecture that could allow more scalability, for example, using the hexagonal architecture (ports and adapters).