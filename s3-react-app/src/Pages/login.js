import React, { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router'
import useAuth from '../hooks/useAuth'
import jwt_decode from "jwt-decode"

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

import axios from '../api/axios'
import { Link } from 'react-router-dom'
const URL = '/login'


const ENDPOINT = "http://localhost:8080/ws";

const LoginPage = () => {

  const { setAuth } = useAuth();

  const navigate = useNavigate();

  const usernameRef = useRef();
  const errRef = useRef();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  
  const [stompClient, setStompClient] = useState(null)

  useEffect(() => {
    usernameRef.current.focus();
  }, [])

  useEffect(() => {
    setErrorMsg('');
  }, [username, password]);

  const onMessageReceived = (data) => {
    const result = JSON.parse(data.body)
    alert(
        "A new Reservation has been made! \n" +
        `The check in date is:  ${result.checkInDate}` +
        `\n The check out date is: ${result.checkOutDate}` +
        `\n The reservation is made for room ${result.roomType}`
      )
    console.log(data)
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(URL, 
          JSON.stringify({username, password}),
          {
            headers: {'Content-Type': 'application/json'}
          }
        );

        const accessToken = response?.data?.accessToken;
        const decoded = jwt_decode(accessToken);
        const roles = decoded?.roles;
        setAuth({ accessToken, decoded, roles });

        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/messages', (data) => {
                onMessageReceived(data);
            });
        });
        setStompClient(stompClient);

        setUsername('');
        setPassword('');

        navigate('/profile');
    } catch (err) {
      if (!err?.response){
        setErrorMsg('No server response')
      } else if (err.response?.status === 400){
        setErrorMsg('Wrong credentials')
      } else if (err.response?.status === 401){
        setErrorMsg('Unauthorised')
      } else {
        setErrorMsg('Login Failed')
      }
      errRef.current.focus();
    }
  }
  return (
    <div className='main'>
      <div className='login-container'>
        <div className='login-box'>
          <form onSubmit={handleSubmit}>
            <h1>Login to your account</h1>
            <p ref={errRef} className={errorMsg ? "errormsg" : "offscreen"}>{errorMsg}</p>
            <label>Username:</label>
            <input className='login-input' type={'text'} ref={usernameRef} autoComplete='off' value={username} onChange={(e) => setUsername(e.target.value)} required />
            <label>Password:</label>
            <input className='login-input' type={'password'} value={password} onChange={(e) => setPassword(e.target.value)} required />
            <button className='btn'>Login</button>
          </form>
          <Link to={'/signup'} className='update-link'>Create Account</Link>
        </div>
      </div>
    </div>
  )
}

export default LoginPage