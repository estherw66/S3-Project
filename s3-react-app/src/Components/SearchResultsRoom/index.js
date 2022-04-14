import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom'

import RoomCard from '../FeaturedRooms/RoomCard';

const SearchResultsRoom = () => {
    const [searchResults, setSearchResults] = useState([]);

    const query = useLocation().search
    const params = new URLSearchParams(query)
    const guests = params.get("guests")
    const type = params.get("type")


    const getSearchResults = () => {
        axios.get("http://localhost:8080/api/rooms/search?guests=" + guests + "&type=" + type)
        .then(response => {
            setSearchResults(response.data);
            console.log(response.data);
        })
        .catch(err => {
            console.log(err);
        })
    }

    useEffect(() => {
        getSearchResults();
    }, []);

  return (
    <>
        {searchResults.length > 0 ? (
            <>
                {searchResults.map((room) => (
                    <RoomCard key={room.id} type={room.type} price={room.basePricePerNight} imgUrl={room.imgUrl} />
            	))}
            </>
        ) : (
            <p>Loading rooms...</p>
        )}
    </>
  )
}

export default SearchResultsRoom