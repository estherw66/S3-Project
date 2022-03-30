// import React, { Component } from "react";

// export default class RoomCard extends Component {

//     state = {
//         type: '',

//     };

//     componentDidMount(){
//         const { type } = this.props;

//         this.setState({
//             type
//         });
//     }

//     render(){

//         return(
//             <div>
//                 <div>
//                     <h5>{this.state.type}</h5>
//                 </div>
//             </div>
//         );
//     }
// }

import React from 'react'
import { RoomCol, Image, Heading3, SubTitle } from './FeaturedRoomStyled'

const RoomCard = ({ type, price }) => {
  return (
    <>
        <RoomCol>
            <Image src="../img/double.jpeg" />
            <Heading3>{type}</Heading3>
            <SubTitle>From ${price} per night</SubTitle>
        </RoomCol>
    </>
  )
}

export default RoomCard