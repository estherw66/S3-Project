import React, { useEffect, useState } from 'react'
import useAuth from '../hooks/useAuth'

import axios from '../api/axios'
import { Link } from 'react-router-dom';
import Sidebar from '../Components/sidebar';

const ProfilePage = () => {
  const { auth } = useAuth();

  const [user, setUser] = useState({});

  const [isEmployee, setIsEmployee] = useState(false);

    const checkStatus = () => {
      auth?.roles?.map((role) => {
        if (role === 'EMPLOYEE'){
          setIsEmployee(true)
        }
      })
    }

    useEffect(() => {
      checkStatus()
    }, [])


  
  const getUser = () => {
    const URL = `/employees/${auth?.decoded?.employeeID}`
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
    <div className='main'>
      <div className='row'>
        <div className='left'>
          <Sidebar />
        </div>
        <div className='right'>
          <div className='header'>
            <div className='my-account'>
              <h3>My Account</h3>
            </div>
            <div className='update-profile'>
              <Link to={`/employees/update/${auth?.decoded?.employeeID}`} className={'profile-btn'}>Update Info</Link>
            </div>
          </div>
          <div className='body'>
            <form className='profile-form'>
              <h4>User Information:</h4>
              <div className='row'>
                <div className='form-input'>
                  <input type={'text'} value={user?.firstName} readOnly/>
                  <label>First Name:</label>
                </div>
                <div className='form-input'>
                  <input type={'text'} value={user?.lastName} readOnly/>
                  <label>Last Name:</label>
                </div>
                <div className='form-input'>
                  <input type={'text'} value={user?.email} readOnly/>
                  <label>Email:</label>
                </div>
              </div>
              <div className='row'>
                <div className='form-input'>
                  <input type={'text'} value={user?.phoneNumber} readOnly/>
                  <label>Phone Number:</label>
                </div>
                <div className='form-input'>
                  <input type={'text'} value={user?.dateOfBirth} readOnly/>
                  <label>Date of Birth:</label>
                </div>
              </div>
              <h4>Account Information:</h4>
              <div className='row'>
                <div className='form-input'>
                  <input type={'text'} value={auth?.decoded?.sub} readOnly/>
                  <label>Username:</label>
                </div>
              </div>
              {isEmployee ? (
                <>
                <h4>Address Information:</h4>
                <div className='row'>
                  <div className='form-input'>
                    <input type={'text'} value={user?.address?.streetName} readOnly/>
                    <label>Street Name:</label>
                  </div>
                  <div className='form-input'>
                    <input type={'text'} value={user?.address?.zipCode} readOnly/>
                    <label>Zip Code:</label>
                  </div>
                  <div className='form-input'>
                    <input type={'text'} value={user?.address?.city} readOnly/>
                    <label>City:</label>
                  </div>
                </div>
                </>
              ) : (null)}
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProfilePage