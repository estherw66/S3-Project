import React, { useState } from 'react'
import { Container, FormWrap, Icon, FormContent, Form, FormH1, FormLabel, FormInput, FormButton, Text } from './LoginStyled'

const Login = () => {

  return (
    <>
      <Container>
        <FormWrap>
          <Icon to='/'>LOGO</Icon>
          <FormContent>
            <Form>
              <FormH1>Login To Your Account:</FormH1>
              <FormLabel htmlFor='username'>Username:</FormLabel>
              <FormInput type='text' required/>
              <FormLabel htmlFor='password'>Password:</FormLabel>
              <FormInput type='password'required/>
              <FormButton type='submit'>Login</FormButton>
              <Text></Text>
            </Form>
          </FormContent>
        </FormWrap>
      </Container>
    </>
  )
}

export default Login