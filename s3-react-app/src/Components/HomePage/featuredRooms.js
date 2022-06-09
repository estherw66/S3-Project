import React, { useEffect, useState } from 'react'
import RoomCard from '../roomcard'

import axios from '../../api/axios'
const URL = '/rooms/featured'

const FeaturedRooms = () => {

    const [rooms, setRooms] = useState([])

    const getRooms = () => {
        axios.get(URL)
        .then((res) => {
            setRooms(res.data.rooms)
        })
        .catch((err) => {
            console.log(err)
        })
    }

    useEffect(() => {
        getRooms()
    }, [])

  return (
    <div className='home-container'>
        <h1 className='title'>Featured Rooms</h1>
        <div className='home-row'>
            {rooms.length > 0 ? (
                <>
                    {rooms?.map((room) => (
                        <RoomCard key={room.id} room={room}/>
                    ))}
                </>
            ) : (
                <p>Loading rooms...</p>
            )}
        </div>
    </div>
  )
}

export default FeaturedRooms