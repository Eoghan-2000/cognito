import { useAuth0 } from '@auth0/auth0-react';
import {ButtonGroup,Button,Card,ListGroup,ListGroupItem, Dropdown} from 'react-bootstrap';
import UserService from '../services/UserService';
import React, { useState, useEffect } from 'react';
import {useNavigate} from 'react-router-dom';
import {AllUserConnections } from './AllUserConnections';
import {useLocation} from 'react-router-dom';
import {CurrentUser} from '../business/CurrentUser';
import { FriendsMap } from './FriendsMap';

function Profile (props) {
    let navigate = useNavigate(); 
    const routeChange = () =>{ 
    let path = `/editProfile`; 
    navigate(path);
    }
    //neo4j login details
    const NEO4J_URI = "bolt://localhost:7687";
    const NEO4J_USER = "neo4j";
    const NEO4J_PASSWORD = "password";
    const delay = ms => new Promise(res => setTimeout(res, ms));

    //decalairing variables needef for profile
    const {user} = useAuth0();
    const[current,setCurrent] = useState([]);
    const [userData, setUserData] = useState([]);
    const [dosData, setDosData] = useState([]);
    const [isAFriend, setIsFriend] = useState(false);
    const [trustType, setTrustType] = useState("");
    const [slider, setSlider] = useState(100);
    const [buttonPressed, setButtonPressed]= useState(false);
    const location = useLocation();
    const { search } =  location.state;
    //if search prob is equal to the user that is currently logged in they clicked to view their own profile so set username to current user,
    //else set it to the user they are viewing
    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    
    //Get get users on load
    useEffect(() =>{
        getCurrentUser()
        getUser();
    },[]);
    
    function getCurrentUser(){
        UserService.searchUserbyEmail(user.name).then((response) =>{
          setCurrent(response.data)
          getDOS(response.data);
        });
    }

    //get users from user service and set the response to the profile data
    function getUser(){
        UserService.searchUsers(search).then((response) =>{
            setUserData(response.data)
        });
    }

    function getDOS(current){
        if(search !== current.username){
        UserService.getDos(search,current.username).then((response) =>{
            setDosData(response.data);
        });
        }
    }

    //check if the users are friends
    function isFriend(user1,user2){
        return UserService.isFriend(user1,user2).then(function(result) {
            setIsFriend(result.data);
          });
    }
    //Check for which button is pressed
    const handleClick = async (e)=>{
    if(e.target.value === "flag"){
        UserService.flagUser(search);
        await delay(2000);
        setButtonPressed(!buttonPressed);
    }else if(e.target.value ==="unflag"){
        UserService.unFlagUser(search);
        await delay(2000);
        setButtonPressed(!buttonPressed);
    }else if(e.target.value ==="untrust"){
        UserService.removeTrust(search,current.username);
        await delay(2000);
        setButtonPressed(!buttonPressed);
    }else if(e.target.value === "sendTrust"){
        UserService.sendTrust(search,current.username);
        await delay(2000);
        setButtonPressed(!buttonPressed);
    }else if(e.target.value === "confirmAdjust"){
        UserService.adjustTrust(search,current.username,slider,trustType);
        await delay(2000);
        setButtonPressed(!buttonPressed);
    }
    }

    if(userData){
        //if the user profile is the user thats logged in
        if(search === current.username){
            return(
                <div class="float-container">
                <div class="float-child">
                <div class="green"> <Card>
                    <Card.Img variant="top" src="DefaultUserImg.png"/>
                        {userData.map(user => <Card.Body> <Card.Title class="text-black">{user.firstname} {user.surname}</Card.Title>
                        <Card.Subtitle class="text-black">{user.username}</Card.Subtitle>
                    <ListGroup className="list-group-flush">
                        <ListGroupItem>From: {user.location}</ListGroupItem>
                        <ListGroupItem>Age: {user.age}</ListGroupItem>
                        <ListGroupItem>Birth Date: {user.dob}</ListGroupItem>
                        <ListGroupItem>Since {user.dateTimeJoined}{user.flagged}</ListGroupItem>
                    </ListGroup></Card.Body>)}
                    <Card.Body>
                        {/* This is where the difference is between current user profile and other user profile*/}
                        <Button color="primary" className="px-4"
                        onClick={routeChange}
                        >Edit Profile</Button>
                    </Card.Body>
                    <br/>
                </Card></div>
                </div>
                {/* Send in blank user 2 so that it shows all of the current users friend instead of degree of seperation*/}
                <div class="float-child">
                    <div class="blue">
                        <h2>Friends Map</h2>
                        <FriendsMap
                        width={400}
                        height={'80vh'}
                        containerId={"id1"}
                        neo4jUri={NEO4J_URI}
                        neo4jUser={NEO4J_USER}
                        neo4jPassword={NEO4J_PASSWORD}
                        backgroundColor={"#262626"}
                        username1={user.name}/>
                    </div>
                </div>
                </div>
            );}else if(current.size != 0){
                //if its not the profile of the user logged in this is what is displayed
                isFriend(search,current.username);
                return(
                    <div class="float-container">
                    <div class="float-child">
                    <div class="green"> <Card>
                        {/* Display users data*/}
                        <Card.Img variant="top" src="DefaultUserImg.png"/>
                            {userData.map(user => <Card.Body> <Card.Title class="text-black">{user.firstname} {user.surname}</Card.Title>
                            <Card.Subtitle class="text-black">{user.username}</Card.Subtitle>
                        <ListGroup className="list-group-flush">
                            <ListGroupItem>From: {user.location}</ListGroupItem>
                            <ListGroupItem>Age: {user.age}</ListGroupItem>
                            <ListGroupItem>Birth Date: {user.dob}</ListGroupItem>
                            <ListGroupItem>Since {user.dateTimeJoined}{user.flagged}</ListGroupItem>
                            {user.flagged === true ? <ListGroupItem id="redWarning">USER HAS BEEN FLAGGED AND IS UNDER INVESTIGATION</ListGroupItem>: <></>}
                            {dosData.length > 1 ? <><ListGroupItem>Degrees of Seperation: {dosData[0]}</ListGroupItem><ListGroupItem>Personal Trust: {dosData[1]}</ListGroupItem></> : <><ListGroupItem>User has no connection to you</ListGroupItem> <ListGroupItem>Personal Trust: 0</ListGroupItem></>}
                        </ListGroup></Card.Body>)}
                        <Card.Body>
                        {/* This is where the difference is between current user profile and other user profile*/}
                        <ButtonGroup onClick={handleClick}>
                        {/* flag users by reporting, algorithm will be ran on them and their friends */}
                        <Button value="flag" variant="danger">Report</Button>
                        {/* If the user is an admin they have the option to unflag users */}
                        {user['https://conito-app.ie/app_role'] === "Admin" &&
                            <Button value="unflag" variant="success">Unflag</Button>
                        }
                        {/* untrust if they are friends, send request if not */}
                        {(isAFriend) ?<> 
                        <Button value="untrust">Untrust</Button>
                        <Dropdown>
                        <Dropdown.Toggle id="dropdown-autoclose-true">Edit Trust</Dropdown.Toggle>
                            <Dropdown.Menu>
                            Relationship Type:
                            <input type="text" id="trusttype" name="trusttype" value={trustType} onChange={(event) => setTrustType(event.target.value)}></input>
                            Trust Level:
                            <input type="range" id="trust" name="trust" value={slider} onChange={(event) => setSlider(event.target.value)} min="0" max="100"></input>
                            <Button value="confirmAdjust">Confirm</Button>
                            </Dropdown.Menu>
                        </Dropdown> 
                        </> : <Button value="sendTrust">Send Trust Request</Button>}
                        </ButtonGroup>
                        </Card.Body>
                        <br/>
                    </Card></div>
                    </div>
                    {/* send in two users this time to get degree of seperation */}
                    <div class="float-child">
                        <div class="blue">
                        <h2>Seperation Map</h2>
                            <AllUserConnections
                            width={400}
                            height={'80vh'}
                            containerId={"id1"}
                            neo4jUri={NEO4J_URI}
                            neo4jUser={NEO4J_USER}
                            neo4jPassword={NEO4J_PASSWORD}
                            backgroundColor={"#262626"}
                            username1={search}
                            username2={current.username}
                            />
                        </div>
                    </div>
                    </div>);
                    }} else{
        return(
        <div>error</div>);
    }
}

export default Profile;