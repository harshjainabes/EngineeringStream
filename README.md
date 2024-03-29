SurveyApp
This application helps users to create surveys.
Once survey is created, survey can be opened to all and their feedback can be collected for analysis purposes

![alt-text](https://github.com/harshjainabes/EngineeringStream/Architecture_diagram.PNG)


## Getting Started

SurveyApp is a springboot application which exposes API endpoints to create surveys, activate/deactivate surveys, Read responses of a survey etc.
Pull the project using https://github.com/harshjainabes/EngineeringStream.git
Swagger support is provided for this API testing.

### Prerequisites

Git
Java 1.8
Maven 3.x.x
Postgres Database
Spring Tool Suite

### Installing

Pull the project using https://github.com/harshjainabes/EngineeringStream.git
Go to the folder where project is pulled.
Open CMD in that folder path and run mvn clean install 
Import the project in STS as a maven project.
And Start this project as a springboot application.
Open any browser and enter http://localhost:8080/swagger-ui.html

## Running the tests

Go to the folder where project is pulled.
Open CMD in that folder path and run mvn clean install.
This will execute all JUNITS test cases.


## Deployment

To deploy this application on docker, get your account onboarded to docker.
Create an image with Java as base image and push the image to DTR.
Pull the image from DTR and create a stack with any number of containers.

## Authors

Harsh Jain

##  API Usage

Use POST /surveyShrike/addUserInfo to create a user who can create a survey and give below data in the payload to create the survey

{
  "description": "test1",
  "email": "harshjainabes@gmail.com",
  "endDate": "2020-10-14T09:07:34.077Z",
  "name": "test1",
  "question": [
    {
      "answerRequired": true,
      "inputType": 1,
      "question": "what is your gender",
      "questionOption": [
        {
          "optionChoiceName": "male"
        },
 {
          "optionChoiceName": "female"
        }

      ]
    }
  ],
  "startDate": "2019-10-14T09:07:34.078Z"
}

Use GET /surveyShrike/getAllSurvey to get list of all available surveys

Use GET /surveyShrike/getSurveyByUser to get list of surveys for a particular user