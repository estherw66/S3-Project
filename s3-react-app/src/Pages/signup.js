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

    const sendRequest = () => {
        let requestData = {
            'firstName': firstName,
            'lastName': lastName,
            'email': email,
            'username': username,
            'password': password
        }

        axios.post(URL, requestData)
        .then(function(){})
          .catch(err => {
            console.log(err);
          })    
          
        navigate('/login')
    }

  return (
    <div className='main'>
        <h3>Sign Up:</h3>
        <form onSubmit={sendRequest}>
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