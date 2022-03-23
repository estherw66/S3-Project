import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./Pages";

function App() {
    return (
       <>
        <Router>
            <Home />
        </Router>
       </>
    );
}

export default App;