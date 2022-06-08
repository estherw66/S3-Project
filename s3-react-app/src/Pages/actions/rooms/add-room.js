import React, { useState } from 'react'
import { useNavigate } from 'react-router'
import Sidebar from '../../../Components/sidebar'
import useAuth from '../../../hooks/useAuth'
import axios from '../../../api/axios'

const AddRoomPage = () => {

    const navigate = useNavigate()
    const { auth } = useAuth()
    const URL = '/rooms'
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

    const [roomType, setRoomType] = useState('')
    const [capacity, setCapacity] = useState(0)
    const [pricePerNight, setPricePerNight] = useState(0)
    const [imageUrl, setImageUrl] = useState('')	
    const [totalAmountInHotel, setTotalAmountInHotel] = useState(0)

    const sendRequest = () => {
        let requestData = {
            'roomType': roomType,
            'capacity': capacity,
            'pricePerNight': pricePerNight,
            'imageUrl': imageUrl,
            'totalAmountInHotel': totalAmountInHotel,
            'featured': false
        }

        axios.post(URL, requestData, authorisation)
        .then(function(){})
          .catch(err => {
            console.log(err);
          }) 
    }

    const validateInput = () => {
        let errorMsg = ''

        if (roomType === ''){
            errorMsg += 'Please enter a Room Type. \n'
        }
        if (capacity === 0){
            errorMsg += 'Please enter a capacity. \n'
        }else if (capacity < 1){
            errorMsg += "Capacity cannot be less than 1. \n"
        }else if (capacity > 10){
            errorMsg += "Capacity cannot be more than 10. \n"
        }
        if (pricePerNight === 0){
            errorMsg += 'Please enter a price per night. \n'
        } else if (pricePerNight < 45){
            errorMsg += 'Price per night cannot be less than £45. \n'
        }
        if (totalAmountInHotel === 0){
            errorMsg += 'Please enter a total amount. \n'
        }else if (totalAmountInHotel < 1){
            errorMsg += 'Total amount cannot be less than 1. \n'
        }

        return errorMsg
    }

    const handleSubmit = (e) => {
        e.preventDefault();

        let errorMsg = validateInput();

        if (errorMsg !== ''){
        alert(errorMsg)
        } else {
        sendRequest();
        alert("Room Added")
        navigate('/employee/rooms')
        }
    }

  return (
    <div className='main'>
        <div className='row'>
            <div className='left'>
                <Sidebar />
            </div>
            <div className='right'>
                <div className='header'>
                    <h3>Add New Room</h3>
                </div>
                <div className='body'>
                    <form onSubmit={handleSubmit}>
                        <h4>Room Information</h4>
                        <div className='row'>
                            <div className='form-input'>
                                <input type={'text'} value={roomType} onChange={(e) => setRoomType(e.target.value)} required/>
                                <label>Room Type:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'number'} value={capacity} onChange={(e) => setCapacity(e.target.value)} required/>
                                <label>Capacity:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'text'} value={imageUrl} onChange={(e) => setImageUrl(e.target.value)} required />
                                <label>Image Url:</label>
                            </div>
                        </div>
                        <div className='row'>
                            <div className='form-input'>
                                <input type={'number'} value={pricePerNight} onChange={(e) => setPricePerNight(e.target.value)} required />
                                <label>Price Per Night:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'number'} value={totalAmountInHotel} onChange={(e) => setTotalAmountInHotel(e.target.value)} required />
                                <label>Total Amount In Hotel:</label>
                            </div>
                        </div>
                        <button>Save Room</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AddRoomPage