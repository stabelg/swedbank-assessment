# Swedbank Assessment

## How to setup

Via cmd:

* Windows: **.\gradlew.bat bootRun**
* Linux or others: **.\gradlew bootRun**

Via IDE:
* Run main in file SwedbankAssessmentApplication.java

## Users

- Admin

User: admin

Pass: admin


## Setup Postman

As the default user is created, you can simply import the **[postman collection from postman folder]()** 
It contain all the endpoints and basic auth configured, the basic auth is configured in the collection

## How it works

When started it will create users and schema from the files: schema.sql and data.sql, after that each api call
the application receives will be first security by the filter(**[BasicAuthFilter](https://github.com/stabelg/swedbank-assessment/blob/master/src/main/java/com/example/swedbankassessment/security/BasicAuthFilter.java)**) that will ensure the user and password are correct. 

In case of any problems with the data spring validations will throw an error message about the error as configured in the model(**[UserRequest](https://github.com/stabelg/swedbank-assessment/blob/master/src/main/java/com/example/swedbankassessment/model/UserRequest.java)**) 
which is cleaned by the Exception Handler(**[CustomExceptionHandler](https://github.com/stabelg/swedbank-assessment/blob/master/src/main/java/com/example/swedbankassessment/util/CustomExceptionHandler.java)**) so the message is easier to read.

The service layer(**[ApplicationUserService](https://github.com/stabelg/swedbank-assessment/blob/master/src/main/java/com/example/swedbankassessment/service/ApplicationUserService.java)**) will do basic validations as: in case of update checks if the user exists or if its trying to use an email or username that already exists and so on
The service layer will also encrypt the password before saving it.

After these users are created you can use them in the basic authentication to create new users instead of admin as the users don't have roles in this sample.

## Endpoints

**POST api/user/**: Creates a new user:

mandatory fields: name, username, email and password, they has some validations like not empty or pattern

**PUT api/user/**: Updates user: 

mandatory fields: id, name, username, email and password, they has some validations like not empty or pattern

**GET api/user/**: Get all user:

Will return all users

**GET api/user/{id}**: Get user by id:

Will return given user by id

**DELETE api/user/{id}**: Deleter user by id:

Will delete the user if it exists

## Improvements/Ideas

* Due to limited time I didn't have time to add unit tests, so this would be my first improvement.
* Logs would be helpful in a production product
* Create roles USERS/ADMINS
* Not really sure about the user details entity right now, it has no usage being split from user as another entity, but I guess it's a good sample of one-to-one relationship. Maybe if the same user had multiple roles this would make more sense.
## Contact

Gustavo Stabelini (gustavostabelini@gmail.com)