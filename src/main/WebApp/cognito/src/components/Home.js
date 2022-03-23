import { useAuth0 } from '@auth0/auth0-react';
import {Button,Card, Form } from 'react-bootstrap';
import {useEffect, useState} from 'react';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import Moment from 'moment';

function Home(){
    //Declare user from auth0
    const {user} = useAuth0();
    const currentUser = new CurrentUser(user.name);
    const users = [] 
    Moment.locale('en');

    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const[newPost,setNewPost]= useState("");
    const[listOfPosts,setListOfPosts] = useState([]);

    //When the user types in the text box the value is changed to their input
    const handleChange = e => {
        setNewPost(e.target.value);
      };
    
    // sends the current user and the post that they submit
    const makePost = e => {
        UserService.makeUserPost(currentUser.username,newPost);
        setNewPost("")
        getPosts()
    };

    function getPosts(){
        UserService.getFriendsList(currentUser.username).then((response) =>{
            if(response.data){
            users.push(response.data)
            users.push(currentUser.username)
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
            }})
        }});
    }

    // gets called once
    useEffect(() =>{
        getPosts();
    },[]);
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