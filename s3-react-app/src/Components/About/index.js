import React from 'react'
import { AboutContainer, Heading, Row, FormCol, AboutCol, Form, InputDiv, Label, Input, Button } from './AboutStyled'

const About = () => {
  return (
    <>
        <AboutContainer>
            <Heading>About Us</Heading>
                <p>
                    The Gold Skye hotel is a hotel located in the center of Edinburgh, Scotland. We first opened in 1966 and our hotel
                    consists of five beautiful old buildings that are located right next to each other, with a total of 75 rooms. We offer
                    different room types for all different kinds of guests. Whether you are a solo traveller wanting to explore the Scottish
                    capitol, or want to start your family road trip through Scotland in Edinburgh, we have a room for every situation.
                </p>
                <p>
                    The Gold Skye hotel is located in the Haymarket Area, with a train station, a tram station and multiple bus stops right 
                    outside of your door. Princes Street, Old Town, the Royal Mile and the Castle are all located within walking distance, so
                    you can easily visit everything.
                </p>
        </AboutContainer>
    </>
  )
}

export default About