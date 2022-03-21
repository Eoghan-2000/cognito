import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import {Button} from 'react-bootstrap'; 

const LogoutButton = () => {
  const { logout } = useAuth0();
  //button for logging out
  return (
    <Button id="logoutnav" onClick={() => logout({ returnTo: window.location.origin })}>
      Log Out
    </Button>
  );
};

export default LogoutButton;