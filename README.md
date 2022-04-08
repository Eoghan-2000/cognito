# Instructions on Setup

This application is currently running on localhost meaning that to test it users will need to have a number of thing installed to run it

## Important first step
after folder downloaded go into folder and delete node_modules in root directory and navigate to src/main/WebApp/cognito and delete node modules folder here too

## Downloads
Node Js: https://nodejs.org/en/download/
Neo4j Desktop: https://neo4j.com/download/ (some initial setup and population of data is needed for this see Neo4j subsection)
PostgreSQL: https://www.postgresql.org/download/
JDK: https://www.oracle.com/java/technologies/downloads/

## setup for neo4j
1. Upon installing neo4j make sure to copy activation key from website, enter this on installation
2. When neo4j is loaded there should be an example project with a Stop and Open dropdown, click on this project where the name is(should be Movie DBMS) and a side bar will appear. Then go to plugins on this sidebar and install the Graph data science library plugin, this should install and restart
3. Next go back to this side menu again and type 'password' into reset password in the details section and click reset.
4. once finished these two and restarted click on the dropdown button labeled "open", open neo4j browser from this to query this database
5. first run the query -> Match(n) detach delete n 
6. this should delete all data on the example database.
7. next you will copy the the contents of the test.json file in the root directory of the project into the input field and run this query.
8. This will populate the database with test data to allow you to see how it works properly(see running the frontend for login details)

## setup postgre
1. When downloading make sure everything is clicked in installation 
2. set password to 'password' in installation
4. set port to default port they give you, it should be 5432
5. Finish install
6. open PgAdmin on you pc
7. click into servers and type in password and click postgre and type in password again
8. right click databases and create a database and name it 'cognito'

## Running API
1. Make sure JDK is installed before this step and it is in your JAVA_HOME path
2. Once installed run the mvnw file (.cmd for windows)
3. This should create jar for running API
4. To run this jar navigate to the root directory and enter -> java -jar target/cognito-0.0.1-SNAPSHOT.jar  ie.app.cognito 

## Running front end
1. In command prompt or terminal navigate to cognito folder -> cd cognito-master/src/main/WebApp
2. To install node modules run -> npm install
3. run npm start to run the react application and if it does not open automatically navigate to localhost:3000
4. Next use either of the following login details, as the registration part of this application will only work when deployed. Try both of these users to see the admin spam cluster menu option.
Admin:
email: eoghan2014+sDuff@gmail.com
password: Password22
Normal user: 
email: eoghan2014+acole@gmail.com
password: Password22
5. Register will log you into the application but the initial database insertion will not work so you will only be able to navigate to different pages.
6. Check help page on website for more details on how to navigate the system

### Enjoy 

