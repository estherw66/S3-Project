import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router-dom";

// styles
import './App.css';

// pages
import HomePage from "./pages";
import LoginPage from "./pages/login";
import RoomsPage from "./pages/rooms";
import Error from "./pages/error";
import AllRoomsPage from "./pages/all-rooms";
import EmployeesPage from "./pages/employees";
import UnauthorisedPage from "./pages/unauthorised";
import ProfilePage from "./pages/profile";
import Logout from "./pages/logout";
import ReservationsPage from "./pages/reservations";

// components
import RequireAuth from "./Components/RequireAuth";
import Navbar from "./Components/navbar";
import Footer from "./Components/footer";

import AddEmployee from "./Components/Employees/AddEmployee";
import ViewEmployeeDetails from "./Components/Employees/ViewEmployeeDetails";
import UpdateEmployee from "./Components/Employees/UpdateEmployee";
import AddRoom from "./Components/Employee-Rooms/AddRoom";


function App() {

    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }

    return (
       <>
        {/* <Navbar toggleMenu={toggleMenu} /> */}
        <Navbar />
        
        <Routes>
            <Route path="/" element={<HomePage />} />

            {/* public routes */}
            <Route path="/rooms" element={<RoomsPage />} />
            <Route path="/all-rooms" element={<AllRoomsPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/unauthorised" element={<UnauthorisedPage />} />

            {/* prive routes only for employees */}
            <Route element={<RequireAuth allowedRoles={["EMPLOYEE", "ADMIN"]}/>}>
                <Route path="/profile" element={<ProfilePage />} />

                <Route path="/employees" element={<EmployeesPage />} />
                <Route path="/employees/add" element={<AddEmployee />} />
                <Route path="/employees/:id" element={<ViewEmployeeDetails />} />
                <Route path="/employees/update/:id" element={<UpdateEmployee />} />

                <Route path="/employee/rooms/add" element={<AddRoom />} />

                <Route path="/employee/reservations" element={<ReservationsPage />} />

                <Route path="/logout" element={<Logout />} />
            </Route>

            {/* private routs only for guests */}
            <Route element={<RequireAuth allowedRoles={["ADMIN"]} />}>
                {/* TODO... */}
                <Route path="/employee/rooms" element={<RoomsPage />} />

            </Route>

            {/* error */}
            <Route path="/*" element={<Error />} />
        </Routes>
        {/* <Footer /> */}
        <Footer />
       </> 
    );
}

export default App;