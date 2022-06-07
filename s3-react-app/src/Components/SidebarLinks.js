import {HiOutlineTicket} from 'react-icons/hi'
import {FaUserAlt} from 'react-icons/fa'
import {FaHotel} from 'react-icons/fa'
import {FaUsers} from 'react-icons/fa'
import {MdLogout} from 'react-icons/md'

export const SidebarLinks = [
    {
        title: 'Account',
        icon: <FaUserAlt />,
        url: '/profile'
    },
    {
        title: 'Reservations',
        icon: <HiOutlineTicket />,
        url: '/employee/reservations'
    },
    {
        title: 'Rooms',
        icon: <FaHotel />,
        url: '/employee/rooms'
    },
    {
        title: 'Employees',
        icon: <FaUsers />,
        url: '/employees'
    },
    {
        title: 'Logout',
        icon: <MdLogout />,
        url: '/logout'
    }
]