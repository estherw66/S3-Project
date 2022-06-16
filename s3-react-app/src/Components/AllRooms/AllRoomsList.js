import axios from 'axios';
import React, { useState, useEffect } from 'react'
import RoomCard from '../roomcard';

const AllRoomsList = () => {
  const [rooms, setRooms] = useState([]);


  useEffect(() => {
    getRooms();
  }, []);

  const getRooms = () => {
    axios.get(`http://localhost:8080/api/rooms`)
    .then(res => {
      setRooms(res.data.rooms);
    })
    .catch(err => {
      console.log(err);
    })
  }

  return (
    <>
      {rooms ? (
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
    </>
  )
}

export default AllRoomsList
