import React, { useState } from 'react'
import Sidebar from '../Components/Sidebar'
import Navbar from '../Components/Navbar'

const Home = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }


  return (
    <>
        <Sidebar isOpen={isOpen} toggleMenu={toggleMenu} />
        <Navbar toggleMenu={toggleMenu} />
    </>
  )
}

export default Home