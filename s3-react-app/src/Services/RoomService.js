import httpCommon from "../http-common";

const getAll = () => {
    return httpCommon.get("/rooms")
}

const getSearchResults = () => {
    return httpCommon.get("/rooms/search")
}

const RoomService = {
    getAll,
    getSearchResults
};

export default RoomService;