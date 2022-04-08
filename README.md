# Instructions on Setup

This application is currently running on localhost meaning that to test it users will need to have a number of thing installed to run it

## Downloads
Node Js: https://nodejs.org/en/download/
Neo4j Desktop: https://neo4j.com/download/ (some initial setup and population of data is needed for this see Neo4j subsection)
PostgreSQL: https://www.postgresql.org/download/

## setup for neo4j
1. When neo4j is loaded there should be an example project if you click on the project with a Stop and Open dropdown, click on this project where the name is(should be Movie DBMS) and a side bar will appear. Then go to plugins on this sidebar and install the Graph data science library plugin, this should install and restart
2. once installed and restarted click on the dropdown button labeled "open", open neo4j browser from this to query this database
3. first run the query -> Match(n) detach delete n 
4. this should delete all data on the example 
5. next you will copy the the contents of the test.json file in the root directory of the project into the input field and run this query.
6. This will populate the database with test data to allow you to see how it works properly(see running the frontend for login details)

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



