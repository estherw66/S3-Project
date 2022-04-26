import React from 'react'
import { RoomCol, Card, Image, Heading3, SubTitle } from './FeaturedRoomStyled'

const RoomCard = ({ room }) => {
  return (
    <>
      <RoomCol>
        <Card>
            {/* {type === "SINGLE" ? (<Heading3>Single Room</Heading3>) : 
            type === "DOUBLE" ? (<Heading3>Double Room</Heading3>) : 
            type === "SINGLE_XXL" ? (<Heading3>Single XXL Room</Heading3>) :
            type === "DOUBLE_DELUXE" ? (<Heading3>Double Deluxe Room</Heading3>) :
            type === "FAMILY" ? (<Heading3>Family Room</Heading3>) :
            type === "FAMILY_SUPERIOR" ? (<Heading3>Superior Family Room</Heading3>) :
            (<></>)} */}
            <Heading3>{room.roomType} Room</Heading3>
            <Image src={room.imageUrl} />
            <SubTitle>From ${room.pricePerNight} per night</SubTitle>
        </Card>
      </RoomCol>
    </>
  )
}

export default RoomCard