//Eoghan Martin - C18342116 - Final Year Project - Cognito
import React, { useEffect, useRef } from "react";
import useResizeAware from "react-resize-aware";
import PropTypes from "prop-types";
import { useAuth0 } from '@auth0/auth0-react';
import Neovis from "neovis.js/dist/neovis.js";

const FriendsMap = (props) => {
  const {user} = useAuth0();
  //Declare props
  const {
    width,
    height,
    containerId,
    backgroundColor,
    neo4jUri,
    neo4jUser,
    neo4jPassword,
    username1,
  } = props;

  const visRef = useRef();

  //if one user is sent in instead of two, get all the friends users 
  useEffect(() => {
    //if one user is sent in instead of two, get all the friends users 
    if(username1){
    const config = {
      container_id: visRef.current.id,
      server_url: neo4jUri,
      server_user: neo4jUser,
      server_password: neo4jPassword,
      labels: {
        User: {
          caption: "username"
          
        },
      },
      relationships: {
        "TRUSTS_EACHOTHER": {
          caption: true
      }
      },//cypher query for all friends of current user 
      initial_cypher:
        "MATCH (u:User {email:\'"+ user.name +"\'})-[rel]-(u2:User) RETURN *",
    };
    const vis = new Neovis(config);
    vis.render();
}}, [neo4jUri, neo4jUser, neo4jPassword]);

  return (
    <div
      id={containerId}
      ref={visRef}
      style={{
        height: '80vh',
        backgroundColor: `${backgroundColor}`
      }}
    />
  );
};


FriendsMap.defaultProps = {
  width: 600,
  height: 600,
  backgroundColor: "#262626",
};

FriendsMap.propTypes = {
  width: PropTypes.number.isRequired,
  height: PropTypes.number.isRequired,
  containerId: PropTypes.string.isRequired,
  neo4jUri: PropTypes.string.isRequired,
  neo4jUser: PropTypes.string.isRequired,
  neo4jPassword: PropTypes.string.isRequired,
  backgroundColor: PropTypes.string,
};

const ResponsiveNeoGraph = (props) => {
  const [resizeListener, sizes] = useResizeAware();

  const side = Math.max(sizes.width, sizes.height) / 2;
  const neoGraphProps = { ...props, width: side, height: side };
  return (
    <div style={{ position: "relative", color:"#262626" }}>
      {resizeListener}
      <FriendsMap {...neoGraphProps} />
    </div>
  );
};

ResponsiveNeoGraph.defaultProps = {
  backgroundColor: "#262626",
};

ResponsiveNeoGraph.propTypes = {
  containerId: PropTypes.string.isRequired,
  neo4jUri: PropTypes.string.isRequired,
  neo4jUser: PropTypes.string.isRequired,
  neo4jPassword: PropTypes.string.isRequired,
  backgroundColor: PropTypes.string,
};

export { FriendsMap, ResponsiveNeoGraph };