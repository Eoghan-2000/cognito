//Eoghan Martin - C18342116 - Final Year Project - Cognito
//Imports
import './App.css';
import {Container,Col} from 'react-bootstrap';
import {BrowserRouter as Router, Routes, Route,useNavigate} from 'react-router-dom';
import NavigationBar from "./components/NavigationBar";
import SpamCluster from "./components/SpamCluster";
import { useAuth0} from '@auth0/auth0-react';
import Home from './components/Home';
import Profile from './components/Profile';
import Messages from './components/Messages';
import SearchResultsPage from './components/SearchResultsPage';
import LoginButton from './components/LoginButton';
import EditProfile from './components/EditProfile';
import Help from './components/Help';

//App will always have a header footer and div container inbetween
//Sets a standard page throughout and the navigation bar will control what content is shown to the user
const App= () => {
    const {isLoading, error, isAuthenticated } = useAuth0();
    const marginTop = {
    marginTop:"20px",
    marginBottom:"20px"
    };
  if(error){
    return <div>{error.message}</div>
  }

  if(isLoading){
    return <div>Loading</div>
  }
  if(isAuthenticated)
  {
  return (
      <Router>
        <header className="App-header">
        <NavigationBar/>
        <Container>
        <Col lg={12} style={marginTop}>
            <div className="bg-dark text-white">
              {/* The different navigations for going through the web application using routes */}
                <Routes>      
                  <Route path="/spamcluster" exact element={<SpamCluster/>}/>
                  <Route path="/" exact element={<Home/>}/>
                  <Route path="/messages" exact element={<Messages/>}/>
                  <Route path="/profile" exact element={<Profile/>}/>
                  <Route path="/searchresults" exact element={<SearchResultsPage search/>}/>
                  <Route path="/spamcluster" exact element={<SpamCluster/>}/>
                  <Route path="/editprofile" exact element={<EditProfile/>}/>
                  <Route path="/help" exact element={<Help/>}/>
              </Routes>
              </div>
            </Col>
        </Container>
        </header>
        {/* <Footer/> */}
      </Router>
  );
}else{
  return(<>
    <div class="loggedout">
      <img class="navicon" src="AppLogo.png" width="100" height="100" alt="brand"/>
        <h1 class="whiteWriting">Welcome to cognito<br/></h1>   
        <LoginButton/>
   </div>
   </>
  );
}
}

export default App;
