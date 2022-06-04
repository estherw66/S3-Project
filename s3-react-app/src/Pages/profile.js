import React, { useEffect, useState } from 'react'
import useAuth from '../hooks/useAuth'

import axios from '../api/axios'
import { Link } from 'react-router-dom';

const ProfilePage = () => {
  const { auth } = useAuth();

  const [user, setUser] = useState({});

  const URL = `/employees/${auth?.decoded?.employeeID}`
  const getUser = () => {
    axios.get(URL, { headers: {Authorization: 'Bearer ' + auth.accessToken }})
    .then(res => {
      setUser(res.data);
    })
    .catch(err => {
      console.log(err)
    })
  }

  useEffect(() => {
    getUser()
  }, [])
  
  return (
    <div>
      <ul>
        {auth?.roles?.find(role => "EMPLOYEE") && <li>
            <Link to={'/employees'}>Employees</Link>
          </li>}
          {auth?.roles?.find(role => "EMPLOYEE") && <li>
            <Link to={'/employee/rooms'}>Rooms</Link>
          </li>}
      </ul>
      <h1>Hello, {user && user.firstName} {user && user.lastName}</h1>
      <h2>Welcome to your profile!</h2>
    </div>
  )
}

export default ProfilePage