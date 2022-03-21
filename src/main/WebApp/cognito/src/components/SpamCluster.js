//Eoghan Martin - C18342116 - Final Year Project - Cognito
import React from 'react';
import {Table} from 'react-bootstrap';
import UserService from '../services/UserService';

//component that will request spam user usernames fromn the back end and display them
class SpamCluster extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            users: []
        }
     }
        //call to front end user service that will connect to api
        componentDidMount(){
            UserService.getSpamCluster().then((response) =>{
                this.setState({users: response.data})
            });
        }
    render() {
    return (
        <div>
        <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                            <td>Username</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user =>
                                <tr key = {user.id}>
                                    <td>{user}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </Table>
        </div>
    );
    }
}

export default SpamCluster;