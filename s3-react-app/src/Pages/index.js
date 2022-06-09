import React, { useState } from 'react'
import useAuth from '../hooks/useAuth'
import FeaturedRooms from '../Components/HomePage/featuredRooms'
import About from '../Components/HomePage/about'
import '../Home.css'
import ReservationForm from '../Components/HomePage/reservationForm'
import Banner from '../Components/HomePage/banner'

// import 
const HomePage = () => {

  const { auth } = useAuth()

  return (
    <>
        {auth?.decoded ? (
          <ReservationForm />
        ) : ( <Banner />)}
        <FeaturedRooms />
        <About />
    </>
  )
}

export default HomePage