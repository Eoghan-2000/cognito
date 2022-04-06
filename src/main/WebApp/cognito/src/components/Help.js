import { useAuth0 } from '@auth0/auth0-react';
import {Button,Card, Form } from 'react-bootstrap';
import {useEffect, useState} from 'react';
import { CurrentUser } from '../business/CurrentUser';
import UserService from '../services/UserService';
import Moment from 'moment';

function Help(){
        return(
            <div className="float-child">
               <h1>Help Page</h1>
               <p>Welcome to Cognito, the social media application based on trust. There are some instructions below regarding some of the info about how to navigate the application.</p>
               <h2>Navigation</h2>
               <ul>
                   <li>To navigate the application you will use the bar at the top of the page. On the left you will see a dropdown beside the cognito logo. This is will help you nevaigate between pages such as home, messages and this page, help. This will allow you to naviagate between these pages.</li>
                   <li>As well as this users will be able to search to the right of this dropdown by typing in a user or search query and pressing the search button.</li>
                   <li>Once on the search results page it will lead you to a search results page in which you can view users profiles.</li>
                   <li>The other page that you can navigate to is your own profile page when you press your username on the top right of the page.</li>
                   <li>Users can also view notifications by pressing the bell icon on the top right and users can logout by using the logout button to the right of this.</li>
               </ul>
               <h2>Home Page</h2>
               <p>When on the home page you can view all of your connections posts, just scrolll to see more! You can also post by typing in the text box at the top and pressing 'Make Post'</p>
               <h2>Messages</h2>
               <p>Users can see messages from connections when on the messages page. If you want to see messages from a particular user press their name on the left side of the page! Like the home page there is a text box and a send button at the end of the conversation, use this to send the user a message!</p>
               <h2>Profile</h2>
               <h3>Other User Profiles</h3>
               <ul>
                   <li>When on a users profile page you can send trust requests or untrust users using the buttons on the bottom left. There is also buttons to report users here which will report a user that you want to be investigated. </li>
                   <li>There is also an interactive map on the right of the page which shows the path between you and the other user. Interact with the map for more info!</li>
                   <li>You can also see users information on the left, which includes degree of seperation and personal trust. The degree of speration is the number of users inbetween you and another user, the personal trust indicates how much you should trust this user based on your connections trust levels for them and the degree of seperation.</li>
               </ul>
               <h3>Own Profile</h3>
               <ul>
                   <li>Like the profile of another users you have the information on the left with a button, this time its to edit your profile. When clicked you can edit your details.</li>
                   <li>On the right you can see another interactive map but this time it is your friends map, interact for more information!</li>
               </ul>
               <h2>Trust Requests</h2>
               <p>When seeing notifications you can acceptor decline notifications. When accepting you are asked for two different things, relationship type and trust level, please define thje relationship type with no spaces, use underscores if needed. An example of this would be Relationship Type: Best_Friend</p>
            <br/>
            <h3>Enjoy :)</h3>
            </div>
        );
    }


export default Help;