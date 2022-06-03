import React, { useEffect, useState } from 'react'
import axios from '../../api/axios';
import { Link } from 'react-router-dom';

const EmployeesList = () => {

    const [employeesArray, setEmployeesArray] = useState([]);

    const getAllEmployees = () => {
        axios.get("http://localhost:8080/api/employees")
        .then(res => {
          setEmployeesArray(res.data);
          console.log(res.data);
        })
        .catch(err => {
          console.log(err);
        })
    }

    useEffect(() => {
      getAllEmployees();
    }, []);

  return (
    <div>
      <h2>All Employees:</h2>
      <div className='row'>
        <button>
          <Link to={"/employees/add"}>Add Employee</Link>
        </button>
        <div className='row'>
          <table>
          <thead>
                             <tr>
                                 <th>First Name</th>
                                 <th>Last Name</th>
                                 <th>Email</th>
                                 <th>Phone Number</th>
                                <th>Date Of Birth</th>
                             </tr>
                         </thead>
                         <tbody>
                             {
                                 employeesArray.employees && employeesArray.employees.map(
                                     employee => 
                                     <tr key={employee.id}>
                                         <td>{employee.firstName}</td>
                                         <td>{employee.lastName}</td>
                                        <td>
                                          <Link to={`/employees/${employee.id}`}>
                                            {employee.email}
                                          </Link>
                                        </td>
                                        <td>{employee.phoneNumber}</td>
                                         <td>{employee.dateOfBirth}</td>
                                     </tr>
                                 )
                             }
                         </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default EmployeesList