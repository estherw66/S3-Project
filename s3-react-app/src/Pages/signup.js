import React, { useState } from 'react'
import { useNavigate } from 'react-router'
import axios from '../api/axios'
const URL = '/guests'

const SingupPage = () => {
    const navigate = useNavigate()

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [errorMsg, setErrorMsg] = useState('');

    const handleSubmit = async (e) => {
      let requestData = {
        'firstName': firstName,
        'lastName': lastName,
        'email': email,
        'username': username,
        'password': password
    }

      e.preventDefault();

      try {
        const response = await axios.post(URL,
        requestData,
          {
            headers: {'Content-Type': 'application/json'}
          }  
        );

        navigate('/login')
      } catch(err) {
        if (err.response?.status === 400){
          setErrorMsg('Username or email already exists')
        } else if (err.response?.status === 500){
          setErrorMsg('No Server Response')
        } else {
          setErrorMsg('Create Account Failed')
        }
      }
    }

  return (
    <div className='main'>
        <h3>Sign Up:</h3>
        <p className={errorMsg ? "errormsg" : "offscreen"}>{errorMsg}</p>
        <form onSubmit={handleSubmit}>
            <label>First Name:</label>
            <input type={'text'} value={firstName} onChange={(e) => setFirstName(e.target.value)} required autoComplete='off' />
            <label>Last Name:</label>
            <input type={'text'} value={lastName} onChange={(e) => setLastName(e.target.value)} required />
            <label>Email:</label>
            <input type={'text'} value={email} onChange={(e) => setEmail(e.target.value)} required />
            <label>Username:</label>
            <input type={'text'} value={username} onChange={(e) => setUsername(e.target.value)} required autoComplete='off'/>
            <label>Password:</label>
            <input type={'password'} value={password} onChange={(e) => setPassword(e.target.value)} required autoComplete='off'/>
            <button>Create Account</button>
        </form>
    </div>
  )
}

export default SingupPage