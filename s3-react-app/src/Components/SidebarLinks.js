import {HiOutlineTicket} from 'react-icons/hi'
import {FaUserAlt} from 'react-icons/fa'
import {FaHotel} from 'react-icons/fa'
import {FaUsers} from 'react-icons/fa'
import {MdLogout} from 'react-icons/md'

export const SidebarLinks = [
    {
        title: 'Account',
        icon: <FaUserAlt />,
        url: '/profile',
        roles: [
            'ADMIN', 'EMPLOYEE'
        ]
    },
    {
        title: 'Reservations',
        icon: <HiOutlineTicket />,
        url: '/employee/reservations',
        roles: [
            'ADMIN', 'EMPLOYEE'
        ]
    },
    {
        title: 'Rooms',
        icon: <FaHotel />,
        url: '/employee/rooms',
        roles: [
            'ADMIN', 'EMPLOYEE'
        ]
    },
    {
        title: 'Employees',
        icon: <FaUsers />,
        url: '/employees',
        roles: [
            'ADMIN'
        ]
    },
    {
        title: 'Logout',
        icon: <MdLogout />,
        url: '/logout',
        roles: [
            'ADMIN', 'EMPLOYEE'
        ]
    }
]