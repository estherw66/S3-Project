import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./Pages";
import LoginPage from "./Pages/login";
import RoomsPage from "./Pages/rooms";
import Error from "./Pages/error";
import Navbar from "./Components/Navbar";
import Sidebar from "./Components/Sidebar";
import Footer from "./Components/Footer";
import AllRoomsPage from "./Pages/allrooms";

function App() {

    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }

    return (
       <>
        <Sidebar isOpen={isOpen} toggleMenu={toggleMenu} />
        <Navbar toggleMenu={toggleMenu} />
        
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/rooms" element={<RoomsPage />} />
            <Route path="/all-rooms" element={<AllRoomsPage />} />
            <Route path="/*" element={<Error />} />
        </Routes>
        <Footer />
       </>
    );
}

export default App;