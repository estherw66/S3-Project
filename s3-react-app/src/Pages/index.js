import React, { useState } from 'react'
import Sidebar from '../Components/Sidebar'
import Navbar from '../Components/Navbar'
import FeaturedRooms from '../Components/FeaturedRooms'

const Home = () => {

  return (
    <>
        <h1> Homepage</h1>
        <FeaturedRooms />
        <h1> About </h1>
    </>
  )
}

export default Home