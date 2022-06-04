import React from 'react'
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth'

const Navbar = () => {
  
  const { auth } = useAuth();

  return (
    <>
      <nav>
        <div className='container'>
          <Link to={'/'} className='nav-logo'>GOLD SKYE</Link>
          <ul className='nav-menu'>
            {!auth?.decoded && <li className='nav-item'>
              <Link to={'/all-rooms'} className='nav-link'>All Rooms</Link>
            </li>}
          </ul>
          <nav className='nav-btn'>
            {!auth?.decoded && <Link to={'/login'} className='nav-btn-link'>Login</Link>}
            {auth?.decoded && <Link to={'/profile'} className='nav-btn-link'>{auth?.decoded.sub}</Link>}
          </nav>
        </div>
      </nav>
    </>
  )
}

export default Navbar 