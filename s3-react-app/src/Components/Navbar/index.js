import React from 'react';
import { FaBars } from 'react-icons/fa'
import { Nav, NavbarContainer, NavLogo, HamburgerMenu, NavMenu, NavItem, NavLinks, NavBtn, NavBtnLink } from './NavbarStyled';

const Navbar = ({ toggleMenu }) => {
  return (
    <>
      <Nav>
        <NavbarContainer>
          <NavLogo to='/'>LOGO</NavLogo>
          <HamburgerMenu onClick={toggleMenu}>
            <FaBars />
          </HamburgerMenu>
          <NavMenu>
            <NavItem>
              <NavLinks to='rooms'>Our Rooms</NavLinks>
            </NavItem>
            <NavItem>
              <NavLinks to='about'>About Us</NavLinks>
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