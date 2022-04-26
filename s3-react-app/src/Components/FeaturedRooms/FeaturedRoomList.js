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
      {rooms.rooms ? (
        <>
          {rooms.rooms && rooms.rooms.map((room) => (
            // <RoomCard key={room.id} type={room.roomType} price={room.pricePerNight} imgUrl={room.imageUrl} />
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
