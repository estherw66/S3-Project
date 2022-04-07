import React, { useState } from 'react'
import FeaturedRooms from '../Components/FeaturedRooms'
import ReservationForm from '../Components/ReservationForm'
import About from '../Components/About'

const Home = () => {

  return (
    <>
        <ReservationForm />
        <FeaturedRooms />
        <About />
    </>
  )
}

export default Home