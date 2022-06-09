import React from 'react'
import { Link } from 'react-router-dom'
import BannerImg from '../../img/Haymarket-banner.jpg'

const Banner = () => {
  return (
    <div className='home-container'>
        <div className='img-container'>
            <img src={BannerImg} className='banner-img' />
            <div className='banner-text'>
                <h1>Login to make a <br/>reservation!</h1>
                <Link to={'/login'} className={'banner-btn'}>LOGIN</Link>
            </div>
        </div>
    </div>
  )
}

export default Banner