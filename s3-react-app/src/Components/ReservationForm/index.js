import React, { useState, useEffect } from 'react'
import RoomService from '../../Services/RoomService';
import { FormContainer, Heading, Row, Form, InputDiv, Label, Input } from './ReservationFormStyled'

const ReservationForm = () => {

    const [rooms, setRooms] = useState([]);


    const handleSubmit = () => {
        RoomService.getAll()
        .then(res => {
            setRooms(res.data)
            console.log(res.data);
        })
        .catch(err =>{
            console.log(err);
        })
    }

  return (
    <>
        <FormContainer>
            <Heading>Make A Reservation</Heading>
            <Row>
                <div>
                    <img src='./img/hotel.jpeg' />
                </div>
                <Form onSubmit={handleSubmit}>
                    <InputDiv>
                        <Label>Check-In:</Label>
                        <Input type={'date'} />
                    </InputDiv>
                    <InputDiv>
                        <Label>Check-Out:</Label>
                        <Input type={'date'}/>
                    </InputDiv>
                    <InputDiv>
                        <Label>Guests:</Label>
                        <Input type={'number'} placeholder={'2'} min={'1'} max={'6'} />
                    </InputDiv>
                    <InputDiv>
                        <Label>Room Type:</Label>
                        <select>
                            <option value='null'>Not Specified</option>
                            <option value='single'>Single Room</option>
                            <option value='singlexxl'>Single XXL Room</option>
                            <option value='double'>Double Room</option>
                            <option value='doubledeluxe'>Double Deluxe Room</option>
                            <option value='family'>Family Room</option>
                            <option value='familysuperior'>Family Superior Room</option>
                        </select>
                    </InputDiv>
                    <button type='submit'>Search Room</button>
                </Form>
                <form onSubmit={handleSubmit}>
                    <button type='submit'>Search</button>
                </form>
                
            </Row>
        </FormContainer>
    </>
  )
}

export default ReservationForm