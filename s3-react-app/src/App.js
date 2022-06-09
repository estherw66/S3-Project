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

import AddEmployeePage from "./pages/actions/employees/add-employee";
import UpdateEmployeePage from "./pages/actions/employees/update-employee";
import ReservationCheckInPage from "./pages/actions/reservations/reservation-checkin";
import UpdateRoomPage from "./pages/actions/rooms/update-room";
import AddRoomPage from "./pages/actions/rooms/add-room";


// components
import RequireAuth from "./Components/RequireAuth";
import Navbar from "./Components/navbar";
import Footer from "./Components/footer";

import ViewEmployeeDetails from "./Components/Employees/ViewEmployeeDetails";
import SingupPage from "./pages/signup";
import GuestReservationsPage from "./pages/guest/guest-reservation";



function App() {

    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }

    return (
       <>
        <Navbar />

        <Routes>
            <Route path="/" element={<HomePage />} />

            {/* public routes */}
            <Route path="/rooms" element={<RoomsPage />} />
            <Route path="/all-rooms" element={<AllRoomsPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/unauthorised" element={<UnauthorisedPage />} />
            <Route path="/signup" element={<SingupPage />} />

            <Route element={<RequireAuth allowedRoles={["EMPLOYEE", "ADMIN", "GUEST"]}/>}>
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/logout" element={<Logout />} />
            </Route>

            {/* prive routes only for employees */}
            <Route element={<RequireAuth allowedRoles={["EMPLOYEE", "ADMIN"]}/>}>

                <Route path="/employees/update/:id" element={<UpdateEmployeePage />} />

                <Route path="/employee/rooms" element={<RoomsPage />} />
                <Route path="/employee/rooms/add" element={<AddRoomPage />} />
                <Route path="/employee/rooms/update/:id" element={<UpdateRoomPage />} />

                <Route path="/employee/reservations" element={<ReservationsPage />} />
                <Route path="/employee/reservation/checkin/:id" element={<ReservationCheckInPage />} />
            </Route>

            {/* private routes only for admin */}
            <Route element={<RequireAuth allowedRoles={["ADMIN"]} />}>
                <Route path="/employees" element={<EmployeesPage />} />
                <Route path="/employees/add" element={<AddEmployeePage />} />
            </Route>

            {/* private routes only for guests */}
            <Route element={<RequireAuth allowedRoles={["GUEST"]} />}>
                <Route path="/guest/reservations/:id" element={<GuestReservationsPage />} />
            </Route>

            {/* error */}
            <Route path="/*" element={<Error />} />
        </Routes>

        <Footer />
       </> 
    );
}

export default App;