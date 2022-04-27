import { useAuth0 } from '@auth0/auth0-react';
import {Button,Card, Form } from 'react-bootstrap';
import {useEffect, useState} from 'react';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import Moment from 'moment';
import React, { useReducer } from 'react';

function Home(){
    //Declare user from auth0
    const {user} = useAuth0();
    const delay = ms => new Promise(res => setTimeout(res, ms));
    const users = [] 
    Moment.locale('en');

    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const[newPost,setNewPost]= useState("");
    const[listOfPosts,setListOfPosts] = useState([]);
    const[userDetails,setUserDetails] = useState([]);

    //When the user types in the text box the value is changed to their input
    const handleChange = e => {
        setNewPost(e.target.value);
      };
    
    // sends the current user and the post that they submit
    const makePost = async e => {
        UserService.makeUserPost(userDetails.username,newPost);
        setNewPost("");
        await delay(1000);
        getCurrentUser();
    }

    function getPosts(current){
        UserService.getFriendsList(current.username).then((response) =>{
            if(response.data){
            users.push(response.data);
            users.push(current.username);
            UserService.getPosts.apply(null, users).then((response) =>{
                //Set returned posts from database as react front end values 
                if(response.data){
                setListOfPosts(response.data.map((u) =>
                <Card className="bg-light">
                <Card.Body>
                <Card.Title class="lightcard">{u.user}</Card.Title>
                <Card.Subtitle class="lightcard">{Moment(u.time).format('h:mma')} {Moment(u.time).format('DD-MM-YYYY')}</Card.Subtitle>
                <Card.Text class="lightcard">
                {u.content}
                </Card.Text>
                </Card.Body>
                </Card>))
            }});
        }});
    }
    function getCurrentUser(){
        UserService.searchUserbyEmail(user.name).then((response) =>{
          setUserDetails(response.data);
          getPosts(response.data);
        });
      }
    // gets called once
    useEffect(() =>{
        getCurrentUser();
    },[]);
    
    // setTimeout(setRefresh(!refresh), 5000);
        return(
            <div className="d-grid gap-2">
                <Form>
                 {/*Users new post input*/}
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Control as="textarea" rows={3} value={newPost} onChange={handleChange}/>
                </Form.Group>
                </Form>
                <Button className="bg-dark" size="lg" onClick={makePost}>Make Post</Button>
                {listOfPosts && listOfPosts}
                {!listOfPosts && <p>No posts</p>}
            </div>
        );
    }


export default Home;