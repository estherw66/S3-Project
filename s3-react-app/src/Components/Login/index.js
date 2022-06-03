import React, { useState, useRef, useEffect, useContext } from 'react'
import { useNavigate } from 'react-router';
import AuthContext from '../../context/AuthProvider';
import { Container, FormWrap, Icon, FormContent, Form, FormH1, FormLabel, FormInput, FormButton } from './LoginStyled'

import axios from '../../api/axios';
import authService from '../../Services/auth/auth.service';
const LOGIN_URL = '/login';

const Login = () => {
  let navigate = useNavigate();

  const { setAuth } = useContext(AuthContext);
  const usernameRef = useRef();
  const errRef = useRef();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    usernameRef.current.focus();
  }, [])

  useEffect(() => {
    setErrMsg('');
  }, [username, password])
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(LOGIN_URL,
        JSON.stringify({username, password}),
          {
            headers: { 'Content-Type': "application/json"}
          }
        );
        console.log(JSON.stringify(response?.data));
        const accessToken = response?.data?.accessToken;
        const roles = response?.data?.roles;
        setAuth({ username, password, roles, accessToken });
        setUsername('');
        setPassword('');
        setSuccess(true);
        console.log(success);
    } catch (err) {
      if (!err?.response){
        setErrMsg('No Server Response');
      } else if (err.response?.status === 400) {
        setErrMsg('Missing Username or Password')
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorised');
      } else {
        setErrMsg('Login Failed');
      }
      // errRef.current.focus();
    }
  }

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await authService.login(username, password)
      .then(() => {
        navigate('/profile');
        window.location.reload();
      },
      (error) => {
        console.log(error)
      });
    } catch (err) {
      console.log(err);
    }

  }

  return (
    <>
        {success ? (
          <Container>
            <FormWrap>
              <FormH1>You Are Logged In!</FormH1>
            </FormWrap>
          </Container>
        ) : (
          <Container>
            <FormWrap>
              <FormContent>
                <Form onSubmit={handleLogin}>
                  <FormH1>Login To Your Account:</FormH1>
                  <FormLabel htmlFor='username'>Username:</FormLabel>
                  <FormInput type='text' id='username' value={username} ref={usernameRef} autoComplete='off' onChange={(e) => setUsername(e.target.value)} required/>
                  <FormLabel htmlFor='password'>Password:</FormLabel>
                  <FormInput type='password' id='password' value={password} onChange={(e) => setPassword(e.target.value)} required/>
                  <FormButton>Login</FormButton>
                </Form>
              </FormContent>
            </FormWrap>
          </Container>
        )}        
    </>
  )
}

export default Login
