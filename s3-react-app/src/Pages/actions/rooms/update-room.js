import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router';
import axios from '../../../api/axios';
import Sidebar from '../../../Components/sidebar';
import useAuth from '../../../hooks/useAuth';

const UpdateRoomPage = () => {

    const navigate = useNavigate()
    const {id} = useParams();
    const URL = `/rooms/${id}`

    const { auth } = useAuth();
    const authorisation = {
        headers: { Authorization: 'Bearer ' + auth?.accessToken}
    }

    const [room, setRoom] = useState({});

    const [pricePerNight, setPricePerNight] = useState(0)
    const [imageUrl, setImageUrl] = useState('')
    const [isFeatured, setIsFeatured] = useState(false)
    const [totalAmount, setTotalAmount] = useState(0)

    const getRoom = () =>{
        axios.get(URL, authorisation)
        .then(res => {
            setRoom(res.data)
            setPricePerNight(res.data.pricePerNight)
            setImageUrl(res.data.imageUrl)
            setIsFeatured(res.data.featured)
            setTotalAmount(res.data.totalAmountInHotel)
        })
        .catch(err => {
            console.log(err)
        })
    }

    useEffect(() => {
        getRoom();
    }, [])

    const sendRequest = () => {
        let updateRequest = {
            'pricePerNight': pricePerNight,
            'imageUrl': imageUrl,
            'totalAmountInHotel': totalAmount
        }

        axios.put(URL, updateRequest, authorisation)
        .then(function(){})
        .catch(err => {
            console.log(err)
        })
        
        navigate('/all-rooms')
    }

    const validateInput = () => {
        let errorMsg = ''

        if (pricePerNight === 0){
            errorMsg += 'Please enter a price per night. \n'
        } else if (pricePerNight < 45){
            errorMsg += 'Price per night cannot be less than £45. \n'
        }else if (totalAmount < 0){
            errorMsg += 'Total amount cannot be negative. \n'
        }

        return errorMsg
    }

    const handleSubmit = (e) => {
        e.preventDefault();

        let errorMsg = validateInput()

        if (errorMsg !== ''){
            alert(errorMsg)
        } else {
            sendRequest();
            alert("Room Updated")
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
                    <h3>Update {room?.roomType} Room</h3>
                </div>
                <div className='body'>
                    <form onSubmit={handleSubmit}>
                        <h4>Room</h4>
                        <div className='row'>
                            <div className='form-input'>
                                <input type={'number'} min={45} value={pricePerNight} onChange={(e) => setPricePerNight(e.target.value)} required />
                                <label>Price Per Night:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'text'} value={imageUrl} onChange={(e) => setImageUrl(e.target.value)} />
                                <label>Image URL:</label>
                            </div>
                            <div className='form-input'>
                                <input type={'number'} min={0} value={totalAmount} onChange={(e) => setTotalAmount(e.target.value)} required />
                                <label>Total Amount In Hotel:</label>
                            </div>
                        </div>
                        <button className='btn'>Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default UpdateRoomPage