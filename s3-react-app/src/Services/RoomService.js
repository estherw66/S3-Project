import httpCommon from "../http-common";

const getAll = () => {
    return httpCommon.get("/rooms")
}

const RoomService = {
    getAll
};

export default RoomService;