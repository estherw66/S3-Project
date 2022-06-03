import React, { useState } from 'react';
import { FaBars } from 'react-icons/fa'
import axios from '../../api/axios';
import { Nav, NavbarContainer, NavLogo, HamburgerMenu, NavMenu, NavItem, NavLinks, NavBtn, NavBtnLink } from './NavbarStyled';

const Navbar = ({ toggleMenu, currentUser }) => {

  const [authorisation, setAuthorisation] = useState('');
  
  const login = (username, password) => {
    axios.post(`http://localhost:8080/api/login`, {
      "username":username,
      "pasword":password
    })
    .then(res => {
      localStorage.setItem("token", res.data.accessToken);
      localStorage.setItem("authorisation", res.data.permission);
      setAuthorisation(res.data.permission);
      console.log(authorisation);
      console.log(localStorage.getItem("token"));
    })
    .catch(err => {
      console.log(err);
    });
  }

  const logout = () => {
    localStorage.removeItem("token");
    setAuthorisation("");
  }

  return (
    <>
      <Nav>
        <NavbarContainer>
          <NavLogo to='/'>GOLD SKYE</NavLogo>
          <HamburgerMenu onClick={toggleMenu}>
            <FaBars />
          </HamburgerMenu>
          <NavMenu>
            <NavItem>
              <NavLinks to='/all-rooms'>Our Rooms</NavLinks>
            </NavItem>
            <NavItem>
              <NavLinks to='/'>About Us</NavLinks>
            </NavItem>
            { currentUser && (
              <NavItem>
                <NavLinks to='/profile'>Profile</NavLinks>
              </NavItem>
            )} 
            
          </NavMenu>
          <NavBtn>
            <NavBtnLink to='/login'>Login</NavBtnLink>
          </NavBtn>
        </NavbarContainer>
      </Nav>
    </>
  )
}
 
export default Navbar