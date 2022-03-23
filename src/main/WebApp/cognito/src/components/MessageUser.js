import { useState, useEffect} from 'react';
import {Tab,Form,Button, Card} from 'react-bootstrap';
import UserService from '../services/UserService';
import Moment from 'moment';

function MessageUser(props){
    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const[messages, setMessages] = useState([]);
    const[newMessage,setNewMessage]= useState("");
    const clicked = props.userClicked;    
    
    //When the user types in the text box the value is changed to their input
    const handleChange = e => {
        setNewMessage(e.target.value)
      };
    
    function sendMessage(userfrom, userto){
        return function(){
        UserService.sendUserMessage(userfrom, userto, newMessage)
        setNewMessage("")
        setUserMessages()
        }
      };

    function setUserMessages(){
        UserService.getMessages(clicked,props.currentUser).then((response)=>{
            setMessages(response.data)
        })
        }

    // gets called if list of friends changes
    useEffect(() =>{
        setUserMessages(props.userClicked);
    },[props.userClicked]);
    
    return(
        <Tab.Pane eventKey={props.userClicked}>
        {messages.map((u) => u.from === props.userClicked ? <><Card className="bg-light"><Card.Title class="lightcard">{props.userClicked}:</Card.Title><Card.Subtitle class="lightcard">{Moment(u.time).format('h:mma')} {Moment(u.time).format('DD-MM-YYYY')}</Card.Subtitle><Card.Text class ="lightcard">{u.message}</Card.Text></Card><br/></> : <><Card className="bg-primary"><Card.Title>You:</Card.Title><Card.Subtitle>{Moment(u.time).format('h:mma')} {Moment(u.time).format('DD-MM-YYYY')}</Card.Subtitle><Card.Text>{u.message}</Card.Text></Card><br/></>)}
        <Form.Control value={newMessage} onChange={handleChange}></Form.Control>
        <Button className="bg-dark" size="lg" onClick={sendMessage(props.currentUser, props.userClicked)}>Send Message</Button>
        </Tab.Pane>
    );
    }

export default MessageUser;