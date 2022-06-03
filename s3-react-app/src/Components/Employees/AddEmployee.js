import React, { useState } from 'react'
import axios from '../../api/axios';
import { useNavigate } from 'react-router-dom';

const AddEmployee = () => {

  let navigate = useNavigate();

  const [firstName, setFirstName] = useState('');
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

    axios.post(`http://localhost:8080/api/employees`, requestData)
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

  const saveEmployee = () => {
    let errorMsg = validateInput();

    if (errorMsg !== ''){
      alert(errorMsg)
    } else {
      sendRequest();
      navigate('/employees')
      window.location.reload()
    }
  }

  return (
    <div>
      <div className='container'>
        <div>
          <div>
            <form>
              <div className='form-input'>
                            <label>First Name:</label>
                            <input type="text" name='firstName' value={firstName} onChange={(e) => setFirstName(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>Last Name:</label>
                            <input type="text" name='lastName' value={lastName} onChange={(e) => setLastName(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>Phone Number:</label>
                            <input type="text" name='phoneNumber' value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>Date Of Birth:</label>
                            <input type="date" name='dateOfBirth' value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>Street Name:</label>
                            <input type="text" name='streetName' value={streetName} onChange={(e) => setStreetName(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>Zip Code:</label>
                            <input type="text" name='zipCode' value={zipCode} onChange={(e) => setZipCode(e.target.value)}/>
                        </div>
                        <div className='form-input'>
                            <label>City:</label>
                            <input type="text" name='city' value={city} onChange={(e) => setCity(e.target.value)}/>
                        </div>
                        <button className='btn btn-success' onClick={saveEmployee}>Save Employee</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AddEmployee