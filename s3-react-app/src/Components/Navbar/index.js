import React from 'react';
import { FaBars } from 'react-icons/fa'
import { Nav, NavbarContainer, NavLogo, HamburgerMenu, NavMenu, NavItem, NavLinks, NavBtn, NavBtnLink } from './NavbarStyled';

const Navbar = ({ toggleMenu }) => {
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
              <NavLinks to='/'>Our Rooms</NavLinks>
            </NavItem>
            <NavItem>
              <NavLinks to='/'>About Us</NavLinks>
            </NavItem>
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