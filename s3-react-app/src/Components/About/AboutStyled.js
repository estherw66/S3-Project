import styled from "styled-components";

export const AboutContainer = styled.div`
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
    grid-template-columns: 30% 60%;
    margin-left: 5%;
    margin-right: 5%;

    @media screen and (max-width: 480px){
        display: grid;
        grid-template-columns: 100%;
    }
`

export const FormCol = styled.div`

`

export const AboutCol = styled.div`

`

export const Form = styled.form`
    justify-content: space-between;
    margin-left: 25px;
    border: 1px solid #000;
`

export const InputDiv = styled.div`
    margin: 20px 0;
    width: 100%;
    display: inline-block;
    margin-left: 1%;
`

export const Label = styled.label` 

`

export const Input = styled.input`

`
export const Button = styled.button` 

`