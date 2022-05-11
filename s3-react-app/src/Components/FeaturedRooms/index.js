import React from 'react'
import { RoomContainer, Heading, Row } from './FeaturedRoomStyled'
import FeaturedRoomList from './FeaturedRoomList'

const FeaturedRooms = () => {
  return (
    <>
        <RoomContainer>
            <Heading>Featured Rooms</Heading>
            <Row>
                <FeaturedRoomList />
            </Row>
        </RoomContainer>
    </>
  )
}

export default FeaturedRooms