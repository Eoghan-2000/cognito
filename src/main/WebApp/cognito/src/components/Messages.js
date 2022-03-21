import { useState, useEffect} from 'react';
import {Nav,Tab,Row,Col,Form,Button, Card} from 'react-bootstrap';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import { useAuth0 } from '@auth0/auth0-react';

function Messages(){
    //Declare user from auth0
    const {user} = useAuth0();
    const currentUser = new CurrentUser(user.name);

    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const[messages,setMessages] = useState([]);
    const[newMessage,setNewMessage]= useState("");
    const[listOfFriends,setListOfFriends] = useState([]);

    //When the user types in the text box the value is changed to their input
    const handleChange = e => {
        setNewMessage(e.target.value);
      };
    
    function sendMessage (userfrom, userto){
        UserService.sendUserMessage(userfrom, userto, newMessage);
        setNewMessage("");
      };

    function setUserMessages(currentUser,userClicked){
        return function(){
        
        UserService.getMessages(userClicked,currentUser).then((response)=>{
            console.log(response.data)
            setMessages(<Tab.Pane eventKey={userClicked}>
            {response.data.map((u) => u.from === userClicked ? <><div class="leftMessage">{userClicked}:{u.message}</div><br /></> : <><div class="rightMessage">You:{u.message}</div><br/></>)}
            {/* {response.data.map((u) => <div class="leftMessage">{u.message}</div>)} */}
            <Form>
            {/*Users new message input*/}
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
            <Form.Control as="textarea" rows={3} value={newMessage} onChange={handleChange}/>
            </Form.Group>
            </Form>
            <Button className="bg-dark" size="lg" onClick={sendMessage(currentUser,userClicked)}>Send Message</Button></Tab.Pane>)})
    }
    }

    // gets called if list of friends changes
    useEffect(() =>{
        UserService.getFriendsList(currentUser.username).then((response) =>{
            if(response.data){
            //Set returned posts from database as react front end values 
            setListOfFriends(response.data.map((u) =>
            <Nav.Item>
            <Nav.Link eventKey={u} onClick={setUserMessages(currentUser.username,u)}>{u}</Nav.Link>
            </Nav.Item>))
        }});
    },[]);
    
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