import React from 'react'
import { RoomContainer, Heading, Row } from '../FeaturedRooms/FeaturedRoomStyled'
import AllRoomsList from './AllRoomsList'


const AllRooms = () => {
  return (
      <>
        <Heading>All Rooms</Heading>
        <Row>
            <AllRoomsList />
        </Row>
      </>
  )
}

export default AllRooms