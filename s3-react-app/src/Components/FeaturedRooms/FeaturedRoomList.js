import axios from 'axios';
import React, { useState, useEffect } from 'react'
import RoomService from '../../Services/RoomService'
import RoomCard from './RoomCard'

const FeaturedRoomList = () => {

  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    getRooms();
  }, []);

  const getRooms = () => {
    axios.get(`http://localhost:8080/api/rooms`)
    .then(res => {
      setRooms(res.data);
      console.log(res.data);
    })
    .catch(err => {
      console.log(err);
    })
  }

  return (
    <>
      {rooms.length > 0 ? (
        <>
          <h1>room</h1>
          {rooms.map((room) => (
            <RoomCard key={room.id} type={room.type} price={room.basePricePerNight} imgUrl={room.imgUrl} />
          ))}
        </>
      ) : (
        <p>Loading rooms...</p>
      )}
      {/* {rooms.map((room) => (
        <h1 key={room.id}>Room</h1>
      ))} */}
    </>
  )
}

export default FeaturedRoomList