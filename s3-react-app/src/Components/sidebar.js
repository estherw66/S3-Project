import React from 'react'
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth'
import { SidebarLinks } from './SidebarLinks';

const Sidebar = () => {
    const { auth } = useAuth();
  return (
    // <div>
    //     <ul>
    //     {auth?.roles?.find(role => "EMPLOYEE") && <li>
    //         <Link to={'/employees'}>Employees</Link>
    //       </li>}
    //       {auth?.roles?.find(role => "EMPLOYEE") && <li>
    //         <Link to={'/employee/rooms'}>Rooms</Link>
    //       </li>}
    //       <li>
    //         <Link to={'/employee/reservations'}>Reservations</Link>
    //       </li>
    //   </ul>
    // </div>
    <div className='sidebar'>
      <ul className='sidebar-list'>
        {SidebarLinks.map((link, key) => {
          return (
            <li key={key} className='sidebar-row'>
              <Link to={link.url} className='side-link'>
                <div className='link'>
                  {link.icon} {link.title}
                </div>
              </Link>
            </li>
          )
        })}
      </ul>
    </div>
  )
}

export default Sidebar