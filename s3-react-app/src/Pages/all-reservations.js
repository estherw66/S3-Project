import React, { useEffect, useState } from 'react'

import axios from '../api/axios'
import Sidebar from '../Components/sidebar'
import useAuth from '../hooks/useAuth'
const URL = '/reservations'

const ReservationsPage = () => {
    const { auth } = useAuth();
    const [reservations, setReservations] = useState([]);
    const getAllReservations = () => {
        axios.get(URL, {headers: { Authorization: 'Bearer ' + auth.accessToken } })
        .then(res => {
            setReservations(res.data.reservations)
            console.log(res.data.reservations)
        })
        .catch(err => {
            console.log(err)
        })
    }
    
    useEffect(() => {
        getAllReservations()
    }, [])
  return (
      <div className='main'>
          <div className='row'>
              <div className='left'>
                  <Sidebar />
              </div>
              <div className='right'>
                  <div className='header'>
                      <h3>Reservations:</h3>
                      <input type={'text'} placeholder='Search...' />
                  </div>
                  <div className='body'>
                      <div className='row'>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Reservation Date</th>
                                    <th>Check In Date</th>
                                    <th>Check Out Date</th>
                                    <th>Amount Of Guests</th>
                                    <th>Total Price</th>
                                    <th>Check In Status</th>
                                    <th>Check In</th>
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
                                        <td>{reservation.isCheckedIn ? 'Checked In' : 'Not Checked In'}</td>
                                        <td><button>{reservation.isCheckedIn ? 'Check Out' : 'Check In'}</button></td>
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

export default ReservationsPage