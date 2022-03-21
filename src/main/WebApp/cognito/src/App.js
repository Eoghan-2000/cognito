//Eoghan Martin - C18342116 - Final Year Project - Cognito
//Imports
import './App.css';
import {Container,Col} from 'react-bootstrap';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import SpamCluster from "./components/SpamCluster";
import { useAuth0 } from '@auth0/auth0-react';
import Home from './components/Home';
import Profile from './components/Profile';
import Messages from './components/Messages';
import SearchResultsPage from './components/SearchResultsPage';


//App will always have a header footer and div container inbetween
//Sets a standard page throughout and the navigation bar will control what content is shown to the user
const App= () => {
    const {isLoading, error, isAuthenticated } = useAuth0();
    const marginTop = {
    marginTop:"20px"
    };
    const posts = [{name: "Eoghan", content:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."},
                    {name:"Peter", content:"Proin at dui et libero mattis eleifend eu at tortor. Proin condimentum cursus dui, in pretium odio tincidunt quis. Sed sapien enim, faucibus nec odio nec, sagittis feugiat ipsum. Nam condimentum ultricies cursus. Maecenas ut nulla justo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque leo odio, varius sit amet posuere id, semper id est. Etiam quis porta nisi, nec vulputate urna. Nullam ut tristique neque. Integer id tellus tellus."},
                    {name:"Andrew", content:"Suspendisse laoreet arcu nulla, id tempus neque malesuada tincidunt. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras scelerisque sed libero ut feugiat. Vestibulum viverra pharetra quam. Ut efficitur ex sed finibus consequat. Phasellus cursus dolor in pellentesque ultrices. Cras nec ultricies mauris. Integer a ultricies tortor. Duis lacinia nisi lacus, vel pellentesque eros ultrices eu. Nullam tempus molestie turpis, ullamcorper rhoncus orci dictum sed. Vestibulum interdum odio erat, vehicula eleifend quam porta sit amet. Duis at aliquet quam, a venenatis augue. Nam rhoncus dictum justo nec rhoncus."}]
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
                  <Route path="/home" exact element={<Home uposts={posts}/>}/>
                  <Route path="/messages" exact element={<Messages umessages={posts}/>}/>
                  <Route path="/profile" exact element={<Profile/>}/>
                  <Route path="/searchresults" exact element={<SearchResultsPage search/>}/>
                  <Route path="/spamcluster" exact element={<SpamCluster/>}/>
              </Routes>
              </div>
            </Col>
        </Container>
        </header>
        {/* <Footer/> */}
      </Router>
  );
}else{
  return(
    <Router>
        <header className="App-header">
        <NavigationBar/>
        <Container>
        <Col lg={12} style={marginTop}>
            <div className="bg-dark text-white">
              {/* The different navigations for going through the web application using routes */}
              <Routes>
              </Routes>
              </div>
            </Col>
        </Container>
        </header>
        <Footer/>
      </Router>
  );
}
}

export default App;
