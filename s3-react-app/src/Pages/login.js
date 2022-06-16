import React, { useEffect, useRef, useState } from 'react'
import { useLocation, useNavigate } from 'react-router'
import useAuth from '../hooks/useAuth'
import jwt_decode from "jwt-decode"

import axios from '../api/axios'
import { Link } from 'react-router-dom'
const URL = '/login'

const LoginPage = () => {

  const { setAuth } = useAuth();

  const navigate = useNavigate();
  const location = useLocation();

  const usernameRef = useRef();
  const errRef = useRef();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  useEffect(() => {
    usernameRef.current.focus();
  }, [])

  useEffect(() => {
    setErrorMsg('');
  }, [username, password]);

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
      <div>
        <div>
          <form onSubmit={handleSubmit}>
            <h1>Login to your account</h1>
            <p ref={errRef} className={errorMsg ? "errormsg" : "offscreen"}>{errorMsg}</p>
            <label>Username:</label>
            <input type={'text'} ref={usernameRef} autoComplete='off' value={username} onChange={(e) => setUsername(e.target.value)} required />
            <label>Password:</label>
            <input type={'password'} value={password} onChange={(e) => setPassword(e.target.value)} required />
            <button>Login</button>
          </form>
          <Link to={'/signup'}>Create Account</Link>
        </div>
      </div>
    </div>
  )
}

export default LoginPage