import React, { useEffect, useState } from 'react'
import '../Home.css'

import axios from '../api/axios'
import RoomCard from '../Components/roomcard'
const URL = "/rooms"

const AllRoomsPage = () => {
  
  const [rooms, setRooms] = useState([])

  useEffect(() => {
    getRooms()
  }, [])

  const getRooms = () => {
    axios.get(URL)
    .then(res => {
      setRooms(res.data.rooms)
    })
    .catch(err => {
      console.log(err)
    })
  }

  return (
    // <>
    //     <AllRooms/>
    // </>
    <div className='home-container'>
      <h1 className='title'>All Rooms</h1>
      <div className='home-row'>
        {rooms.length > 0 ? (
          <>
            {rooms?.map((room) => (
              room.totalAmountInHotel !== 0 ? (
                <>
                  <RoomCard key={room.id} room={room} />
                </>
              ) : (null)
            ))}
          </>
        ) : (
          <p>Loading rooms...</p>
        )}
      </div>
    </div>
  )
}

export default AllRoomsPage
