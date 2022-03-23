import React from 'react'
import { SidebarContainer, Icon, CloseIcon, SidebarWrapper, SidebarMenu, SidebarLink, SideBtnWrap, SidebarRoute } from './SidebarStyled'

const Sidebar = ({ isOpen, toggleMenu }) => {
  return (
    <>
        <SidebarContainer isOpen={isOpen} onClick={toggleMenu}>
            <Icon onClick={toggleMenu}>
                <CloseIcon />
            </Icon>
            <SidebarWrapper>
                <SidebarMenu>
                    <SidebarLink to='rooms' onClick={toggleMenu}>
                        Our Rooms
                    </SidebarLink>
                    <SidebarLink to='about' onClick={toggleMenu}>
                        About Us
                    </SidebarLink>
                </SidebarMenu>
                <SideBtnWrap>
                    <SidebarRoute to='/login'>
                        Login
                    </SidebarRoute>
                </SideBtnWrap>
            </SidebarWrapper>
        </SidebarContainer>
    </>
  )
}

export default Sidebar