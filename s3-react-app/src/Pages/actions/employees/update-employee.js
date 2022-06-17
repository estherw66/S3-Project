import React, { useEffect, useState } from 'react'
import useAuth from '../../../hooks/useAuth'
import Sidebar from '../../../Components/sidebar';
import { useNavigate } from 'react-router';

import axios from '../../../api/axios';


const UpdateEmployeePage = () => {
    const navigate = useNavigate();
    const { auth } = useAuth();
    const [user, setUser] = useState({})  

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    
    const URL = `/employees/${auth?.decoded?.employeeID}`
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

    const getUser = () => {
        axios.get(URL, authorisation)
        .then(res => {
        setUser(res.data);
        setFirstName(res.data.firstName)
        setLastName(res.data.lastName)
        setPhoneNumber(res.data.phoneNumber)
        })
        .catch(err => {
        console.log(err)
        })
    }

    useEffect(() => {
        getUser()
    }, [])

    const sendRequest = () => {

        let updateRequest = {
            'firstName': firstName,
            'lastName': lastName,
            'phoneNumber': phoneNumber
        }
        axios.put(URL, updateRequest, authorisation)
        .then(function(){})
        .catch(err => {
            console.log(err)
        })
    }

    const validateInput = () => {
        let errorMsg = ''
    
        if (firstName === ''){
          errorMsg += 'Please enter a first name. \n'
        }
        if (lastName === ''){
          errorMsg += 'Please enter a last name.\n'
        }
        if (phoneNumber === ''){
          errorMsg += 'Please enter a phone number.\n'
        }
    
        return errorMsg;
    }


    const handleSubmit = (e) => {
        e.preventDefault(); 

        let errorMsg = validateInput();

        if (errorMsg !== ''){
        alert(errorMsg)
        } else {
        sendRequest();
        alert("update successful")
        navigate('/profile')
        }
    }
  return (
    <div className='main'>
        <div className='row'>
            <div className='left'>
                <Sidebar />
            </div>
            <div className='right'>
                <div className='header'>
                    <h3>Update Info</h3>
                </div>
                <div className='body'>
                    <form onSubmit={handleSubmit}>
                        <h4>User</h4>
                        <div className='row'>
                            <div className='form-input'>
                                <input type={'text'} value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
                                <label>First Name:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'text'} value={lastName} onChange={(e) => setLastName(e.target.value)} />
                                <label>Last Name:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'text'} value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
                                <label>Phone Number:</label>
                            </div>
                        </div>
                        <button className='btn'>Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default UpdateEmployeePage