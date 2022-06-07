import axios from 'axios';
import React, { useState, useEffect } from 'react'
import RoomCard from './RoomCard'

const FeaturedRoomList = () => {
  const [roomsArray, setRoomsArray] = useState([]);


  useEffect(() => {
    getRooms();
  }, []);

  const getRooms = () => {
    axios.get(`http://localhost:8080/api/rooms/featured`)
    .then(res => {
      setRoomsArray(res.data);
      console.log(res.data);
    })
    .catch(err => {
      console.log(err);
    })
  }

  return (
    <>
      {roomsArray.rooms ? (
        <>
          {roomsArray.rooms && roomsArray.rooms.map((room) => (
            <RoomCard key={room.id} room={room} />
          ))}
        </>
      ) : (
        <p>Loading rooms...</p>
      )}
    </>
  )
}

export default FeaturedRoomList
