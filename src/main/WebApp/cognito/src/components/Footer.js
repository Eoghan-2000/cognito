//Eoghan Martin - C18342116 - Final Year Project - Cognito
import React from 'react';
import {Navbar,Container,Col} from 'react-bootstrap';


class Footer extends React.Component{
    render() {
        //footer
        return (
            <Navbar fixed="bottom" bg="dark" variant="dark">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>All Rights Reserved by Eoghan Martin</div>
                    </Col>
                </Container>
            </Navbar>
        );
    }
}

export default Footer;