import React from 'react'
import { AboutContainer, Heading, Row, FormCol, AboutCol, Form, InputDiv, Label, Input, Button } from './AboutStyled'

const About = () => {
  return (
    <>
        <AboutContainer>
            <Heading>About Us</Heading>
                <Row>
                    <FormCol>
                        <Form>
                            <h3>Contact Us</h3>
                            <InputDiv>
                                <Label>Name:</Label>
                                <Input type={'text'} />
                            </InputDiv>
                            <InputDiv>
                                <Label>Email:</Label>
                                <Input type={'email'} />
                            </InputDiv>
                            <InputDiv>
                                <Label>Subject:</Label>
                                <Input type={'text'} />
                            </InputDiv>
                            <InputDiv>
                                <Label>Message:</Label>
                                <Input type={'text'} />
                            </InputDiv>
                            <Button>Submit</Button>
                        </Form>
                    </FormCol>
                    <AboutCol>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                            Mattis molestie a iaculis at erat. Quis varius quam quisque id diam. 
                            Aenean pharetra magna ac placerat. Vivamus at augue eget arcu dictum 
                            varius duis. Turpis massa tincidunt dui ut ornare lectus sit amet est. 
                            Semper eget duis at tellus at urna condimentum mattis. Aliquet nec 
                            ullamcorper sit amet risus nullam. Amet aliquam id diam maecenas 
                            ultricies mi eget. Tellus in metus vulputate eu scelerisque felis 
                            imperdiet.</p>
                    </AboutCol>
                </Row>
        </AboutContainer>
    </>
  )
}

export default About