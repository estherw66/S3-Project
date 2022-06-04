import React, { useState } from 'react'
import FeaturedRooms from '../Components/FeaturedRooms'
import ReservationForm from '../Components/ReservationForm'
import About from '../Components/About'

const HomePage = () => {

  return (
    <>
        <ReservationForm />
        <FeaturedRooms />
        <About />
    </>
  )
}

export default HomePage