import React, { useEffect, useState } from 'react'

import axios from '../../api/axios'
import useAuth from '../../hooks/useAuth'

const ReservationForm = () => {
    const { auth } = useAuth()
    const [rooms, setRooms] = useState([])

    var today = new Date();
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1)
    var defaultCheckIn = today.toISOString().substring(0,10)
    var defaultCheckOut = tomorrow.toISOString().substring(0,10)

    const [checkInDate, setCheckInDate] = useState(defaultCheckIn)
    const [checkOutDate, setCheckOutDate] = useState(defaultCheckOut)
    const [amountOfGuests, setAmountOfGuests] = useState(2)
    const [room, setRoom] = useState({})
    const [guestID, setGuestID] = useState(0)
    

    const getAllRooms = () => {
        const URL = '/rooms'

        axios.get(URL)
        .then((res) => {
            setRooms(res.data.rooms)
        })
        .catch((err) => {
            console.log(err)
        })
    }

    useEffect(() => {
        setGuestID(auth?.decoded?.employeeID)
        getAllRooms()
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault()

        console.log(checkInDate)
        console.log(checkOutDate)
        console.log(amountOfGuests)
        console.log(room)
        console.log(guestID)
    }

  return (
    <div className='home-container'>
        <form onSubmit={handleSubmit}>
            <div className='home-row'>
                <label>Check In Date:</label>
                <input type={'date'} value={defaultCheckIn} onChange={(e) => setCheckInDate(e.target.value)} required />
                <label>Check Out Date:</label>
                <input type={'date'} value={defaultCheckOut} onChange={(e) => setCheckOutDate(e.target.value)} required />
                <label>Guests:</label>
                <input type={'number'} value={amountOfGuests} onChange={(e) => setAmountOfGuests(e.target.value)} required />
            </div>
            <div className='home-row'>
                <label>Room:</label>
                <select onChange={(e) => setRoom(rooms[e.target.value])}>
                    <option>--------</option>
                    {rooms?.map(
                        roomType =>
                        <option key={roomType?.id} value={roomType.id}>{roomType?.roomType}</option>
                    )}
                </select>
                <button>Make Reservation</button>
            </div>
        </form>
    </div>
  )
}

export default ReservationForm