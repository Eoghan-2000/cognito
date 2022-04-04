import { useAuth0 } from '@auth0/auth0-react';
import {Button,Form} from 'react-bootstrap';
import {useEffect, useState} from 'react';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import { useNavigate } from 'react-router';

function EditProfile(){
    //Declare user from auth0
    const {user} = useAuth0();
    const navigate = useNavigate;
    const currentUser = new CurrentUser(user.name);

    //useStates to allow for finding users friends, posts, list of posts in rendered format and new posts by the user
    const [userDetails,setUserDetails] = useState([]);

    //When the user types in the text box the value is changed to their input
    const handleChange = e => {
        const name = e.target.name;
        const value = e.target.value;
        setUserDetails(values => ({...values, [name]: value}));
    };
    
    // sends the current user and the post that they submit
    const editProfile = e => {
        UserService.editProfile(currentUser.username,userDetails.username,userDetails.firstname,userDetails.surname,userDetails.location, userDetails.dob)
    };

    function getUser(){
        UserService.searchUserbyEmail(user.name).then((response) =>{
            setUserDetails(response.data);
        });
    }

    // gets called once
    useEffect(() =>{
        getUser();
    },[]);

        return(
            <div className="d-grid gap-2">
                <Form>
                 {/*Users new post input*/}
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Label>Username</Form.Label>
                <Form.Control name='username' value={userDetails.username} onChange={handleChange}/>
                <Form.Label>First Name</Form.Label>
                <Form.Control name='firstname' value={userDetails.firstname} onChange={handleChange}/>
                <Form.Label>Surname</Form.Label>
                <Form.Control name='surname' value={userDetails.surname} onChange={handleChange}/>
                <Form.Label>Location</Form.Label>
                <Form.Control name='location' value={userDetails.location} onChange={handleChange}/>
                <Form.Label>Date of Birth</Form.Label>
                <Form.Control type='date' name='DOB' value={userDetails.dob} onChange={handleChange}/>
                </Form.Group>
                </Form>
                <Button className="bg-dark" size="lg" onClick={editProfile}>Confirm Changes</Button>
            </div>
        );
    }


export default EditProfile;