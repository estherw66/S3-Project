import axios from "axios";

const login = (username, password) => {
    return axios.post('http://localhost:8080/api/login', {
        username,
        password
    })
    .then((res) => {
        if (res.data.accessToken) {
            localStorage.setItem("accessToken", JSON.stringify(res.data));
        }

        return res.data;
    })
}

const logout = () => {
    localStorage.removeItem("accessToken");
}

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("accessToken"));
}

const authService = {
    login,
    logout,
    getCurrentUser,
};

export default authService;