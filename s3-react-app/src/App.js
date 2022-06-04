import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router-dom";
import './App.css';
import HomePage from "./pages";
import LoginPage from "./pages/login";
import RoomsPage from "./pages/rooms";
import Error from "./pages/error";
import Sidebar from "./Components/Sidebar";
import AllRoomsPage from "./pages/allrooms";
import EmployeesPage from "./pages/employees";
import AddEmployee from "./Components/Employees/AddEmployee";
import ViewEmployeeDetails from "./Components/Employees/ViewEmployeeDetails";
import UpdateEmployee from "./Components/Employees/UpdateEmployee";
import AddRoom from "./Components/Employee-Rooms/AddRoom";
import RoomsListEmployee from "./Components/Employee-Rooms/RoomsListEmployee";
import EmployeeProfilePage from "./pages/employee-profile";
import UnauthorisedPage from "./pages/unauthorised";
import RequireAuth from "./Components/RequireAuth";
import Navbar from "./Components/navbar";

function App() {

    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen)
    }

    return (
       <>
        <Sidebar isOpen={isOpen} toggleMenu={toggleMenu} />
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
                <Route path="/profile" element={<EmployeeProfilePage />} />

                <Route path="/employees" element={<EmployeesPage />} />
                <Route path="/employees/add" element={<AddEmployee />} />
                <Route path="/employees/:id" element={<ViewEmployeeDetails />} />
                <Route path="/employees/update/:id" element={<UpdateEmployee />} />

                <Route path="/employee/rooms" element={<RoomsListEmployee />} />
                <Route path="/employee/rooms/add" element={<AddRoom />} />
            </Route>

            {/* private routs only for guests */}
            <Route element={<RequireAuth allowedRoles={["GUEST"]} />}>
                {/* TODO... */}
            </Route>

            {/* error */}
            <Route path="/*" element={<Error />} />
        </Routes>
        {/* <Footer /> */}
       </> 
    );
}

export default App;