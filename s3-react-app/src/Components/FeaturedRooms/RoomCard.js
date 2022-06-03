import React from 'react'
import { RoomCol, Card, Image, Heading3, SubTitle } from './FeaturedRoomStyled'

const RoomCard = ({ room }) => {
  return (
    <>
      <RoomCol>
        <Card>
            <Heading3>{room.roomType} Room</Heading3>
            <Image src={room.imageUrl} />
            <SubTitle>From £{room.pricePerNight} per night</SubTitle>
        </Card>
      </RoomCol>
    </>
  )
}

export default RoomCard