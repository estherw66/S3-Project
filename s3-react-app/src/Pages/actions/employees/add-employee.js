import React, { useState } from 'react'
import { useNavigate } from 'react-router'

import axios from '../../../api/axios';
import Sidebar from '../../../Components/sidebar';
import useAuth from '../../../hooks/useAuth';
const URL = '/employees'

const AddEmployeePage = () => {
    const navigate = useNavigate();
    const { auth } = useAuth();

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [dateOfBirth, setDateOfBirth] = useState('');
    const [streetName, setStreetName] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [city, setCity] = useState('');


    const sendRequest = () => {
        let requestData = {
            'firstName': firstName,
            'lastName': lastName,
            'phoneNumber': phoneNumber,
            'dateOfBirth': dateOfBirth,
            'streetName': streetName,
            'zipCode': zipCode,
            'city': city
          };
      
          axios.post(URL, requestData,
            {
                headers: { Authorization: "Bearer " + auth.accessToken}
            })
          .then(function(){})
          .catch(err => {
            console.log(err);
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
        if (dateOfBirth === ''){
          errorMsg += 'Please enter a date of birth.\n'
        }
        if (streetName === ''){
          errorMsg += 'Please enter a street name.\n'
        }
        if (zipCode === ''){
          errorMsg += 'Please enter a zip code.\n'
        }
        if (city === ''){
          errorMsg += 'Please enter a city. \n'
        }
    
        return errorMsg;
    }

    const handleSubmit = (e) => {
        let errorMsg = validateInput();

        if (errorMsg !== ''){
        alert(errorMsg)
        } else {
        sendRequest();
        alert("Employee Added")
        navigate('/employees')
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
            <h3>Add New Employee</h3>
          </div>
          <div className='body'>
            <form onSubmit={handleSubmit}>
              <h4>Employee Information</h4>
              <div className='row'>
                <div className='form-input'>
                  <input id='first-name' type={'text'} value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
                  <label>First Name:</label>
                </div>
                <div className='form-input'>
                  <input id='last-name' type={'text'} value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                  <label>Last Name:</label>
                </div>
                <div className='form-input'>
                  <input id='phone-number' type={'text'} value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} required />
                  <label>Phone Number:</label>
                </div>
              </div>
              <div className='row'>
                <label>Date of Birth: </label>
                <input id='date-of-birth' type={'date'} value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} required />
              </div><br/>
              <h4>Address Information</h4>
              <div className='row'>
                <div className='form-input'>
                  <input id='street-name' type={'text'} value={streetName} onChange={(e) => setStreetName(e.target.value)} required />
                  <label>Street Name:</label>
                </div>
                <div className='form-input'>
                  <input id='zip-code' type={'text'} value={zipCode} onChange={(e) => setZipCode(e.target.value)} required />
                  <label>Zip Code:</label>
                </div>
                <div className='form-input'>
                  <input id='city' type={'text'} value={city} onChange={(e) => setCity(e.target.value)} required />
                  <label>City:</label>
                </div>
              </div>
              <button className='btn' id='save-changes'>Save Changes</button>
            </form>
          </div> 
        </div>
      </div>
    </div>
  )
}

export default AddEmployeePage