package ie.app.cognito.Service;

import ie.app.cognito.Business.UserMessages;
import ie.app.cognito.Business.UserNotifications;
import ie.app.cognito.Business.UserPosts;
import ie.app.cognito.Dao.SqlRepository;
import ie.app.cognito.Dao.UserMessageRepo;
import ie.app.cognito.Dao.UserPostRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserSqlService {
    @Autowired
    SqlRepository notifcationRepo;
    @Autowired
    UserPostRepo postRpo;
    @Autowired
    UserMessageRepo messageRepo;

     //Get users notififcations
     public List<UserNotifications> getUserNotifications(String user1){
        return notifcationRepo.getNotifications(user1);
    }
    
    //Send Trust Request to sql db
    public void sendTrust(String username1, String username2) {
        notifcationRepo.save(new UserNotifications(username1,username2,"pending"));
    }

    //Remove notification in sql db
    public void removeNotification(String username1,String username2){
        notifcationRepo.deleteNotification(username1,username2);
    }

    //get users posts
    public List<UserPosts> getPosts(String[] user) {
        List<UserPosts> result = new ArrayList<>();
        result = postRpo.getUserPosts(user);
        return  result;
    }
    //make new post
    public void makePost(String user, String post) {
        postRpo.save(new UserPosts(user,post));
    }

    //add message to database
    public void sendMessage(String userFrom,String userTo, String message){
        messageRepo.save(new UserMessages(userFrom, userTo, message));
    }
    //check message repo for two possible ids to get message
    public List<UserMessages> getMessages(String user1,String user2) {
        return messageRepo.gMessages(user1,user2);
    }

    //call for query for declined notification
    public void declineNotification(String username1, String username2) {
        notifcationRepo.declineNotification(username1, username2);
    }

    //when user edits usersname
    public void editProfile(String user, String newuser) {
        if(user != newuser){
            notifcationRepo.editUserName1(user,newuser);
            notifcationRepo.editUserName2(user,newuser);
            messageRepo.editUserNameTo(user, newuser);
            messageRepo.editUserNameFrom(user, newuser);
            postRpo.editUserName(user, newuser);
        }
    }
}
