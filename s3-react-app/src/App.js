import React, { useEffect, useState } from "react";
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
import EmployeesPage from "./Pages/employees";
import AddEmployee from "./Components/Employees/AddEmployee";
import ViewEmployeeDetails from "./Components/Employees/ViewEmployeeDetails";
import UpdateEmployee from "./Components/Employees/UpdateEmployee";
import AddRoom from "./Components/Employee-Rooms/AddRoom";
import RoomsListEmployee from "./Components/Employee-Rooms/RoomsListEmployee";
import EmployeeProfilePage from "./Pages/employee-profile";
import authService from "./Services/auth/auth.service";

function App() {

    const [isOpen, setIsOpen] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined)

    useEffect(() => {
        const user = authService.getCurrentUser();

        if (user){
            setCurrentUser(user);
        }
    }, [])

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }

    return (
       <>
        <Sidebar isOpen={isOpen} toggleMenu={toggleMenu} />
        <Navbar toggleMenu={toggleMenu} currentUser={currentUser}/>
        
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/rooms" element={<RoomsPage />} />
            <Route path="/all-rooms" element={<AllRoomsPage />} />
            <Route path="/*" element={<Error />} />

            <Route path="/profile" element={<EmployeeProfilePage />} />

            <Route path="/employees" element={<EmployeesPage />} />
            <Route path="/employees/add" element={<AddEmployee />} />
            <Route path="/employees/:id" element={<ViewEmployeeDetails />} />
            <Route path="/employees/update/:id" element={<UpdateEmployee />} />

            <Route path="/employee/rooms" element={<RoomsListEmployee />} />
            <Route path="/employee/rooms/add" element={<AddRoom />} />
        </Routes>
        <Footer />
       </> 
    );
}

export default App;