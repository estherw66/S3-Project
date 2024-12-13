import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router'

import axios from '../../api/axios'
import useAuth from '../../hooks/useAuth'

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const ENDPOINT = "http://localhost:8080/ws";

const ReservationForm = () => {
    const navigate = useNavigate()
    const { auth } = useAuth()
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

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

    const [stompClient, setStompClient] = useState(null)

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

        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        setStompClient(stompClient);
    }, [])

    const changeSelectRoom = (e) => {
        const id = e.target.value - 1
        setRoom(rooms[id])
    }
    const sendRequest = () => {
        const URL = '/reservations'

        let requestData = {
            'checkInDate': checkInDate,
            'checkOutDate': checkOutDate,
            'amountOfGuests': amountOfGuests,
            'guestID': guestID,
            'reservationRooms': [
                room
            ]
        }

        axios.post(URL, requestData, authorisation)
        .then(function(){})
          .catch(err => {
            console.log(err);
          }) 
    }

    const validateInput = () => {
        let errorMsg = ''

        if (room === null){
            errorMsg += 'Please select a room. \n'
        }
        if (checkInDate < defaultCheckIn){
            errorMsg += 'Check in date cannot be before today. \n'
        }
        if (amountOfGuests > room.capacity) {
            errorMsg += 'Amount of guests cannot be higher than room capacity'
        }

        return errorMsg
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        let errorMsg = validateInput();

        if (errorMsg !== ''){
            alert(errorMsg)
        } else {
            sendRequest()
            stompClient.send("/app/message", {}, JSON.stringify({
                'checkInDate': checkInDate,
                'checkOutDate': checkOutDate,
                'roomType': room.roomType
            }))
            alert("Reservation Made!")
            navigate(`/guest/reservations/${auth?.decoded?.employeeID}`)
        }
    }

  return (
    <div className='home-container'>
        <h2>Make a Reservation</h2>
        <form onSubmit={handleSubmit} className='reservation-form'>
            <div className='input inlined'>
                <label>Check In Date:</label>
                <input id='check-in' type={'date'} value={checkInDate} min={defaultCheckIn} onChange={(e) => setCheckInDate(e.target.value)} required />
            </div>
            <div className='input inlined'>
                <label>Check Out Date:</label>
                <input id='check-out' type={'date'} value={checkOutDate} min={defaultCheckOut} onChange={(e) => setCheckOutDate(e.target.value)} required />
            </div>
            <div className='input inlined'>
                <label>Guests:</label>
                <input id='guests' type={'number'} value={amountOfGuests} min={1} max={6} onChange={(e) => setAmountOfGuests(e.target.value)} required />
            </div>
            <div className='input inlined'>
                <label>Room:</label>
                <select id='room' onChange={changeSelectRoom}>
                    <option>--------</option>
                    {rooms?.map(
                        roomType =>
                        <option key={roomType?.id} value={roomType.id}>{roomType?.roomType}</option>
                    )}
                    {/* {rooms ? (
                        <>
                        {rooms?.map((roomType) => (
                            room.totalAmountInHotel !== 0 ? (
                            <>
                                <option key={roomType?.id} value={roomType.id}>{roomType?.roomType}</option>
                            </>
                            ) : (null)
                        ))}
                        </>
                    ) : (
                        <p>Loading rooms...</p>
                    )} */}
                </select>
            </div>
            <div id='make-reservation' className='input'>
                <button className='btn'>Make Reservation</button>
            </div>
        </form>
    </div>
  )
}

export default ReservationForm