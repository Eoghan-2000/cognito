//Eoghan Martin - C18342116 - Final Year Project - Cognito
import axios from 'axios';
//URL for backend to access database
const USERS_SERVICE_API_URL = 'http://localhost:8080/api/';

class UserService{
    //call to api to sendTrust
    sendTrust(user1,user2){
        axios.post(USERS_SERVICE_API_URL + 'sendtrust/' + user1 + '/' + user2)
    }
    //call to api to remove trust
    removeTrust(user1,user2){
        axios.post(USERS_SERVICE_API_URL + 'removetrust/' + user1 + '/' + user2)
    }
    //call to api to check if users are friends
    isFriend(user1,user2){
        return axios.get(USERS_SERVICE_API_URL + 'isFriend/' + user1 + '/' + user2);
    }

    //accept trust request
    acceptReq(user1,user2,sliderval){
        axios.post(USERS_SERVICE_API_URL + 'acceptrust/' + user1 + "/" + user2 + '/' + sliderval);
    }
    //get notifications for users
    getNotifications (user){
        return axios.get(USERS_SERVICE_API_URL + 'getnotifications/' + user);
    }

    //call to api using axios to search for particular user
    searchUsers(searchString){
        return axios.get(USERS_SERVICE_API_URL + 'searchUser/' +searchString);
    }
    //call to api using axios to get spam clusters
    getSpamCluster(){
        return axios.get(USERS_SERVICE_API_URL + 'spamAccounts')
    }

    //sends users that has been reported to api
    flagUser(username){
        axios.post(USERS_SERVICE_API_URL + 'reportUser/' + username);
    }
    //unflags flagged user
    unFlagUser(username){
        axios.post(USERS_SERVICE_API_URL + 'unflagUser/' + username);
    }
    //gets specific users friend list
    getFriendsList = async (username) => {
        const resp = await axios.get(USERS_SERVICE_API_URL + 'getfriends/' + username);
        return resp;
    }
    //get posts for a certain group of users
    getPosts = async (...users) => {
        const resp = await axios.get(USERS_SERVICE_API_URL + 'getposts/' + users);
        return resp;
    }
    //makes new user post
    makeUserPost(user, post){
        axios.post(USERS_SERVICE_API_URL + 'makepost/' + user + "/" + post);
    }
    getDos(user1,user2){
        return axios.get(USERS_SERVICE_API_URL + 'getdos/' + user1 + "/" + user2);
    }

    getMessages = async (current,userclicked) => {
        const resp = await axios.get(USERS_SERVICE_API_URL + 'getmessages/' + current + '/' + userclicked)
        return resp;
    }
    sendUserMessage = async (userFrom, userTo,Message) => {
        await axios.post(USERS_SERVICE_API_URL +'sendmessage/'+ userFrom + '/' + userTo + '/' + Message);
    }
}

export default new UserService();