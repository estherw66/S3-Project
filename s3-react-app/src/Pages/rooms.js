import React, { useEffect, useState } from 'react'
import Sidebar from '../Components/sidebar'
import useAuth from '../hooks/useAuth'
import axios from '../api/axios'
import { Link } from 'react-router-dom'
const URL = '/rooms'

const RoomsPage = () => {
  const { auth } = useAuth();
  const [rooms, setRooms] = useState([]);

  const getAllRooms = () => {
    axios.get(URL)
    .then(res => {
      setRooms(res.data.rooms)
    })
    .catch(err => {
      console.log(err)
    })
  }

  useEffect(() => {
    getAllRooms()
  }, [])

  return (
    <div className='main'>
      <div className='row'>
        <div className='left'>
          <Sidebar />
        </div>
        <div className='right'>
          <div className='header'>
            <h3>All Rooms:</h3>
            <input type={'text'} className='search-bar' placeholder='Search...'/>
            <Link to={`/employee/rooms/add`}>Add New Room</Link>
          </div>
          <div className='body'>
            <div className='row'>
              <table>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Room Type</th>
                    <th>Capacity</th>
                    <th>Price Per Night</th>
                    <th>Total Amount</th>
                    <th>Featured</th>
                    <th>Image</th>
                  </tr>
                </thead>
                <tbody>
                  {rooms?.map(
                    room =>
                    <tr key={room.id}>
                      <td>{room.id}</td>
                      <td>{room.roomType}</td>
                      <td>{room.capacity}</td>
                      <td>{room.pricePerNight}</td>
                      <td>{room.totalAmountInHotel}</td>
                      <td>{room.featured ? 'Yes' : 'No'}</td>
                      <td>{room.imageUrl === '' ? 'No' : 'Yes'}</td>
                      <td><Link to={`/employee/rooms/update/${room.id}`}>Update</Link></td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default RoomsPage