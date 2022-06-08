import React, { useEffect } from 'react'
import { useNavigate } from 'react-router';
import useAuth from '../hooks/useAuth'

const Logout = () => {
    const { setAuth } = useAuth();

    const navigate = useNavigate();

    const handleLogout = () => {
        setAuth({});
        navigate('/login')
    }

    useEffect(() => {
        handleLogout()
    }, [])
    
  return (
    <div>Loggin out...</div>
  )
}

export default Logout 