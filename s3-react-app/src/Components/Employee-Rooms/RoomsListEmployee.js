import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import axios from '../../api/axios';

const RoomsListEmployee = () => {

    const [roomsArray, setRoomsArray] = useState([]);

    const getAllRooms = () => {
        axios.get("http://localhost:8080/api/rooms")
        .then(res => {
          setRoomsArray(res.data);
          console.log(res.data);
        })
        .catch(err => {
          console.log(err);
        })
    }

    useEffect(() => {
        getAllRooms();
    }, [])


  return (
    <div>
        <h2>All Rooms:</h2>
        <Link to={'/employee/rooms/add'}>Add New Room</Link>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Room Type</th>
                        <th>Capacity</th>
                        <th>Price Per Night</th>
                        <th>Total Amount</th>
                        <th>Featured</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        roomsArray.rooms && roomsArray.rooms.map(
                            room => (
                                <tr key={room.id}>
                                    <td>{room.roomType}</td>
                                    <td>{room.capacity}</td>
                                    <td>{room.pricePerNight}</td>
                                    <td>{room.totalAmountInHotel}</td>
                                    <td>
                                        {room.featured ? 'Yes' : 'No'}
                                        <button style={{marginLeft: "15px"}}>{room.featured ? 'Feature' : 'Remove'}</button>
                                    </td>
                                    <td>
                                        
                                    </td>
                                </tr>
                            )
                        )
                    }
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default RoomsListEmployee