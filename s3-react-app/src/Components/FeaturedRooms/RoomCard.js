import React from 'react'
import { RoomCol, Card, Image, Heading3, SubTitle } from './FeaturedRoomStyled'

const RoomCard = ({ type, price, imgUrl, availableRooms }) => {
  return (
    <>
      <RoomCol>
        <Card>
          <Image src={imgUrl} />
            {type === "SINGLE" ? (<Heading3>Single Room</Heading3>) : 
            type === "DOUBLE" ? (<Heading3>Double Room</Heading3>) : 
            type === "SINGLE_XXL" ? (<Heading3>Single XXL Room</Heading3>) :
            type === "DOUBLE_DELUXE" ? (<Heading3>Double Deluxe Room</Heading3>) :
            type === "FAMILY" ? (<Heading3>Family Room</Heading3>) :
            type === "FAMILY_SUPERIOR" ? (<Heading3>Superior Family Room</Heading3>) :
            (<></>)}
            <SubTitle>From ${price} per night</SubTitle>
            <SubTitle>Still available: {availableRooms}</SubTitle>
        </Card>
      </RoomCol>
    </>
  )
}

export default RoomCard