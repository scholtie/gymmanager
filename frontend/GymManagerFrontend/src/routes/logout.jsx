import {redirect, useLoaderData} from "react-router-dom";
import axios from "axios";

export async function loader() {
    console.log(localStorage.getItem('SavedToken'));
    const config = {
        headers: {
            Authorization:localStorage.getItem('SavedToken')
        }
    }
    const results = await axios.post('http://localhost:8081/auth/logout', config)
        .then(response => console.log(response))
        .catch(err => console.log(err))
    localStorage.clear();
    return null;
}
export default function Logout() {
    return (
        <div id="logout">
            <h1>Successfully logged out</h1>
        </div>);
}
