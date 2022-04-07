import styled from "styled-components";

// export const NavbarContainer = styled.div`
//     display: flex;
//     justify-content: space-between;
//     height: 80px;
//     z-index: 1;
//     width: 100%;
//     padding: 0 24px;
//     max-width: 1100px;
// `;

/* index */
export const RoomContainer = styled.div`
    width: 80%;
    margin: auto;
    text-align: center;
    /* background: #D2D7DF; */
`

export const Heading = styled.h1`
    color: #8A897C;
`

export const Row = styled.div`
    justify-content: space-between;
    display: grid;
    grid-template-columns: 30% 30% 30%;
    margin-left: 5%;
    margin-right: 5%;

    @media screen and (max-width: 480px){
        display: grid;
        grid-template-columns: 100%;
    }
`

/* room col */
export const RoomCol = styled.div`
    flex-basis: 33%;
    border-radius: 10px;
    margin-bottom: 5%;
    text-align: center;
    border: 1px solid #000;
`
export const Card = styled.div`
    width: 100%;
`

export const Image = styled.img`   
    width: 100%;
    border-radius: 10px;
`

export const Heading3 = styled.h3`
    margin-top: 16px;
    margin-bottom: 15px;
    text-align: center;
    color: #353535;
`

export const SubTitle = styled.p`
    padding: 0;
    color: #444;
`