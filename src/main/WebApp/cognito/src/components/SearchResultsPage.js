import {Card} from 'react-bootstrap';
import UserService from '../services/UserService';
import React, { useState, useEffect } from 'react';
import {Link, useLocation } from 'react-router-dom'

const SearchResultsPage = (props) => {
    //gets search string from users search
    const location = useLocation();
    const { search } =  location.state;
    //state for users data from search result
    const [userData, setUserData] = useState([]);
    //gets called when component is loaded once
    useEffect(() =>{
        //finds users based on search string
        UserService.searchUsers(search).then((response) =>{
            setUserData(response.data)
        });
    },[]);
    //sets result to mapped renderable react code for search results
    if(userData){
        const listofusers = userData.map((u) =>
        <Card className="bg-dark">
        <Card.Body>
        <Card.Title>{u.firstname} {u.surname}</Card.Title>
        <Card.Subtitle>{u.username}</Card.Subtitle>
        <Link className='navbar-brand' to="/profile" state={{search: u.username}}>View Profile</Link>
        </Card.Body>
        </Card>
    );
    //return the result
        return (
            <div>
            {listofusers}
            </div>
        );
    }
    //if there is no result return this
    else{
        return(
            <div class="text-center">No Users Found</div>
        );
    }
}

export default SearchResultsPage;