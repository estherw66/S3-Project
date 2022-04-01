import React, { useState, useEffect } from 'react'
import RoomService from '../../Services/RoomService'
import RoomCard from './RoomCard'

const FeaturedRoomList = () => {

  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    retrieveRooms();
  }, []);

  const retrieveRooms = () => {
    RoomService.getAll()
    .then(response => {
      setRooms(response.data);
      console.log(response.data);
    })
    .catch(e => {
      console.log(e);
    })
  }

  const refreshList = () => {
    retrieveRooms();
  }

  return (
    <>
      {rooms.length > 0 ? (
        <>
          {rooms.map((room) => (
            <RoomCard key={room.id} type={room.type} price={room.basePricePerNight} imgUrl={room.imgUrl} />
          ))}
        </>
      ) : (
        <p>Loading rooms...</p>
      )}
    </>
  )
}

export default FeaturedRoomList