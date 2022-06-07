import React from 'react'

const RoomCard = ({ room }) => {
  return (
    <>
        <div className='card-col'>
            <div className='card'>
                <h3>{room.roomType} Room</h3>
                <img src={room.imageUrl} />
                <p>From £{room.pricePerNight} Per Night</p>
            </div>
        </div>
    </>
  )
}

export default RoomCard