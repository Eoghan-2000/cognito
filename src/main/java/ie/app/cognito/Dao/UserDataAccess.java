//Eoghan Martin - C18342116 - Final Year Project - Cognito
package ie.app.cognito.Dao;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.neo4j.driver.Values.parameters;

//class that directly queries the database using neo4j driver
@RestController
public class UserDataAccess {
    private final Driver driver;

    //Constructor to connect to database
    public UserDataAccess(Driver driver) {
        this.driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j","password"));
    }

    //Close Driver
    public void close() throws Exception {
        driver.close();
    }

    //Method to return all users on the system
    public List<Record> returnAll() {
        //Try connect to session using driver
        List<Record> r = new ArrayList<>();
        try(Session session = driver.session()) {
            //Query to return all users
            Result result = session.run("Match (U:User) return n");
            //Put all records in record list
            while (result.hasNext()) {
                r.add(result.next());
            }
        }
        return r;
    }

    public List<Record> searchResult(String searchString) {
        //Try connect to session using driver
        List<Record> r = new ArrayList<>();
        try(Session session = driver.session()) {
            //Query to return all users
            Result result = session.run("Match (U:User) where U.username contains \"" + searchString + "\" or U.firstname contains \"" + searchString + "\" or U.surname contains \"" + searchString + "\" or U.username=\"" + searchString + "\" or U.firstname=\"" + searchString + "\" or U.surname =\"" + searchString + "\" return U");
            //Put all records in record list
            while (result.hasNext()) {
                r.add(result.next());
            }
        }
        return r;
    }
    public Record findDOS(String username1, String username2) {
        //instantiate session variable using neo4j driver
        Record rec = null;
        try(Session session = driver.session()) {
            //Query to find shortest path between two users
            Result result = session.run("match path=shortestPath((u1:User{username:$username1})-[rel:TRUSTS_EACHOTHER*..5]-(u2:User{username:$username2})) "
                            + "return path, length(path) as Degrees_of_Seperation, REDUCE(s=100.0,r IN range(0,size(rel)-2) | s * 0.75) as Your_Trust;"
                    , parameters("username1", username1, "username2", username2));
            if (!result.hasNext()) {
                System.out.print("There is not path between " + username1 + " and " + username2 + "\n");
            } else{
                //Store result in record variable
                rec = result.next();
            }
        }
        return rec;
    }

    public void acceptedTrust(String username1, String username2){
        try(Session session = driver.session()) {
            session.run("MATCH" +
                        "(a:User)," +
                        "(b:User)" +
                        "WHERE a.username = \'" + username1 + "\' AND b.username = \'" + username2 +"\'" +
                        "CREATE (a)-[r:TRUSTS_EACHOTHER{trustLevel: 100}]->(b)" +
                        "RETURN type(r)");
        }
    }
    public void removeTrust(String username1, String username2){
        try(Session session = driver.session()){
            session.run("MATCH" +
            "(U:User {username: \'" + username1 + "\'})-[r:TRUSTS_EACHOTHER]-(U2:User {username: \'" + username2 + "\'})" +
            "Delete r");
        }
    }

    //Finds spam cluster on database
    public List<Record> findSpamCluster() {
        List<Record> recs = new ArrayList<>();
        try (Session session = driver.session()) {
            //Will check if graph exists on the database
            Result dropRes = session.run("CALL gds.graph.exists('spam') yield exists return exists");
            Record dropRec = dropRes.next();
            //if so it will drop existing one
            if (dropRec.get("exists").asBoolean() == Boolean.TRUE) {
                session.run("CALL gds.graph.drop('spam')");
            }
            //call to create graph
            session.run("CALL gds.graph.create.cypher("
                    + "  'spam',"
                    + "  'MATCH (n:User) where n.dateTimeJoined = date.realtime() RETURN id(n) AS id',"
                    + "  'MATCH (n:User)-[r:TRUSTS_EACHOTHER]-(m:User) RETURN id(n) AS source, id(m) AS target' "
                    + ");");
            //Call the get users with local clustering coefficient equal to 1 and return the username of these
            Result result = session.run("CALL gds.localClusteringCoefficient.stream('spam')\n"
                    + "YIELD nodeId, localClusteringCoefficient\n"
                    + "WHERE localClusteringCoefficient = 1.0\n"
                    + "RETURN gds.util.asNode(nodeId).username AS name;");
            //Adds all of these results to record variable
            if (result.hasNext()) {

                while (result.hasNext()) {
                    recs.add(result.next());
                }
                return recs;
            }
        }
        return recs;
    }

    public void reportUser(String username) {
        try (Session session = driver.session()) {
            //Will check if graph exists on the database
            session.run("Match (U:User {username:\"" + username + "\"}) Set U.flagged = true;" );
            Result dropRes = session.run("CALL gds.graph.exists('spamFriend') yield exists return exists");
            Record dropRec = dropRes.next();
            //if so it will drop existing one
            if (dropRec.get("exists").asBoolean() == Boolean.TRUE) {
                session.run("CALL gds.graph.drop('spamFriend')");
            }
            //call to create graph
            session.run("CALL gds.graph.create.cypher("
                    + "  'spamFriend',"
                    + "  'MATCH (n:User{username:\""+ username + "\"})-[r:TRUSTS_EACHOTHER]-(m:User) RETURN id(m) AS id',"
                    + "  'MATCH (n:User)-[r:TRUSTS_EACHOTHER]-(m:User) RETURN id(n) AS source, id(m) AS target', "
                    + " {validateRelationships : false} "
                    + ");");
            //Call the get users with local clustering coefficient equal to 1 and return the username of these
            session.run("CALL gds.localClusteringCoefficient.stream('spamFriend')\n"
                    + "YIELD nodeId, localClusteringCoefficient\n"
                    + "WHERE localClusteringCoefficient = 1.0\n"
                    + "Set gds.util.asNode(nodeId).flagged = true;");
        }
    }

    public void unflagUser(String username) {
        try (Session session = driver.session()) {
        session.run("Match (U:User {username:\"" + username + "\"}) Set U.flagged = false;" );
        }
    }

    public Record isFriend(String user1, String user2) {
        Record res;
        try (Session session = driver.session()) {
            Result r = session.run("RETURN EXISTS( (:User {username:\"" + user1 + "\"})-[:TRUSTS_EACHOTHER]-(:User {username:\""+user2+"\"}) )");
            res = r.next();
            }
        return res;
    }

    public List<Record> returnUserFriends(String user) {
        List<Record> r = new ArrayList<>();
        try(Session session = driver.session()) {
            //Query to return all users
            Result result = session.run("Match (U:User)-[:TRUSTS_EACHOTHER]-(U2:User) where U.username = \"" + user + "\" return U2;");
            //Put all records in record list
            while (result.hasNext()) {
                r.add(result.next());
            }
        }
        return r;
    }
}
