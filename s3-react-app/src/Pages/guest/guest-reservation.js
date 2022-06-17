import React, { useState, useEffect } from 'react'
import { Link, useParams } from 'react-router-dom'
import Sidebar from '../../Components/sidebar'
import useAuth from '../../hooks/useAuth'
import axios from '../../api/axios'

const GuestReservationsPage = () => {

    const { auth } = useAuth()
    const { id } = useParams()
    const [reservations, setReservations] = useState([])
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

    const getReservationsByUser = () => {
        const URL = `/reservations/${auth?.decoded?.employeeID}`
          axios.get(URL, authorisation)
          .then(res => {
            setReservations(res.data.reservations);
          })
          .catch(err => {
            console.log(err)
          })
    }
    
      useEffect(() => {
        getReservationsByUser()
      }, [])


  return (
    <div className='main'>
          <div className='row'>
              <div className='left'>
                  <Sidebar />
              </div>
              <div className='right'>
                  <div className='header'>
                      <h3>My Reservations:</h3>
                  </div>
                  <div className='body'>
                      <div className='row'>
                        <table className='table'>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Reservation Date</th>
                                    <th>Check In Date</th>
                                    <th>Check Out Date</th>
                                    <th>Amount Of Guests</th>
                                    <th>Total Price</th>
                                    <th>Check In Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                {reservations?.map(
                                    reservation =>
                                    <tr key={reservation.id}>
                                        <td>{reservation.id}</td>
                                        <td>{reservation.reservationDate}</td>
                                        <td>{reservation.checkInDate}</td>
                                        <td>{reservation.checkOutDate}</td>
                                        <td>{reservation.amountOfGuests}</td>
                                        <td>£{reservation.totalPrice}</td>
                                        <td>{reservation.checkedIn ? 'Checked In' : 'Not Checked In'}</td>
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

export default GuestReservationsPage