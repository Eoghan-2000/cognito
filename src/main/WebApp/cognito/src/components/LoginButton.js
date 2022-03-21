import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import { Button} from 'react-bootstrap';

const LoginButton = () => {
  //button for logging in
  const { loginWithRedirect } = useAuth0();
  return <Button id="loginnav" onClick={() => loginWithRedirect({appState: { target: '/home' }})}>Log In</Button>;
};

export default LoginButton;