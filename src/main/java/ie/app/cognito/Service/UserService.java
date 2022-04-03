//Eoghan Martin - C18342116 - Final Year Project - Cognito
package ie.app.cognito.Service;

//Imports
import ie.app.cognito.Business.User;
import ie.app.cognito.Dao.SqlRepository;
import ie.app.cognito.Dao.UserDataAccess;
import ie.app.cognito.Dao.UserPostRepo;
import ie.app.cognito.Dao.UserRepository;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//User service class that has all of the user related services
@Service
public class UserService {

    @Autowired
    UserDataAccess userAccess;

    @Autowired
    UserRepository uRepo;

    @Autowired
    SqlRepository sRepo;

    @Autowired
    UserPostRepo uPostRepo;

    @Autowired
    SqlRepository uNotifications;

    public void newUser(String username, String email){
       uRepo.save(new User(username, "firstname", "lastname", LocalDate.now(), null, "location", email, false));
    }


    //searcg by calling DAO class to query database
    public List<User> userSearch(String searchString){
        //takes in result as record list and loops through creating user list to return.
        List<Record> r = userAccess.searchResult(searchString);
        List<User> users = new ArrayList<>();
        for (Record recs : r)
        {
            List<Pair<String, Value>> values = recs.fields();
            for (Pair<String,Value> nameValue: values) {
                Value value = nameValue.value();
                users.add(new User(value.get("username").asString(),
                        value.get("firstname").asString(),
                        value.get("surname").asString(),
                        value.get("dateTimeJoined").asLocalDate(),
                        value.get("dob").asLocalDate(),
                        value.get("location").asString(),
                        value.get("email").asString(),
                        value.get("flagged").asBoolean()));
        }
        }
        return users;
    }

    public void reportUser(String username){
        userAccess.reportUser(username);
    }

    //Finds spam cluster by calling DAO class to query database
    //will try improve efficiency on this
    public List<String> spamCluster(){
        Set<String> set = new HashSet<String>(); 
        List<String> firstList = new ArrayList<>();
        //takes in result as record and loops through to return usernames as a list of strings
        List<Record> r = userAccess.findSpamCluster();
        for (Record recs : r)
        {
            List<Value> values = recs.values();
            for (Value v: values) {
                firstList.add(v.asString());

            }
        }
        List<String> postSpamList = spamCluster2(firstList);
        List<String> messageSpamList = spamCluster3(firstList);

        for(String s : postSpamList){
            set.add(s);
        }
        for(String s : messageSpamList){
            set.add(s);
        }
        firstList = new ArrayList<>(set);
        for(String s : firstList){
            userAccess.flagUser(s);
        }
        return firstList;
    }
    private List<String> spamCluster2(List<String> s) {
        s = uPostRepo.getPostSpam(s);
        return s;
    }

    private List<String> spamCluster3(List<String> s) {
        s = uNotifications.getSusNotifications(s);
        return s;
    }

    //get degree of seperation betweeen two users
    public List<String> RequestDOS(String user1,String user2){
        List<String> res = new ArrayList<>();
        Record r =  userAccess.findDOS(user1,user2);
        for(Value v : r.values()){
            res.add(v.toString());
        }
        return res;
    }

    //Get all users on the database and return as user list
    public List<User> getAllUsers() {
        List<Record> r = userAccess.returnAll();
        List<User> users = new ArrayList<>();
        for (Record recs : r)
        {
            //uses list of type pair string value to extract different node attributes
            List<Pair<String, Value>> values = recs.fields();
            for (Pair<String,Value> nameValue: values) {
                Value value = nameValue.value();
                users.add(new User(value.get("username").asString(),
                        value.get("firstname").asString(),
                        value.get("surname").asString(),
                        value.get("dateTimeJoined").asLocalDate(),
                        value.get("dob").asLocalDate(),
                        value.get("location").asString(),
                        value.get("email").asString(),
                        value.get("flagged", false)));
        }
    }
        return users;
    }

    //Unflag user on neo4j db
    public void unflagUser(String username) {
        userAccess.unflagUser(username);
    }

    //Create connection upon accepted friend request
    public void acceptTrust(String username1, String username2, int trust, String type) {
        userAccess.acceptedTrust(username1, username2,trust,type);
    }

    //Remove Trust relationship in neo4j db
    public void removeTrust(String username1,String username2){
        userAccess.removeTrust(username1, username2);
    }

    public Boolean isFriend(String user1, String user2) {
        Boolean result = false;
        Record r = userAccess.isFriend(user1,user2);
        result = r.get(0).asBoolean();
        return result;
    }

    public List<String> getUserFriends(String user) {
        List<Record> r = userAccess.returnUserFriends(user);
        List<String> users = new ArrayList<>();
        for (Record recs : r)
        {
            //uses list of type pair string value to extract different node attributes
            List<Pair<String, Value>> values = recs.fields();
            for (Pair<String,Value> nameValue: values) {
                Value value = nameValue.value();
                users.add(value.get("username").asString());
        }
    }
        return users;
    }
}
