import React, { useEffect, useState } from 'react'
import {AiOutlineUserAdd} from 'react-icons/ai'

import axios from '../api/axios'
import useAuth from '../hooks/useAuth'
import Sidebar from '../Components/sidebar'
import { Link } from 'react-router-dom'
const URL = '/employees'

const EmployeesPage = () => {

  const { auth } = useAuth();

  const [employees, setEmployees] = useState([]);

  const getAllEmployees = () => {
    axios.get(URL, { headers: { Authorization: 'Bearer ' + auth.accessToken } })
    .then(res => {
      setEmployees(res.data.employees);
    })
    .catch(err => {
      console.log(err)
    })
  }

  useEffect(() => {
    getAllEmployees();
  }, [])

  return (
    <div className='main'>
      <div className='row'>
        <div className='left'>
          <Sidebar />
        </div>
        <div className='right'>
          <div className='header'>
            <h3>All Employees:</h3>
            <Link to={'/employees/add'} className='btn'>Add New <AiOutlineUserAdd /></Link>
          </div>
          <div className='body'>
            <div className='row'>
              <table className='table'>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Date of Birth</th>
                    <th>Street Name</th>
                    <th>Zip Code</th>
                    <th>City</th>
                  </tr>
                </thead>
                <tbody>
                  {employees?.map(
                    employee => 
                    <tr key={employee.id}>
                      <td>{employee.id}</td>
                      <td>{employee.firstName}</td>
                      <td>{employee.lastName}</td>
                      <td>{employee.email}</td>
                      <td>{employee.phoneNumber}</td>
                      <td>{employee.dateOfBirth}</td>
                      <td>{employee.address.streetName}</td>
                      <td>{employee.address.zipCode}</td>
                      <td>{employee.address.city}</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default EmployeesPage