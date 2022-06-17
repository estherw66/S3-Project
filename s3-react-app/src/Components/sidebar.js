import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth'

import {HiOutlineTicket} from 'react-icons/hi'
import {FaUserAlt} from 'react-icons/fa'
import {FaHotel} from 'react-icons/fa'
import {FaUsers} from 'react-icons/fa'
import {MdLogout} from 'react-icons/md'


const Sidebar = () => {
    const { auth } = useAuth();
    const [isAdmin, setIsAdmin] = useState(false);
    const [isEmployee, setIsEmployee] = useState(false);

    const checkStatus = () => {
      auth?.roles?.map((role) => {
        if (role === 'ADMIN'){
          setIsAdmin(true)
        }
        if (role === 'EMPLOYEE'){
          setIsEmployee(true)
        }
      })
    }

    useEffect(() => {
      checkStatus()
    }, [])

  return (
    <>
    {isEmployee ? (
      <div className='sidebar'>
        <ul className='sidebar-list'>
          <Link to={'/profile'} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><FaUserAlt /> Account</div>
            </li>
          </Link>
          <Link to={'/employee/reservations'} className='side-link'>
           <li className='sidebar-row'>
              <div className='link'><HiOutlineTicket /> Reservations</div>
            </li>
          </Link>
          <Link to={'/employee/rooms'} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><FaHotel /> Rooms</div>
            </li>
          </Link>
          {isAdmin ? (
            <Link to={'/employees'} className='side-link'>
              <li className='sidebar-row'>
                <div className='link'><FaUsers /> Employees</div>
              </li>
            </Link>
          ) : (
            null
          )}
          <Link to={'/logout'} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><MdLogout /> Logout</div>
            </li>
          </Link>
        </ul>
      </div>
    ) : (
      <div className='sidebar'>
        <ul className='sidebar-list'>
          <Link to={'/profile'} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><FaUserAlt /> Account</div>
            </li>
          </Link>
          <Link to={`/guest/reservations/${auth?.decoded?.employeeID}`} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><HiOutlineTicket /> Reservations</div>
            </li>
          </Link>
          <Link to={'/logout'} className='side-link'>
            <li className='sidebar-row'>
              <div className='link'><MdLogout /> Logout</div>
            </li>
          </Link>
        </ul>
      </div>
    )}
    </>
  )
}

export default Sidebar