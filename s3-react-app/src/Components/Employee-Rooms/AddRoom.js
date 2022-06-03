import React, { useState } from 'react'
import { useNavigate } from 'react-router'
import axios from '../../api/axios';

const AddRoom = () => {

    let navigate = useNavigate();

    const [roomType, setRoomType] = useState('');
    const [capacity, setCapacity] = useState(0);
    const [pricePerNight, setPricePerNight] = useState(0);
    const [totalAmountInHotel, setTotalAmountInHotel] = useState(0);
    const [imageUrl, setImageUrl] = useState('');

    const [uploadingImage, setuploadingImage] = useState('');

    function saveImage(e){
        let file = e.target.files[0]
        let reader = new FileReader()
        reader.readAsDataURL(e.target.files[0])
        reader.onload = function (e) {
            let rawLog = reader.result.split(',')[1]

            setuploadingImage("Loading...")

            let dataSend = {
                dataReq: {data: rawLog, name: file.name, type: file.type},
                fname: "uploadFilesToGoogleDrive"
            }
            fetch('https://script.google.com/macros/s/AKfycbzaLVqToP4WpOx0LrAOBHqISp7-3NHK1xCD1ewo7gNw7OsvMr1c03azrrR4VJ_FPQ2s5w/exec',
            {method: "POST", body: JSON.stringify(dataSend)})
            .then(res => res.json()).then((a) => {
                addImage(a.url)
                setuploadingImage("")
            }).catch((e) => {
                setuploadingImage("Something went wrong, try again later. \n" + e)
            })
        }
    }

    function addImage(url) {
        let includeID = url.replace("file/d", "uc?export=view?&id=")
        let changeView = includeID.replace("/view?usp=drivesdk", "")

    }

    const sendRequest = () => {
        let requestData = {
            'roomType': roomType,
            'capacity': capacity,
            'pricePerNight': pricePerNight,
            'totalAmountInHotel': totalAmountInHotel,
            'imageUrl': imageUrl,
            'featured': false
        };

        axios.post(`http://localhost:8080/api/rooms`, requestData)
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

    const saveRoom = () => {
        let errorMsg = validateInput()

        if (errorMsg !== ''){
            alert(errorMsg)
        } else {
            sendRequest();
            navigate('/employee/rooms')
            window.location.reload();
        }
    }

  return (
    <div>
        <div className='container'>
            <div>
                <div>
                    <form>
                        <div className='form-input'>
                            <label>Room Type:</label>
                            <input type={'text'} name='roomType' value={roomType} onChange={(e) => setRoomType(e.target.value)} />
                        </div>
                        <div className='form-input'>
                            <label>Capacity:</label>
                            <input type={'number'} name='capacity' value={capacity} onChange={(e) => setCapacity(e.target.value)} min={'1'} max={'10'}/>
                        </div>
                        <div className='form-input'>
                            <label>Price Per Night:</label>
                            <input type={'number'} name='pricePerNight' value={pricePerNight} onChange={(e) => setPricePerNight(e.target.value)} min={'45'}/>
                        </div>
                        <div className='form-input'>
                            <label>Total Amount in Hotel:</label>
                            <input type={'number'} name='totalAmountInHotel' value={totalAmountInHotel} onChange={(e) => setTotalAmountInHotel(e.target.value)} min={'1'} max={'25'}/>
                        </div>
                        <div className='form-input'>
                            <label>Image Url:</label>
                            <input type={'text'} name='imgUrl' value={imageUrl} onChange={(e) => setImageUrl(e.target.value)} />
                        </div>
                        {/* <div className='form-input'>
                            <input type={'file'} accept='image/jpeg, image/png' onChange={(e) => saveImage(e)} />
                        </div> */}
                        <button onClick={saveRoom}>Save Room</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AddRoom