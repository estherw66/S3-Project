import React, { Component } from "react";
import axios from "axios";

import RoomCard from './RoomCard';

const api = axios.create({
    baseURL: 'http://localhost:8080/api/rooms'
})

export default class FeaturedRoomList extends Component {
    // state = {
    //     url: "http://localhost:8080/api/rooms",
    //     room: null
    // };

    // async componentDidMount() {
    //     const res = await axios.get(this.state.url);
    //     this.setState({ room: res.data['results']});
    // }

    state = {
        rooms: []
    }

    constructor(){
        super();
        this.getRooms();
    }

    getRooms = async () => {
        let data = await api.get('/').then(({data}) =>
        data);
        this.setState({ rooms: data})
    }


    render(){
        return(
            <>
                
                {/* {this.state.rooms ? (
                    <div className="row">
                        {this.state.room.map(room => (
                            <h5 key={room.id}>
                                Type: {room.type}
                                from: ${room.basePricePerNight} per night.
                            </h5>
                            // <RoomCard
                            // key={room.id}
                            // roomtype={room.type}/>
                        ))}
                    </div>
                ) : (
                    <h3>Loading rooms...</h3>
                )} */}
            </>
        )
    }
}