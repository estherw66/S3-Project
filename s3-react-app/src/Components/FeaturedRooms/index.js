// import React, { useState } from 'react'

// const Login = () => {

//   return (
//     <>
//       <Container>
//         <FormWrap>
//           <Icon to='/'>LOGO</Icon>
//           <FormContent>
//             <Form>
//               <FormH1>Login To Your Account:</FormH1>
//               <FormLabel htmlFor='username'>Username:</FormLabel>
//               <FormInput type='text' required/>
//               <FormLabel htmlFor='password'>Password:</FormLabel>
//               <FormInput type='password'required/>
//               <FormButton type='submit'>Login</FormButton>
//               <Text></Text>
//             </Form>
//           </FormContent>
//         </FormWrap>
//       </Container>
//     </>
//   )
// }

// export default Login

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