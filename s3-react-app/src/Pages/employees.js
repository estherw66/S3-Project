import React, { useEffect, useState } from 'react'
import EmployeesList from '../Components/Employees/EmployeesList'

import axios from '../api/axios'
import useAuth from '../hooks/useAuth'
import Sidebar from '../Components/sidebar'
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
            <input type={'text'} placeholder={'Search...'} />
          </div>
          <div className='body'>
            <div className='row'>
              <table>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Date of Birth</th>
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