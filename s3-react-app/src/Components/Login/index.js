import React from 'react'
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
              <FormLabel htmlFor='for'>Email:</FormLabel>
              <FormInput type='email' required />
              <FormLabel htmlFor='for'>Password:</FormLabel>
              <FormInput type='password' required />
              <FormButton type='submit'>Login</FormButton>
              <Text>Create Account</Text>
            </Form>
          </FormContent>
        </FormWrap>
      </Container>
    </>
  )
}

export default Login