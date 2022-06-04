import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
// import authHeader from '../services/auth/auth-header'


const EmployeeProfilePage = () => {
  let navigate = useNavigate();

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
      <button onClick={getEmployeeById}> set employee</button>
    </div>
  )
}

export default EmployeeProfilePage