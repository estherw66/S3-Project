import React from 'react'
import AllRoomsList from './AllRoomsList'


const AllRooms = () => {
  return (
      <div className='home-container'>
        <h1 className='title'>All Rooms</h1>
        <div className='home-row'>
          <AllRoomsList/>
        </div>
      </ div>
  )
}

export default AllRooms