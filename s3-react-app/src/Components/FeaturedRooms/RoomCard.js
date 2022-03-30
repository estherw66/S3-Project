import React, { Component } from "react";

export default class RoomCard extends Component {

    state = {
        type: '',

    };

    componentDidMount(){
        const { type } = this.props;

        this.setState({
            type
        });
    }

    render(){

        return(
            <div>
                <div>
                    <h5>{this.state.type}</h5>
                </div>
            </div>
        );
    }
}