import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

import axios from '../api/axios'
import Sidebar from '../Components/sidebar'
import useAuth from '../hooks/useAuth'
const URL = '/reservations'

const ReservationsPage = () => {
    const { auth } = useAuth();
    const [reservations, setReservations] = useState([]);
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

    const getAllReservations = () => {
        axios.get(URL, authorisation)
        .then(res => {
            setReservations(res.data.reservations)
        })
        .catch(err => {
            console.log(err)
        })
    }
    
    useEffect(() => {
        getAllReservations()
    }, [])



    // const updateUser = (e) => {
    //     e.preventDefault(); 
    //     console.log("hello" + firstName, lastName, phoneNumber)

    //     let updateRequest = {
    //         'firstName': firstName,
    //         'lastName': lastName,
    //         'phoneNumber': phoneNumber
    //     }
    //     axios.put(URL, updateRequest, authorisation)
    //     .then(function(){})
    //     .catch(err => {
    //         console.log(err)
    //     })
    // }

    const checkIn = (e) => {
        e.preventDefault();
        axios.put('http://localhost:8080/reservations/1', authorisation)
        .then(function(){})
        .catch(err => {
            console.log(err)
        })
    }

  return (
      <div className='main'>
          <div className='row'>
              <div className='left'>
                  <Sidebar />
              </div>
              <div className='right'>
                  <div className='header'>
                      <h3>Reservations:</h3>
                      <input type={'text'} placeholder='Search...' className='search-bar' />
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
                                        <td>{reservation.checkedIn ? 'Checked In' : 'Not Checked In'}</td>
                                        <td><button>{reservation.checkedIn ? 'Check Out' : 'Check In'}</button></td>
                                        <td><button><Link to={`/employee/reservation/checkin/${reservation.id}`}>{reservation.checkedIn ? 'Check Out' : 'Check In'}</Link></button></td>
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