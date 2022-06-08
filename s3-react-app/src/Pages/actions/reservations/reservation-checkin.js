import React, { useEffect } from 'react'
import { useNavigate, useParams } from 'react-router';
import useAuth from '../../../hooks/useAuth'

import axios from '../../../api/axios';

const ReservationCheckInPage = () => {

  const { auth } = useAuth();
  const navigate = useNavigate();
  const {id} = useParams();

  const URL = `/${id}`

  const authorisation = {
    headers: { Authorization: 'Bearer ' + auth?.accessToken}
  }

  const checkIn = () => {
    let updateRequest = {
      'isCheckedIn': true
    }


    axios.put(URL, updateRequest, authorisation)
        .then(function(){})
        .catch(err => {
            console.log(err)
        })

    navigate('/employee/reservations')
  }

  useEffect(() => {
    checkIn()
  }, [])


  return (
    <div>Check in...</div>
  )
}

export default ReservationCheckInPage