import React, { useEffect, useState } from 'react'
import useAuth from '../hooks/useAuth';

import axios from '../api/axios';

const EmployeeProfilePage = () => {

  const [employee, setEmployee] = useState({});

  useEffect(() => {
    getEmployeeById()
  }, [])
  

  const getEmployeeById = () => {
    // return axios.get("http://localhost:8080/api/employees/7", { headers: authHeader() })
    // .then((res) => {
    //   setEmployee(res.data);
    //   console.log(employee)
    // })
  }

  return (
    <div>
      <h2>employee profile page</h2>
      
    </div>
  )
}

export default EmployeeProfilePage