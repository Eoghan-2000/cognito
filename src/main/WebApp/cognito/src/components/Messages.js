import { useState, useEffect} from 'react';
import {Nav,Tab,Row,Col} from 'react-bootstrap';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import { useAuth0 } from '@auth0/auth0-react';
import MessageUser from './MessageUser';

function Messages(){
    //Declare user from auth0
    const {user} = useAuth0();
    const currentUser = new CurrentUser(user.name);

    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const[messages,setMessages] = useState([]);
    const[listOfFriends,setListOfFriends] = useState([]);
    const[userDetails,setUserDetails]= useState([]);


    

    function setUserMessages(userClicked){
        return function(){
            setMessages(<MessageUser userClicked={userClicked} currentUser={userDetails.username}/>)
        }
    }
    function getCurrentUser(){
        UserService.searchUserbyEmail(user.name).then((response) =>{
          setUserDetails(response.data)
          getFriendsList(response.data)
        });
      }
    function getFriendsList(current){
        UserService.getFriendsList(current.username).then((response) =>{
            if(response.data){
            //Set returned posts from database as react front end values 
            setListOfFriends(response.data.map((u) =>
            <Nav.Item>
            <Nav.Link eventKey={u} onClick={setUserMessages(u)}>{u}</Nav.Link>
            </Nav.Item>)
            )
        }});
    }

    // gets called if list of friends changes
    useEffect(() =>{
        getCurrentUser()
    },[messages]);
    return(
        <div className="d-grid gap-2">
            <div class="text-center"><h2>Messages</h2></div>
            <Tab.Container id="left-tabs-example" defaultActiveKey="first">
            <Row>
            <Col sm={2}>
            <Nav variant="pills" className="flex-column">
            {listOfFriends}
            </Nav>
            </Col>
            <Col>
            <Tab.Content>
            {messages}
            </Tab.Content>
            </Col>
            </Row>
            </Tab.Container>
        </div>
    );
}

export default Messages;