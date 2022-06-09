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
          <li className='sidebar-row'>
            <Link to={'/profile'} className='side-link'>
              <div className='link'><FaUserAlt /> Account</div>
            </Link>
          </li>
          <li className='sidebar-row'>
            <Link to={'/employee/reservations'} className='side-link'>
              <div className='link'><HiOutlineTicket /> Reservations</div>
            </Link>
          </li>
          <li className='sidebar-row'>
            <Link to={'/employee/rooms'} className='side-link'>
              <div className='link'><FaHotel /> Rooms</div>
            </Link>
          </li>
          {isAdmin ? (
            <li className='sidebar-row'>
              <Link to={'/employees'} className='side-link'>
                <div className='link'><FaUsers /> Employees</div>
              </Link>
            </li>
          ) : (
            null
          )}
          <li className='sidebar-row'>
            <Link to={'/logout'} className='side-link'>
              <div className='link'><MdLogout /> Logout</div>
            </Link>
          </li>
        </ul>
      </div>
    ) : (
      <div className='sidebar'>
        <ul className='sidebar-list'>
          <li className='sidebar-row'>
            <Link to={'/profile'} className='side-link'><FaUserAlt /> Account</Link>
          </li>
          <li className='sidebar-row'>
            <Link to={`/guest/reservations/${auth?.decoded?.employeeID}`} className='side-link'>
              <div className='link'><HiOutlineTicket /> Reservations</div>
            </Link>
          </li>
          <li className='sidebar-row'>
            <Link to={'/logout'} className='side-link'>
              <div className='link'><MdLogout /> Logout</div>
            </Link>
          </li>
        </ul>
      </div>
    )}
    </>
  )
}

export default Sidebar